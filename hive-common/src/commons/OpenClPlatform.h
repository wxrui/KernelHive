/**
 * Copyright (c) 2014 Gdansk University of Technology
 * Copyright (c) 2014 Rafal Lewandowski
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
#ifndef KERNEL_HIVE_OPEN_CL_PLATFORM_H
#define KERNEL_HIVE_OPEN_CL_PLATFORM_H

#include <CL/cl.h>

#include "OpenClDevice.h"

namespace KernelHive {

	/**
	 * Represents an OpenCL platform.
	 */
	class OpenClPlatform {

	public:
		/** The separator character used in the information strings. */
		static const char DEVICES_INFO_SEPARATOR = ';';

		/**
		 * The default constructor.
		 */
		OpenClPlatform();

		/**
		 * The default constructor.
		 */
		OpenClPlatform(cl_platform_id platformId);

		/**
		 * The copy constructor.
		 *
		 * @param platform the platform
		 */
		OpenClPlatform(OpenClPlatform& platform);

		/**
		 * The destructor.
		 */
		virtual ~OpenClPlatform();

		/**
		 * Gets the OpenCL identifier of this platform.
		 *
		 * @return the cl_pltaform_id of this platform or NULL
		 * 		if the platform could not be initialized
		 */
		cl_platform_id getClPlatformId();

		/**
		 * Gets the GPU devices available on this platform.
		 *
		 * @return an array of GPU devices available on this platform
		 */
		OpenClDevice** getGpuDevices();

		/**
		 * Gets the number of GPU devices available on this platform.
		 *
		 * @return the number of GPU devices available on this platform
		 */
		uint32_t getGpuDevicesCount();

		/**
		 * Gets the CPU devices available on this platform.
		 *
		 * @return an array of CPU devices available on this platform
		 */
		OpenClDevice** getCpuDevices();

		/**
		 * Gets the number of CPU devices available on this platform.
		 *
		 * @return the number of CPU devices available on this platform
		 */
		uint32_t getCpuDevicesCount();

	private:
		/** The OpenCL ID of the platform. */
		cl_platform_id clPlatformId;

		/** The GPU devices available on this platform. */
		OpenClDevice** gpuDevices;

		/** The number of GPU devices available on this platform. */
		uint32_t gpuDevicesCount;

		/** The CPU devices available on this platform. */
		OpenClDevice** cpuDevices;

		/** The number of CPU devices available on this platform. */
		uint32_t cpuDevicesCount;

		/**
		 * Gets the information about provided OpenCL devices.
		 *
		 * @param devices an array of pointers to OpenCL devices
		 * @param count the number of given devices
		 * @return a string containing information about given devices
		 */
		static std::string getDevicesInfo(OpenClDevice** devices, uint32_t count);

		/**
		 * Initializes the devices on this platform.
		 */
		void initializeDevices(cl_device_type deviceType,
				OpenClDevice*** devices, uint32_t* numDevices);

		/**
		 * Deallocates the memory used to store devices of this
		 * platform.
		 */
		void cleanupDevices(OpenClDevice*** devices, uint32_t devicesCount);

		/**
		 * Gets the IDs of devices of the chosen type available
		 * on this platform. Will dynamically allocate the deviceIds
		 * array - deallocation should be handled by the caller.
		 *
		 * @param deviceType the type of devices to look for
		 * @param deviceIds the address of OpenCL device IDs array
		 * @param numDevices the address of the number of devices found
		 * 		variable
		 * @return the OpenCL error code of this operation
		 */
		cl_int getDeviceIds(cl_device_type deviceType,
				cl_device_id** deviceIds, cl_uint* numDevices);

	};

} /* namespace KernelHive */

#endif /* KERNEL_HIVE_OPEN_CL_PLATFORM_H */
