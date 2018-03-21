package org.kingqueen.kiinmis.model.eaysui.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Tree
 * @description 功能描述
 * @author 刘洪君
 * @date 2017年12月19日上午10;//17;//56
 * @version V1.0
 */
public class Tree {

	// 节点ID，对加载远程数据很重要。
	private String id;
	// 显示节点文本。
	private String text;
	// 节点状态，'open' 或 // 'closed'，默认;//'open'。如果为'closed'的时候，将不自动展开该节点。
	private String state;
	// 表示该节点是否被选中。
	private boolean  checked;
	//父节点
	private	Tree fatherNode;
	// 一个节点数组声明了若干节点。
	private List<Tree> children = new ArrayList<Tree>();
	
	
	public Tree getFatherNode() {
		return fatherNode;
	}

	public void setFatherNode(Tree fatherNode) {
		this.fatherNode = fatherNode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean  getChecked() {
		return checked;
	}

	public void setChecked(boolean  checked) {
		this.checked = checked;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

}
