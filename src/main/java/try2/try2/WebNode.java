package try2.try2;
import java.util.ArrayList;

public class WebNode {

	public WebNode parent;
	public ArrayList<WebNode> children;
	public WebPage webPage;
	public double nodeScore;
	
	public WebNode(WebPage webPage) {
		this.webPage=webPage;
		this.children=new ArrayList<WebNode>();
	}
	
	public void setNodeScore(ArrayList<Keyword> keywords) throws Exception {
		//����ۤv��score
		webPage.setScore(keywords);
		this.nodeScore= webPage.score;
		//�A��Ĥl��score
		for(WebNode child: children) {
			this.nodeScore+=child.nodeScore;
		}
	}
	
	public void addChild(WebNode child) {
		this.children.add(child);
		//WebNode��p�Ī��a�����V�o��
		child.parent=this;
	}
	
	public int getDepth() {
		//�⦳�X�ӯ���
		int retVal=0;
		WebNode currNode=this;
		
		while(currNode.parent!=null) {
			retVal++;
			currNode= currNode.parent;
		}
		return retVal;
	}
	
}
