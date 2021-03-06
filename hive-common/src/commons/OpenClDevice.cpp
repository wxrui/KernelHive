/**
 * Copyright (c) 2014 Gdansk University of Technology
 * Copyright (c) 2014 Rafal Lewandowski
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
#include <CL/cl.h>
#include <string>
#include <sstream>
#include <algorithm>
#include <cctype>

#include "OpenClDevice.h"

namespace KernelHive {

// ========================================================================= //
// 							Public Members									 //
// ========================================================================= //

	OpenClDevice::OpenClDevice(cl_platform_id clPlatformId, cl_device_id clDeviceId) {
		this->clPlatformId = clPlatformId;
		this->clDeviceId = clDeviceId;
		constructIdentifier();
	}

	OpenClDevice::OpenClDevice(const OpenClDevice& device) {
		this->clPlatformId = device.clPlatformId;
		this->clDeviceId = device.clDeviceId;
		this->identifier = device.identifier;
	}

	OpenClDevice::~OpenClDevice() {
		// TODO Auto-generated destructor stub
	}

	std::string OpenClDevice::getDeviceInfo() {
		std::stringstream stream;

		stream << getIdentifier() << DEVICE_INFO_SEPARATOR;
		stream << getDeviceName() << DEVICE_INFO_SEPARATOR;
		stream << getDeviceVendor() << DEVICE_INFO_SEPARATOR;
		stream << getDeviceAvailability() << DEVICE_INFO_SEPARATOR;
		stream << getMaxComputeUnits() << DEVICE_INFO_SEPARATOR;
		stream << getMaxClockFrequency() << DEVICE_INFO_SEPARATOR;
		stream << getGlobalMemSize() << DEVICE_INFO_SEPARATOR;
		stream << getLocalMemSize() << DEVICE_INFO_SEPARATOR;
		stream << getMaxWorkGroupSize();

		return stream.str();
	}

	std::string OpenClDevice::getIdentifier() {
		// TODO Implement an identifier which will actually be used..
		return identifier;
	}

	std::string OpenClDevice::getDeviceName() {
		std::string deviceName;
		char buffer[DEVICE_INFO_QUERY_BUFFER_SIZE];

		// TODO Error handling
		cl_int retVal = clGetDeviceInfo(clDeviceId, CL_DEVICE_NAME,
				DEVICE_INFO_QUERY_BUFFER_SIZE, buffer, NULL);
		if (retVal == CL_SUCCESS) {
			deviceName = buffer;
		}

		return deviceName;
	}

	std::string OpenClDevice::getDeviceVendor() {
		std::string deviceVendor;
		char buffer[DEVICE_INFO_QUERY_BUFFER_SIZE];

		// TODO Error handling
		cl_int retVal = clGetDeviceInfo(clDeviceId, CL_DEVICE_VENDOR,
				DEVICE_INFO_QUERY_BUFFER_SIZE, buffer, NULL);
		if (retVal == CL_SUCCESS) {
			deviceVendor = buffer;
		}

		return deviceVendor;
	}

	cl_uint OpenClDevice::getDeviceVendorId() {
		cl_uint val;
		// TODO Error handling
		/*cl_int retVal = */clGetDeviceInfo(clDeviceId, CL_DEVICE_VENDOR_ID,
				sizeof(cl_uint), &val, NULL);
		return val;
	}

	cl_bool OpenClDevice::getDeviceAvailability() {
		cl_bool val;
		// TODO Error handling
		/*cl_int retVal = */clGetDeviceInfo(clDeviceId, CL_DEVICE_AVAILABLE,
				sizeof(cl_bool), &val, NULL);
		return val;
	}

	cl_uint OpenClDevice::getMaxComputeUnits() {
		cl_uint val;
		// TODO Error handling
		/*cl_int retVal = */clGetDeviceInfo(clDeviceId, CL_DEVICE_MAX_COMPUTE_UNITS,
				sizeof(cl_uint), &val, NULL);
		return val;
	}

	cl_uint OpenClDevice::getMaxClockFrequency() {
		cl_uint val;
		// TODO Error handling
		/*cl_int retVal = */clGetDeviceInfo(clDeviceId, CL_DEVICE_MAX_CLOCK_FREQUENCY,
				sizeof(cl_uint), &val, NULL);
		return val;
	}

	cl_ulong OpenClDevice::getGlobalMemSize() {
		cl_ulong val;
		// TODO Error handling
		/*cl_int retVal = */clGetDeviceInfo(clDeviceId, CL_DEVICE_GLOBAL_MEM_SIZE,
				sizeof(cl_ulong), &val, NULL);
		return val;
	}

	cl_ulong OpenClDevice::getLocalMemSize() {
		cl_ulong val;
		// TODO Error handling
		/*cl_int retVal = */clGetDeviceInfo(clDeviceId, CL_DEVICE_LOCAL_MEM_SIZE,
				sizeof(cl_ulong), &val, NULL);
		return val;
	}

	size_t OpenClDevice::getMaxWorkGroupSize() {
		size_t val;
		// TODO Error handling
		/*cl_int retVal = */clGetDeviceInfo(clDeviceId, CL_DEVICE_MAX_WORK_GROUP_SIZE,
				sizeof(size_t), &val, NULL);
		return val;
	}

	cl_platform_id OpenClDevice::getClPlatformId() {
		return this->clPlatformId;
	}

	cl_device_id OpenClDevice::getClDeviceId() {
		return this->clDeviceId;
	}

// ========================================================================= //
// 							Private Members									 //
// ========================================================================= //

	void OpenClDevice::constructIdentifier() {
		identifier = getDeviceVendor() + getDeviceName();
		identifier.erase(std::remove_if(identifier.begin(), identifier.end(), isspace),
				identifier.end());
	}

} /* namespace KernelHive */
