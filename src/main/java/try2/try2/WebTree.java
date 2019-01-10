package try2.try2;

import java.util.ArrayList;

public class WebTree {

	public WebNode root;

	public WebTree(WebPage rootPage) {
		// �u�ݫؤ@��root(�]�����O�]�t�ܦh�N�l�]����node)
		this.root = new WebNode(rootPage);
	}

	public void calPostOrderScore(ArrayList<Keyword> keywords) throws Exception {
		setPostOrderScore(root, keywords);
	}

	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws Exception {
		// ����Ĥl��score
		for (WebNode child : startNode.children) {
			setPostOrderScore(child, keywords);
		}
		// �A��ۤv
		startNode.setNodeScore(keywords);
	}

	public void PrintTree() {
		printTree(root);
	}

	private void PrintTree(WebNode startNode) {
		
		for (int i = 0; i < startNode.getDepth(); i++) {
			System.out.print("\t");
		}
		
		if (startNode.children.isEmpty()) {  //�S�p��
			System.out.println("(" + startNode.nodeScore + ")");
		}else{
			System.out.println("(" + startNode.nodeScore);
			for (WebNode child : startNode.children) {
				printTree(child);
			}
			for (int i = 0; i < startNode.getDepth(); i++) {
				System.out.print("\t");
			}
			System.out.println(")");
		}
	}
	
	public void printTree(WebNode startNode) {
		System.out.println(startNode.nodeScore);
	}
}
