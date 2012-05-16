package pl.gda.pg.eti.kernelhive.gui.project;

import java.io.File;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;

public interface IProject {

	File getProjectFile();
	
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
	void load();
	
	/**
	 * loads project from given xml file
	 * @param file File to load
	 */
	void load(File file);
	/**
	 * 
	 * @return list of project nodes
	 */
	List<IProjectNode> getProjectNodes();
	/**
	 * sets list of project nodes
	 * @param nodes List of project nodes
	 */
	void setProjectNodes(List<IProjectNode> nodes);
	/**
	 * add new project node
	 * @param node new project node
	 */
	void addProjectNode(IProjectNode node);
	/**
	 * removes the selected project node
	 * @param node project node to be removed
	 * @param removeFromDisc boolean indicating whether to delete corresponding files from disc
	 */
	void removeProjectNode(IProjectNode node, boolean removeFromDisc);
}