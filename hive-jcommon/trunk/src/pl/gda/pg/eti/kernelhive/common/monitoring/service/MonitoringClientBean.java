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
package pl.gda.pg.eti.kernelhive.common.monitoring.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.4-b01 Generated
 * source version: 2.2
 *
 */
@WebService(name = "MonitoringClientBean", targetNamespace = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/")
@XmlSeeAlso({
	ObjectFactory.class
})
public interface MonitoringClientBean {

	/**
	 *
	 * @return returns
	 * java.util.List<pl.gda.pg.eti.kernelhive.common.monitoring.service.ClusterDefinition>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getClusters", targetNamespace = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/", className = "pl.gda.pg.eti.kernelhive.common.monitoring.service.GetClusters")
	@ResponseWrapper(localName = "getClustersResponse", targetNamespace = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/", className = "pl.gda.pg.eti.kernelhive.common.monitoring.service.GetClustersResponse")
	@Action(input = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/MonitoringClientBean/getClustersRequest", output = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/MonitoringClientBean/getClustersResponse")
	public List<ClusterDefinition> getClusters();

	/**
	 *
	 * @return returns
	 * java.util.List<pl.gda.pg.eti.kernelhive.common.monitoring.service.UnitDefinition>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getUnits", targetNamespace = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/", className = "pl.gda.pg.eti.kernelhive.common.monitoring.service.GetUnits")
	@ResponseWrapper(localName = "getUnitsResponse", targetNamespace = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/", className = "pl.gda.pg.eti.kernelhive.common.monitoring.service.GetUnitsResponse")
	@Action(input = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/MonitoringClientBean/getUnitsRequest", output = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/MonitoringClientBean/getUnitsResponse")
	public List<UnitDefinition> getUnits();

	/**
	 *
	 * @param arg0
	 * @return returns
	 * java.util.List<pl.gda.pg.eti.kernelhive.common.monitoring.service.UnitDefinition>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getUnitsForCluster", targetNamespace = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/", className = "pl.gda.pg.eti.kernelhive.common.monitoring.service.GetUnitsForCluster")
	@ResponseWrapper(localName = "getUnitsForClusterResponse", targetNamespace = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/", className = "pl.gda.pg.eti.kernelhive.common.monitoring.service.GetUnitsForClusterResponse")
	@Action(input = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/MonitoringClientBean/getUnitsForClusterRequest", output = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/MonitoringClientBean/getUnitsForClusterResponse")
	public List<UnitDefinition> getUnitsForCluster(
			@WebParam(name = "arg0", targetNamespace = "") int arg0);

	/**
	 *
	 * @param arg0
	 * @return returns
	 * java.util.List<pl.gda.pg.eti.kernelhive.common.monitoring.service.PreviewObject>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getPreviewData", targetNamespace = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/", className = "pl.gda.pg.eti.kernelhive.common.monitoring.service.GetPreviewData")
	@ResponseWrapper(localName = "getPreviewDataResponse", targetNamespace = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/", className = "pl.gda.pg.eti.kernelhive.common.monitoring.service.GetPreviewDataResponse")
	@Action(input = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/MonitoringClientBean/getPreviewDataRequest", output = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/MonitoringClientBean/getPreviewDataResponse")
	public List<PreviewObject> getPreviewData(
			@WebParam(name = "arg0", targetNamespace = "") int arg0);

	/**
	 *
	 * @param arg1
	 * @param arg0
	 * @return returns
	 * java.util.List<pl.gda.pg.eti.kernelhive.common.monitoring.service.DeviceDefinition>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getDevices", targetNamespace = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/", className = "pl.gda.pg.eti.kernelhive.common.monitoring.service.GetDevices")
	@ResponseWrapper(localName = "getDevicesResponse", targetNamespace = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/", className = "pl.gda.pg.eti.kernelhive.common.monitoring.service.GetDevicesResponse")
	@Action(input = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/MonitoringClientBean/getDevicesRequest", output = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/MonitoringClientBean/getDevicesResponse")
	public List<DeviceDefinition> getDevices(
			@WebParam(name = "arg0", targetNamespace = "") int arg0,
			@WebParam(name = "arg1", targetNamespace = "") int arg1);

	/**
	 *
	 * @return returns
	 * java.util.List<pl.gda.pg.eti.kernelhive.common.monitoring.service.DeviceDefinition>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getAllDevices", targetNamespace = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/", className = "pl.gda.pg.eti.kernelhive.common.monitoring.service.GetAllDevices")
	@ResponseWrapper(localName = "getAllDevicesResponse", targetNamespace = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/", className = "pl.gda.pg.eti.kernelhive.common.monitoring.service.GetAllDevicesResponse")
	@Action(input = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/MonitoringClientBean/getAllDevicesRequest", output = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/MonitoringClientBean/getAllDevicesResponse")
	public List<DeviceDefinition> getAllDevices();

	/**
	 *
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getGraphPath", targetNamespace = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/", className = "pl.gda.pg.eti.kernelhive.common.monitoring.service.GetGraphPath")
	@ResponseWrapper(localName = "getGraphPathResponse", targetNamespace = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/", className = "pl.gda.pg.eti.kernelhive.common.monitoring.service.GetGraphPathResponse")
	@Action(input = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/MonitoringClientBean/getGraphPathRequest", output = "http://monitoring.engine.kernelhive.eti.pg.gda.pl/MonitoringClientBean/getGraphPathResponse")
	public String getGraphPath(
			@WebParam(name = "arg0", targetNamespace = "") MonitoredEntity arg0);
}
