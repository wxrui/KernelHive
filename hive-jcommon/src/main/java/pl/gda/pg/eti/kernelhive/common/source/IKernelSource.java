/**
 * Copyright (c) 2014 Gdansk University of Technology
 * Copyright (c) 2014 Marcel Schally-Kacprzak
 * Copyright (c) 2016 Adrian Boguszewski
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
package pl.gda.pg.eti.kernelhive.common.source;

import java.util.List;
import java.util.Map;

import pl.gda.pg.eti.kernelhive.common.kernel.repository.KernelRoleEnum;
import pl.gda.pg.eti.kernelhive.common.validation.ValidationResult;

public interface IKernelSource {

	String GLOBAL_SIZES = "globalSizes";
	String DIMENSIONS_NUMBER = "numberOfDimensions";
	String LOCAL_SIZES = "localSizes";
	String OFFSETS = "offsets";
	String OUTPUT_SIZE = "outputSize";
	String KERNEL_ROLE = "role";

	Map<String, Object> getProperties();

	String getId();

	int getDimensions();

	int[] getGlobalSize();

	int[] getLocalSize();

	int[] getOffset();

	int getOutputSize();

	KernelRoleEnum getKernelRole();

	List<ValidationResult> validate();
}
