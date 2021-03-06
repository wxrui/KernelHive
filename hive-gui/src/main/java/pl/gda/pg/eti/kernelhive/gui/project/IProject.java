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
package pl.gda.pg.eti.kernelhive.gui.project;

import java.io.File;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;

import pl.gda.pg.eti.kernelhive.common.graph.node.GUIGraphNodeDecorator;

public interface IProject {
	/**
	 * gets project file
	 * @return {@link File}
	 */
	File getProjectFile();
	/**
	 * sets project file
	 * @param file {@link File}
	 */
	void setProjectFile(File file);
	/**
	 * 
	 * @return project name
	 */
	String getProjectName();
	/**
	 * 
	 * @return project directory
	 */
	File getProjectDirectory();
	/**
	 * sets new project name
	 * @param name
	 */
	void setProjectName(String name);
	/**
	 * sets new project directory
	 * @param dir
	 */
	void setProjectDirectory(File dir);
	/**
	 * inits the project
	 * @throws ConfigurationException
	 */
	void initProject() throws ConfigurationException;
	/**
	 * saves project configuration to default xml file
	 * @return true if successful, false if not.
	 * @throws ConfigurationException
	 */
	void save() throws ConfigurationException;
	
	/**
	 * saves project configuration to given file
	 * @param file File to save
	 * @throws ConfigurationException
	 */
	void save(File file) throws ConfigurationException;
	/**
	 * loads project configuration from default xml file
	 */
	void load() throws ConfigurationException;
	
	/**
	 * loads project from given xml file
	 * @param file File to load
	 */
	void load(File file) throws ConfigurationException;
	/**
	 * 
	 * @return list of project nodes
	 */
	List<GUIGraphNodeDecorator> getProjectNodes();
	/**
	 * sets list of project nodes
	 * @param nodes List of project nodes
	 */
	void setProjectNodes(List<GUIGraphNodeDecorator> nodes);
	/**
	 * add new project node
	 * @param node new project node
	 */
	void addProjectNode(GUIGraphNodeDecorator node);
	/**
	 * removes the selected project node
	 * @param node project node to be removed
	 * @param removeFromDisc boolean indicating whether to delete corresponding files from disc
	 */
	void removeProjectNode(GUIGraphNodeDecorator node, boolean removeFromDisc);
}
