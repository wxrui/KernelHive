#ifndef KERNEL_HIVE_DATA_UPLOADER_H
#define KERNEL_HIVE_DATA_UPLOADER_H

#include <string>

#include "network/TCPClient.h"
#include "network/TCPClientListener.h"
#include "threading/SynchronizedBuffer.h"

namespace KernelHive {

class DataUploader
	: public TCPClient, public TCPClientListener {

public:
	/**
	 * Initializes this data uploader.
	 *
	 * @param address the data repository address
	 * @param buffer the buffer with data to upload
	 */
	DataUploader(NetworkAddress* address, SynchronizedBuffer* buffer);

	/**
	 * The destructor
	 */
	virtual ~DataUploader();

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

	std::string* getDataIdentifier();

private:
	/** The initial state. */
	static const int STATE_INITIAL = 0;

	/** The identifier of uploaded data has been acquired. */
	static const int STATE_IDENTIFIER_ACQUIRED = 1;

	/** The size to use for the upload buffer. */
	static const size_t UPLOAD_BATCH = 1024;

	/** The command which allows to publish data in the repository. */
	static const char* PUBLISH_DATA;

	/** A command which allows to append data to existing storage. */
	static const char* APPEND_DATA;

	/** The current state of this data uploader. */
	int currentState;

	/** A pointer to the buffer with data to be uploaded. */
	SynchronizedBuffer* buffer;

	/** The command which tells the repository that data will be uploaded to it. */
	std::string dataPublish;

	/** The identifier of uploaded data - returned by the repository after uploading. */
	std::string dataIdentifier;

	/**
	 * Pre-compiles the commands which will be sent to the data repository.
	 */
	void prepareCommands();

	/**
	 * Formats a data append command for sending.
	 *
	 * @param dataId the data identifier
	 * @param packageSize the size of the package to send
	 * @return the formatted prefix of the data append command
	 */
	std::string formatDataAppend(std::string dataId, size_t packageSize);

	/**
	 * Performs uploading of data.
	 */
	void uploadData();

};

} /* namespace KernelHive */

#endif /* KERNEL_HIVE_DATA_UPLOADER_H */