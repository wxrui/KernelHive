/**
 * Copyright (c) 2014 Gdansk University of Technology
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
package pl.gda.pg.eti.kernelhive.gui.monitoring;

import pl.gda.pg.eti.kernelhive.common.monitoring.service.MonitoredEntity;
import pl.gda.pg.eti.kernelhive.common.monitoring.service.MonitoredEntityType;

public class MonitoredEntityBuilder {

	MonitoredEntity entity;

	public MonitoredEntityBuilder() {
		entity = new MonitoredEntity();
	}

	public MonitoredEntityBuilder setType(MonitoredEntityType type) {
		entity.setType(type);
		return this;
	}

	public MonitoredEntityBuilder setId(int id) {
		entity.setId(id);
		return this;
	}

	public MonitoredEntity get() {
		return entity;
	}

	public MonitoredEntityBuilder setUnitId(int unitId) {
		entity.setUnitId(unitId);
		return this;
	}

	public MonitoredEntityBuilder setClusterId(int clusterId) {
		entity.setClusterId(clusterId);
		return this;
	}

	public MonitoredEntityBuilder setDeviceId(int deviceId) {
		entity.setDeviceId(deviceId);
		return this;
	}
}
