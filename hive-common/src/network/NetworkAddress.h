/*
 * NetworkAddress.h
 *
 *  Created on: 14-05-2012
 *      Author: roy
 */

#ifndef NETWORKADDRESS_H_
#define NETWORKADDRESS_H_

#define MAX_HOSTPORT_SIZE 50

namespace KernelHive {

class NetworkAddress {
public:
	NetworkAddress(char *host, int port);
	NetworkAddress(char *host, char *port);
	virtual ~NetworkAddress();
	const char* toString();

	char *host;
	int port;

};

}

#endif /* NETWORKADDRESS_H_ */
