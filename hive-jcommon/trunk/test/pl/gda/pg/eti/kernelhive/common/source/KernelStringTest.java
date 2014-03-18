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
package pl.gda.pg.eti.kernelhive.common.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class KernelStringTest {
	
	private KernelString ks;
	private String kernel;
	private String id;

	@Before
	public void setUp() throws Exception {
		id = "test";
		kernel = "test";
		ks = new KernelString(id, kernel);
		ks.getProperties().put("globalSizes", "1 0 0");
		ks.getProperties().put("localSizes", "1 0 0");
		ks.getProperties().put("offsets", "0 0 0");
	}

	@Test
	public void testGetKernel() {
		assertEquals(kernel, ks.getKernel());
	}

	@Test
	public void testGetId() {
		assertEquals(id, ks.getId());
	}
	
	@Test
	public void testGetGlobalSize(){
		assertNotNull(ks.getGlobalSize());
	}
	
	@Test
	public void testGetLocalSize(){
		assertNotNull(ks.getLocalSize());
	}
	
	@Test
	public void testGetOffset(){
		assertNotNull(ks.getOffset());
	}

}
