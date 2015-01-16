/**
 * Copyright (c) 2014 Gdansk University of Technology
 * Copyright (c) 2014 Marcel Schally-Kacprzak
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
package pl.gda.pg.eti.kernelhive.common.validation;

import java.io.File;

import pl.gda.pg.eti.kernelhive.common.validation.ValidationResult.ValidationResultType;

public class ResourcePathValidator {

	public static ValidationResult validateDirectory(String dirPath) {
		ValidationResult vr = null;
		File f = new File(dirPath);
		if (f.exists() && f.isDirectory()) {
			vr = new ValidationResult("OK", ValidationResultType.VALID);
		} else {
			vr = new ValidationResult("NOT OK", ValidationResultType.INVALID);
		}

		return vr;
	}
	
	public static ValidationResult validateFile(String filePath){
		ValidationResult vr = null;
		File f = new File(filePath);
		if(f.exists() && f.isFile()){
			vr = new ValidationResult("OK", ValidationResultType.VALID);
		} else {
			vr = new ValidationResult("NOT OK", ValidationResultType.INVALID);
		}
		return vr;
	}
	
	public static ValidationResult validateFileNotExists(String filePath){
		ValidationResult vr = null;
		File f = new File(filePath);
		if(!f.exists()){
			vr = new ValidationResult("OK", ValidationResultType.VALID);
		} else{
			vr = new ValidationResult("NOT OK", ValidationResultType.INVALID);
		}
		return vr;
	}

}