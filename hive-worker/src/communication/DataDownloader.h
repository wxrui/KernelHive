#ifndef KERNEL_HIVE_DATA_DOWNLOADER_H
#define KERNEL_HIVE_DATA_DOWNLOADER_H

#include <pthread.h>

#include "network/TCPClient.h"
#include "network/TCPClientListener.h"
#include "network/TCPMessage.h"
#include "threading/Thread.h"
#include "threading/SynchronizedBuffer.h"

namespace KernelHive {

/**
 * A thread responsible for downloading data from from a given
 * network location.
 */
class DataDownloader
	: public TCPClient, public TCPClientListener {

public:
	/**
	 * Instantiates this downloader thread.
	 */
	DataDownloader(NetworkAddress* address, const char* dataId, SynchronizedBuffer* buffer);

	/**
	 * Deallocates resources used by this downloader thread.
	 */
	virtual ~DataDownloader();

	/**
	 * Called upon receiving data via the socket.
	 *
	 * @param message the received message
	 */
	void onMessage(TCPMessage* message);

	/**
	 * Called when a connection is established.
	 */
	void onConnected();

private:
	/** The initial state. */
	static const int STATE_INITIAL = 0;

	/** Data size has been acquired. */
	static const int STATE_SIZE_ACQUIRED = 1;

	/** Data has been acquired. */
	static const int STATE_DATA_ACQUIRED = 2;

	/** The command which allows to query for data size. */
	static const int GET_SIZE = 1;

	/** The command which to query for data. */
	static const int GET_DATA = 2;

	/** Defines the current state of this data downloader. */
	int currentState;

	/** Stores the total amount of data to be received. */
	size_t totalDataSize;

	/** Defines the amount of data currently downloaded. */
	size_t progressSize;

	/** The identifier which can be used to query the data repository. */
	int dataId;

	/** The query which can be sent to data repository in order to acquire data size. */
	TCPMessage* sizeQuery;

	int *sizeQueryData;

	/** The query which can be sent to data repository in order to acquire data. */
	TCPMessage* dataQuery;

	int *dataQueryData;

	/** A pointer to the buffer in which the downloaded data should be stored. */
	SynchronizedBuffer* buffer;

	/**
	 * Parses and assigns the provided data size string.
	 *
	 * @return true if size has been parsed successfully, false otherwise
	 */
	bool acquireDataSize(TCPMessage *sizeMessage);

	/**
	 * Pre-compiles the queries which will be sent to the data repository.
	 */
	void prepareQueries();

};

} /* namespace KernelHive */

#endif /* KERNEL_HIVE_DATA_DOWNLOADER_H */
