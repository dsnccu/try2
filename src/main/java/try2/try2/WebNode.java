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
		//System.out.println("node"+nodeScore);
		   //�A��Ĥl��score
		double childscore=0;
		for(WebNode child: children) {
			//System.out.println("child"+child.nodeScore);
			childscore+=child.nodeScore;
		}
		this.nodeScore= webPage.score+childscore;
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
	
	public void calNodeScore(ArrayList<Keyword> keywords) {
		try {
			webPage.setScore(keywords);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		this.nodeScore= webPage.score;
		for(int i=0;i<children.size();i++) {
			nodeScore+=children.get(i).nodeScore;
		}
		System.out.println("node score:"+nodeScore);
	}
	
}
