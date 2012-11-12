#include <CL/cl.h>
#include <sstream>

#include "OpenClHost.h"

namespace KernelHive {

// ========================================================================= //
// 							Static Members									 //
// ========================================================================= //

	OpenClHost OpenClHost::instance;

	/*static*/ OpenClHost* OpenClHost::getInstance() {
		return &instance;
	}

	/*static*/ std::string OpenClHost::getDevicesInfo() {
		std::stringstream stream;

		cl_uint counts = 0;
		cl_uint count = 0;
		OpenClDevice** devices;
		OpenClPlatform** platforms = instance.getPlatforms();
		for (cl_uint i = 0; i < instance.getPlatformsCount(); i++) {
			// TODO Commented out so that only CPU devices are returned
			/*count = platforms[i]->getGpuDevicesCount();
			if (count > 0) {
				counts += count;
				devices = platforms[i]->getGpuDevices();
				for (cl_uint j = 0; j < count; j++) {
					stream << devices[j]->getDeviceInfo();
					if (j + 1 < count) {
						stream << OpenClPlatform::DEVICES_INFO_SEPARATOR;
					}
				}
			}*/
			count = platforms[i]->getCpuDevicesCount();
			if (count > 0) {
				counts += count;
				devices = platforms[i]->getCpuDevices();
				for (cl_uint j = 0; j < count; j++) {
					stream << devices[j]->getDeviceInfo();
					if (j + 1 < count) {
						stream << OpenClPlatform::DEVICES_INFO_SEPARATOR;
					}
				}
			}
			if (i + 1 < instance.getPlatformsCount()) {
				stream << OpenClPlatform::DEVICES_INFO_SEPARATOR;
			}
		}

		std::stringstream outStream;
		outStream << counts;
		// TODO Commented out while only CPU devices are fetched
		//outStream << OpenClPlatform::DEVICES_INFO_SEPARATOR;
		outStream << stream.str();
		return outStream.str();
	}

// ========================================================================= //
// 							Public Members									 //
// ========================================================================= //

	OpenClHost::OpenClHost() {
		cl_uint count = getAvailablePlatformsCount();
		cl_platform_id* ids = new cl_platform_id[count];
		getAvailablePlatformIds(ids, count);

		platforms = new OpenClPlatform*[count];
		platformsCount = count;
		for (cl_uint i = 0; i < count; i++) {
			platforms[i] = new OpenClPlatform(ids[i]);
			OpenClDevice** devices = platforms[i]->getCpuDevices();
			for (cl_uint j = 0; j < platforms[i]->getCpuDevicesCount(); j++) {
				devicesMap[devices[j]->getIdentifier()].push_back(devices[j]);
			}
			devices = platforms[i]->getGpuDevices();
			for (cl_uint j = 0; j < platforms[i]->getGpuDevicesCount(); j++) {
				devicesMap[devices[j]->getIdentifier()].push_back(devices[j]);
			}
		}
		delete [] ids;
	}

	OpenClHost::~OpenClHost() {
		for (cl_uint i = 0; i < platformsCount; i++) {
			delete platforms[i];
		}
		delete [] platforms;
	}

	OpenClDevice* OpenClHost::lookupDevice(std::string identifier) {
		OpenClDevice* device = NULL;
		if (devicesMap.find(identifier) != devicesMap.end()) {
			DevicesList list = devicesMap[identifier];
			DevicesList::iterator iter;
			for (iter = list.begin(); iter != list.end(); iter++) {
				if ((*iter)->getDeviceAvailability() == CL_TRUE) {
					device = *iter;
					break;
				}
			}

		}
		return device;
	}

	OpenClPlatform** OpenClHost::getPlatforms() {
		return this->platforms;
	}

	cl_uint OpenClHost::getPlatformsCount() {
		return this->platformsCount;
	}

// ========================================================================= //
// 							Private Members									 //
// ========================================================================= //

	cl_uint OpenClHost::getAvailablePlatformsCount() {
		cl_uint count = 0;

		cl_int retVal = clGetPlatformIDs(0, NULL, &count);
		if (retVal != CL_SUCCESS) {
			// TODO throw exception
		}

		return count;
	}

	void OpenClHost::getAvailablePlatformIds(cl_platform_id* ids, cl_uint count) {
		cl_int retVal = clGetPlatformIDs(count, ids, &count);
		if (retVal != CL_SUCCESS) {
			// TODO throw exception
		}
	}

}
