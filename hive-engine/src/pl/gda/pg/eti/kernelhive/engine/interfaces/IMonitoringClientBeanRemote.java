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
package pl.gda.pg.eti.kernelhive.engine.interfaces;

import java.util.List;
import javax.ejb.Remote;
import pl.gda.pg.eti.kernelhive.common.monitoring.service.PreviewObject;
import pl.gda.pg.eti.kernelhive.engine.monitoring.dao.ClusterDefinition;
import pl.gda.pg.eti.kernelhive.engine.monitoring.dao.DeviceDefinition;
import pl.gda.pg.eti.kernelhive.engine.monitoring.MonitoredEntity;
import pl.gda.pg.eti.kernelhive.engine.monitoring.dao.UnitDefinition;

@Remote
public interface IMonitoringClientBeanRemote {

	String getGraphPath(MonitoredEntity entity);

	List<UnitDefinition> getUnits();

	List<UnitDefinition> getUnitsForCluster(int clusterId);

	List<ClusterDefinition> getClusters();

	List<DeviceDefinition> getDevices(int clusterId, int unitId);

	List<DeviceDefinition> getAllDevices();

	List<PreviewObject> getPreviewData(int workflowId);
}