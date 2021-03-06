/**
 * Copyright (c) 2014 Gdansk University of Technology
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
#include "HostStatusSerializer.h"
#include <sstream>
#include <string>
#include <cstdlib>
#include <cstdio>
#include <list>
#include "commons/ByteBuffer.h"
#include "MessageType.h"

using namespace std;

namespace KernelHive {

HostStatusSerializer::HostStatusSerializer() { }

ByteBuffer* HostStatusSerializer::serializeToSequentialMessage(HostStatus* hostStatus) {
	ByteBuffer* message = new ByteBuffer();
	
	byte messageType = SEQUENTIAL_MESSAGE;
	message->append(messageType);
	message->append(hostStatus->getId());
	message->append(hostStatus->getCpuSpeed());
	
	short int coresCount = hostStatus->getCpuCount();
	message->append(coresCount);
	for(int i = 0; i < coresCount; i++) {
		message->append(hostStatus->getCpuUsage(i));
	}

	message->append(hostStatus->getMemoryUsed());

	list<GPUStatus*> gpuDevices = hostStatus->getGpuDevices();
	short int gpuDevicesCount = gpuDevices.size();
	message->append(gpuDevicesCount);
	for(list<GPUStatus*>::const_iterator it = gpuDevices.begin();
			it != gpuDevices.end(); it++) {
		GPUStatus* gpu = *it;
		message->append(gpu->getId());
		message->append(gpu->getUsedMemory());
		message->append(gpu->getGpuUsage());
		message->append(gpu->getFanSpeed());
	}

	return message;	
}

ByteBuffer* HostStatusSerializer::serializeToInitialMessage(HostStatus* hostStatus) {
	ByteBuffer* message = new ByteBuffer();
	
	byte messageType = INITIAL_MESSAGE;
	message->append(messageType);

	short int unitId = hostStatus->getId();
	message->append(unitId);

	short int coresCount = hostStatus->getCpuCount();
	message->append(coresCount);

	int memoryTotal = hostStatus->getMemoryTotal();
	message->append(memoryTotal);

	short int gpuCount = hostStatus->getGpuDevices().size();
	message->append(gpuCount);

	printf("initial: %hd %hd %d %hd\n", unitId, coresCount, memoryTotal, gpuCount);

	return message;	
}

string HostStatusSerializer::serializeToStringInitialMessage(HostStatus* hostStatus) {
	stringstream ss;

	ss << hostStatus->getId();
	printf("serializeToStringInitialMessage\n");
	ss << ":" << hostStatus->getCpuCount();
	ss << ":" << hostStatus->getMemoryTotal();

	list<GPUStatus*> gpuDevices = hostStatus->getGpuDevices();
	ss << ":" << gpuDevices.size();

	for(list<GPUStatus*>::const_iterator it = gpuDevices.begin(); it != gpuDevices.end(); it++) {
		GPUStatus* gpu = *it;
		ss << ":" << gpu->getId();
		ss << ":" << gpu->getName();
		ss << ":" << gpu->getVendorName();
		ss << ":" << gpu->getIdentifier();
		ss << ":" << gpu->getTotalMemory();
		ss << ":" << gpu->isAvailable();
	}

	string str = ss.str();
	printf("initial: %s\n", str.c_str());

	return ss.str();
}

} /* namespace KernelHive */
