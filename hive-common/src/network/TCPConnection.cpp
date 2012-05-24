/*
 * TCPConnection.cpp
 *
 *  Created on: 24-05-2012
 *      Author: roy
 */

#include <cstdio>
#include <cstdlib>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <sys/socket.h>

#include "../commons/Logger.h"
#include "TCPConnection.h"

namespace KernelHive {

TCPConnection::TCPConnection(int sockfd, TCPConnectionListener *listener) {
	this->listener = listener;
	this->sockfd = sockfd;
}

void TCPConnection::executeLoopCycle() {
	TCPMessage *message;
	try {
		message = readMessage();
	}
	catch(const char *msg) {
		Logger::log(ERROR, "Couldn't read from socket: %s. Reconnecting.\n", msg);
		listener->onDisconnected(this->sockfd);
		return;
	}
	if(listener != NULL)
		listener->onMessage(this->sockfd, message);
}

TCPMessage* TCPConnection::readMessage() {
	char *message = (char *)(calloc(MAX_MESSAGE_BYTES, sizeof (char)));
        bzero(message, MAX_MESSAGE_BYTES);
        int n = read(sockfd, message, MAX_MESSAGE_BYTES);
        if(n < 0)
            throw (strerror(errno));

        if(n == 0)
            throw ("Server disconnected.");

        return new TCPMessage(message, n);
}

void TCPConnection::sendMessage(char *msg)
{
	if(write(sockfd, msg, strlen(msg)) < 0)
		Logger::log(ERROR, "Error writing to socket.\n");
	printf("Sent TCP message %s\n");
}

void TCPConnection::disconnect()
{
	shutdown(sockfd, SHUT_RDWR);
}

TCPConnection::~TCPConnection() {
	// TODO Auto-generated destructor stub
}

}
