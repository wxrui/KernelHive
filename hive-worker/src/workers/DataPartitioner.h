#ifndef KERNEL_HIVE_DATA_PARTITIONER_H
#define KERNEL_HIVE_DATA_PARTITIONER_H

#include "BasicWorker.h"
#include "threading/SynchronizedBuffer.h"

namespace KernelHive {

/**
 * A worker which implements the dataPartitioner kernel logic.
 * Parameters currently expected as input:
 * <ul>
 *   <li>n - number of parts to split to</li>
 * </ul>
 */
class DataPartitioner : public BasicWorker {

public:
	DataPartitioner(char **argv);

	virtual ~DataPartitioner();

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
	/** The number of parts to split input data to. */
	int partsCount;

	/** The total size of data received from all sources. */
	size_t totalDataSize;

	/** Buffers array for storing data parts. */
	SynchronizedBuffer** resultBuffer;

};

} /* namespace KernelHive */

#endif /* KERNEL_HIVE_DATA_PARTITIONER_H */