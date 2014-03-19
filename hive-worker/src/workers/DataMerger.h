/**
 * Copyright (c) 2014 Gdansk University of Technology
 * Copyright (c) 2014 Rafal Lewandowski
 * Copyright (c) 2014 Pawel Rosciszewski
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
#ifndef KERNEL_HIVE_DATA_MERGER_H
#define KERNEL_HIVE_DATA_MERGER_H

#include <string>

#include "BasicWorker.h"
#include "network/NetworkAddress.h"
#include "threading/SynchronizedBuffer.h"
#include "../communication/DataUploaderTCP.h"

namespace KernelHive {

/**
 * A worker which implements the dataMerger kernel logic.
 * Parameters currently expected as input:
 * <ul>
 *   <li>n - number of parts to merge</li>
 *   <li>n tuples of input data host, port and ID, one for each part</li>
 *   <li>output data host</li>
 *   <li>output data port</li>
 * </ul>
 */
class DataMerger : public BasicWorker {

public:
	/**
	 * Initializes a new instance of DataMerger.
	 *
	 * @param clusterAddress the address of the KernelHive cluster
	 */
	DataMerger(char **argv);

	/**
	 * The destructor.
	 */
	virtual ~DataMerger();

protected:
	/**
	 * Gets the name of the kernel to use for calculations.
	 *
	 * @return the kernel name
	 */
	const char* getKernelName();

	/**
	 * The processing logic should be implemented in this method.
	 */
	void workSpecific();

	/**
	 * Performs DataMerger specific initialization.
	 *
	 * @param argv the parameters passed to this worker, parameter offset
	 * 		is shifted to DataMerger specific parameters
	 */
	void initSpecific(char *const argv[]);

private:
	/** The name of the kernel to use for calcaulations. */
	static const char* KERNEL;

	/** The number of data sources to merge from. */
	int datasCount;

	/** The addresses from which the data can be downloaded. */
	NetworkAddress** inputDataAddresses;

	/** The identifier which can be used to download data for this worker. */
	std::string** dataIds;

	/** The data identifier in the integer number form. */
	int* dataIdsInt;

	/** The total size of data received from all sources. */
	size_t totalDataSize;

	/** The address to which the output data will be uploaded. */
	NetworkAddress* outputDataAddress;

	/** A buffer for storing the result of calculations. */
	SynchronizedBuffer* resultBuffer;

	void cleanupResources();

};

} /* namespace KernelHive */

#endif /* KERNEL_HIVE_DATA_MERGER_H */
