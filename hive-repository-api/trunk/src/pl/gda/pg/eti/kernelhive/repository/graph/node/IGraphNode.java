package pl.gda.pg.eti.kernelhive.repository.graph.node;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import pl.gda.pg.eti.kernelhive.common.validation.ValidationResult;
import pl.gda.pg.eti.kernelhive.repository.graph.node.type.GraphNodeType;

public interface IGraphNode extends Serializable {

	String getNodeId();

	String getName();

	void setName(String name);

	GraphNodeType getType();

	List<IGraphNode> getFollowingNodes();

	boolean canAddFollowingNode(IGraphNode node);

	boolean addFollowingNode(IGraphNode node);

	boolean removeFollowingNode(IGraphNode node);

	List<IGraphNode> getPreviousNodes();

	boolean canAddPreviousNode(IGraphNode node);

	boolean addPreviousNode(IGraphNode node);

	boolean removePreviousNode(IGraphNode node);

	IGraphNode getParentNode();

	void setParentNode(IGraphNode node);

	List<IGraphNode> getChildrenNodes();

	boolean addChildNode(IGraphNode node);

	boolean removeChildNode(IGraphNode node);

	boolean canAddChildNode(IGraphNode node);

	Map<String, Object> getProperties();

	void setProperties(Map<String, Object> properties);

	List<ValidationResult> validate();
}
