package dacortez.minaDeOuro;

import java.util.Stack;

public class Solution {
	private Node finalNode;
	private Stack<Node> stack;
	
	public Stack<Node> getStack() {
		return stack;
	}
	
	public Solution(Node finalNode) {
		this.finalNode = finalNode;
		if (finalNode != null)
			setStack();
	}
	
	protected Solution() {
	
	}

	private void setStack() {
		stack = new Stack<Node>();
		Node node = finalNode;
		while (node != null) {
			stack.push(node);
			node = node.getParentNode();
		}
	}
	
	public int getCost() {
		return finalNode.getPathCost();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getCost()).append(" pontos\n");
		for (int i = stack.size() - 2, j = 1; i >= 0; i--, j++) {
			Node node = stack.get(i);
			sb.append(node.getAction());
			if (j % 31 == 0) 
				sb.append('\n');
		}
		return sb.toString();
	}
}
