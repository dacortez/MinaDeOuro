package dacortez.minaDeOuro;

import java.util.Stack;

public class Solution {
	private int cost;
	private Stack<Node> stack;
	
	public int getCost() {
		return cost;
	}
	
	public Stack<Node> getStack() {
		return stack;
	}
	
	public Solution(Node finalNode) {
		if (finalNode != null) {
			cost = -finalNode.getPathCost();
			stack = new Stack<Node>();
			setStack(finalNode);
		}
	}

	private void setStack(Node finalNode) {
		Node node = finalNode;
		while (node != null) {
			stack.push(node);
			node = node.getParentNode();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(cost).append(" pontos\n");
		for (int i = stack.size() - 2; i >= 0; i--) {
			Node node = stack.get(i);
			sb.append(node.getAction());
			if (i % 31 == 0) 
				sb.append('\n');
		}
		return sb.toString();
	}
}
