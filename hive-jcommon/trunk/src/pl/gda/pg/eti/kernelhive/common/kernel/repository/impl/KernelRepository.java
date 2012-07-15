package pl.gda.pg.eti.kernelhive.common.kernel.repository.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;

import pl.gda.pg.eti.kernelhive.common.graph.node.GraphNodeType;
import pl.gda.pg.eti.kernelhive.common.kernel.repository.IKernelRepository;
import pl.gda.pg.eti.kernelhive.common.kernel.repository.KernelRepositoryEntry;

/**
 * 
 * @author mschally
 *
 */
public class KernelRepository implements IKernelRepository {

	private static String ROOT_NODE = "kh:repository";
	private static String ENTRY_NODE = "kh:entry";
	private static String ENTRY_NODE_TYPE_ATTRIBUTE = "type";
	private static String KERNEL_NODE = "kh:kernel";
	private static String KERNEL_NODE_NAME_ATTRIBUTE = "name";
	private static String KERNEL_NODE_SRC_ATTRIBUTE = "src";
	
	private XMLConfiguration config;
	private URL resource;
	
	public KernelRepository(URL resource) {
		config = new XMLConfiguration();
		this.resource = resource;
	}
	
	@Override
	public List<KernelRepositoryEntry> getEntries()
			throws ConfigurationException {
		config.load(resource);
		
		List<KernelRepositoryEntry> entries = new ArrayList<KernelRepositoryEntry>();
		
		List<ConfigurationNode> entryNodes = config.getRoot().getChildren(ENTRY_NODE);
		for(ConfigurationNode node : entryNodes){
			entries.add(getEntryFromConfigurationNode(node));
		}		
		return entries;
	}

	@Override
	public KernelRepositoryEntry getEntryForGraphNodeType(GraphNodeType type)
			throws ConfigurationException {
		config.load(resource);
		
		List<ConfigurationNode> entryNodes = config.getRootNode().getChildren(ENTRY_NODE);
		for(ConfigurationNode node : entryNodes){
			List<ConfigurationNode> typeAttrList = node.getAttributes(ENTRY_NODE_TYPE_ATTRIBUTE);
			
			if(typeAttrList.size()>0){
				String val = (String) typeAttrList.get(0).getValue();
				
				if(type.equals(GraphNodeType.getType(val))){
					return getEntryFromConfigurationNode(node);
				}
			} else{
				throw new ConfigurationException("KH: no required 'type' attribute in <kh:entry> node");
			}
		}
		
		return null;
	}
	
	private KernelRepositoryEntry getEntryFromConfigurationNode(ConfigurationNode node) throws ConfigurationException{
		List<ConfigurationNode> typeAttrList = node.getAttributes(ENTRY_NODE_TYPE_ATTRIBUTE);
		
		if(typeAttrList.size()>0){
			String val = (String) typeAttrList.get(0).getValue();
			List<ConfigurationNode> kernelsList = node.getChildren(KERNEL_NODE);
			Map<String, URL> kernelsMap = new HashMap<String, URL>(kernelsList.size());
			
			for(ConfigurationNode kNode : kernelsList){
				String name;
				URL src;
				
				List<ConfigurationNode> nameAttrList = kNode.getAttributes(KERNEL_NODE_NAME_ATTRIBUTE);
				List<ConfigurationNode> srcAttrList = kNode.getAttributes(KERNEL_NODE_SRC_ATTRIBUTE);
				
				if(nameAttrList.size()>0){
					name = (String) nameAttrList.get(0).getValue();
				} else{
					throw new ConfigurationException("KH: no required 'name' attribute in <kh:kernel> node");
				}
				if(srcAttrList.size()>0){
					src = KernelRepository.class.getResource((String) srcAttrList.get(0).getValue());
					if(src==null){
						throw new ConfigurationException("KH: the resource path: '"+srcAttrList.get(0).getValue()+"' cound not be found");
					}
				} else{
					throw new ConfigurationException("KH: no required 'src' attribute in <kh:kernel> node");
				}
				kernelsMap.put(name, src);
			}
			
			return new KernelRepositoryEntry(GraphNodeType.getType(val), kernelsMap);
		} else{
			throw new ConfigurationException("KH: no required 'type' attribute in <kh:entry> node");
		}
	}
}
