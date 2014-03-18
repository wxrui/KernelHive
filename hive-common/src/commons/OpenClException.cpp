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
#include "OpenClException.h"

namespace KernelHive {

// ========================================================================= //
// 							Public Members									 //
// ========================================================================= //

	OpenClException::OpenClException(std::string& message, cl_int openClErrorCode) {
		this->message = message;
		this->openClErrorCode = openClErrorCode;
	}

	OpenClException::OpenClException(const char* message, cl_int openClErrorCode) {
		this->message = message;
		this->openClErrorCode = openClErrorCode;
	}

	OpenClException::~OpenClException() {
		// TODO Auto-generated destructor stub
	}

	std::string OpenClException::getMessage() {
		return message;
	}

	cl_int OpenClException::getOpenClErrorCode() {
		return openClErrorCode;
	}

} /* namespace KernelHive */
