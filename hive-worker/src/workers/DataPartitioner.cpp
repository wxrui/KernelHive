/**
 * Copyright (c) 2014 Gdansk University of Technology
 * Copyright (c) 2014 Rafal Lewandowski
 * Copyright (c) 2014 Pawel Rosciszewski
 * Copyright (c) 2014 Szymon Bultrowicz
 *
 * This file is part of KernelHive.
 * KernelHive is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * KernelHive is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with KernelHive. If not, see <http://www.gnu.org/licenses/>.
 */
#include <iostream>

#include "DataPartitioner.h"

#include "commons/KhUtils.h"
#include "../communication/DataUploaderTCP.h"

namespace KernelHive {

// ========================================================================= //
// 							Constants Init									 //
// ========================================================================= //

const char* DataPartitioner::KERNEL = "partitionData";

// ========================================================================= //
// 							Public Members									 //
// ========================================================================= //

DataPartitioner::DataPartitioner(char **argv) : BasicWorker(argv) {
	partsCount = 0;
	totalDataSize = 0;
	outputDataAddress = NULL;
	resultBuffers = NULL;
}

DataPartitioner::~DataPartitioner() {
	DataPartitioner::cleanupResources();
}

// ========================================================================= //
// 							Protected Members							     //
// ========================================================================= //

const char* DataPartitioner::getKernelName() {
	return KERNEL;
}

void DataPartitioner::workSpecific() {
	std::cout << ">>> DataPartitioner work BEGIN" << std::endl;
	runAllDownloads(); // Download data and the kernel

	// Wait for the data to be ready
	waitForAllDownloads();

//	std::cout << ">>> data size: " << buffers[dataIdInt]->getSize() << std::endl;
//	buffers[dataIdInt]->logMyFloatData();

	setPercentDone(40);

	totalDataSize = buffers[dataId]->getSize();
	int outputSizeInBytes = outputSize*sizeof(byte);

	// Allocate local result buffers
	for (int i = 0; i < partsCount; i++) {
		resultBuffers[i]->allocate(outputSizeInBytes);
	}

	// Allocate input and output buffers on the device
	context->createBuffer(INPUT_BUFFER, totalDataSize, CL_MEM_READ_WRITE);
	context->createBuffer(OUTPUT_BUFFER, partsCount * outputSizeInBytes, CL_MEM_READ_WRITE);

	// Begin copying data to the device
	OpenClEvent dataCopy = context->enqueueWrite(INPUT_BUFFER, 0,
			totalDataSize, (void*)buffers[dataId]->getRawData());

	// Compile and prepare the kernel for execution
	context->buildProgramFromSource((char *)buffers[kernelDataId]->getRawData(),
			buffers[kernelDataId]->getSize());
	context->prepareKernel(getKernelName());

	// Wait for data copy to finish
	OpenClEvent* tmp = &dataCopy;
	context->waitForEvents(1, &tmp);
	setPercentDone(60);

	// Set kernel agrguments
	context->setBufferArg(0, INPUT_BUFFER);
	context->setValueArg(1, sizeof(unsigned int), (void*)&totalDataSize);
	context->setValueArg(2, sizeof(unsigned int), (void*)&partsCount);
	context->setBufferArg(3, OUTPUT_BUFFER);
	context->setValueArg(4, sizeof(unsigned int), (void*)&outputSize);

	// Execute the kernel
	context->executeKernel(numberOfDimensions, dimensionOffsets,
			globalSizes, localSizes);
	setPercentDone(80);

	// Copy the result:
	OpenClEvent** copyEvents = new OpenClEvent*[partsCount];
	for (int i = 0; i < partsCount; i++) {
		OpenClEvent dataCopy  = context->enqueueRead(OUTPUT_BUFFER, i * outputSizeInBytes,
				outputSizeInBytes, (void*)resultBuffers[i]->getRawData());
		copyEvents[i] = &dataCopy;
//		uploaders.push_back(new DataUploader(outputDataAddress, resultBuffers[i]));
	}

	setPercentDone(90);

	uploaders.push_back(new DataUploaderTCP(outputDataAddress, resultBuffers, partsCount));

	runAllUploads();
	waitForAllUploads();

	setPercentDone(100);
}

void DataPartitioner::initSpecific(char *const argv[]) {
	std::cout << ">>> DataPartitioner init BEGIN" << std::endl;
	// TODO For partitioner only:
	nextParam(argv);
	std::cout << ">>> param skip" << std::endl;
	inputDataAddress = new NetworkAddress(nextParam(argv), nextParam(argv));
	std::cout << ">>> input address ready: " << inputDataAddress->toString() << std::endl;
	dataId = nextParam(argv);
	std::cout << ">>> dataId ready: " << dataId << std::endl;

	buffers[dataId] = new SynchronizedBuffer();

	partsCount = KhUtils::atoi(nextParam(argv));
	std::cout << ">>> partsCount ready: " << partsCount << std::endl;
	resultBuffers = new SynchronizedBuffer*[partsCount];
	for (int i = 0; i < partsCount; i++) {
		resultBuffers[i] = new SynchronizedBuffer();
	}
	outputDataAddress = new NetworkAddress(nextParam(argv), nextParam(argv));
	std::cout << ">>> output address ready: " << outputDataAddress->toString() << std::endl;

	downloaders[dataId] = new DataDownloaderTCP(inputDataAddress,
			dataId.c_str(), buffers[dataId]);

	downloaders[kernelDataId] = new DataDownloaderTCP(kernelAddress,
			kernelDataId.c_str(), buffers[kernelDataId]);
}

// ========================================================================= //
// 							Private Members									 //
// ========================================================================= //

void DataPartitioner::cleanupResources() {
	delete outputDataAddress;
	if (resultBuffers != NULL) {
		for (int i = 0; i < partsCount; i++) {
			if (resultBuffers[i] != NULL) {
				delete resultBuffers[i];
			}
		}
		delete [] resultBuffers;
	}
}

} /* namespace KernelHive */
