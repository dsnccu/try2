package try2.try2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
	public static void main(String[] args) throws IOException {
		
		
		System.out.println("Please Enter Your Keyword: ");
		Scanner sc = new Scanner(System.in);
		
		
		while(sc.hasNextLine()) {
			ArrayList<Keyword> keywords=new ArrayList<Keyword>();
			String keyword = sc.next();
			keywords.add(new Keyword(keyword,5));
			keywords.add(new Keyword("工作",3));
			keywords.add(new Keyword("實習",3));
			keywords.add(new Keyword("政大",2));
			keywords.add(new Keyword("徵才",2));

			
			GoogleQuery googleQuery = new GoogleQuery(keywords.get(0).name);
			
			//搜尋結果
			ArrayList<String> searchR = new ArrayList<String>();
			googleQuery.query();
			searchR = googleQuery.getSearchR();
			
			for(int a=0;a<searchR.size();a++) {
			
		    System.out.println(a+": " + searchR.get(a));

			
			//找小孩
			String url = searchR.get(a);
			int x =url.indexOf("//")+2;
			int y=url.indexOf("/",x);
			String shorturl=url.substring(0,y);
			System.out.println("SHORT: "+shorturl);
			Document doc = null;
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e1) {
				//e1.printStackTrace();           
				//System.out.println("Don't have any childen!");
			}
			if(doc!=null) {
			Element body = doc.body();
			Elements es = body.select("a");
			ArrayList<String> children=new ArrayList<String>();
			for (Iterator it = es.iterator();it.hasNext();) {
				Element e = (Element) it.next();
				String u=e.attr("href");
				if(u.startsWith("http")) {
					//System.out.println(u);
					children.add(u);
				}else if(u.startsWith("/")) {
					u=shorturl+u;
					//System.out.println(u);
					children.add(u);
				}
			}
			
			
			
			
			WebPage rootPage=new WebPage(url);
			WebTree tree=new WebTree(rootPage);
			
			
			//tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Welcome.html","Project")));
			int i=0;
			while(i<children.size()) {
				tree.root.addChild(new WebNode(new WebPage(children.get(i))));
				i++;
			}
//			int j=0;
//			while(j<tree.root.children.size()) {
//				System.out.println(tree.root.children.get(j));
//				j++;
//			}
			
			
//			
//			Scanner sc=new Scanner(System.in);
//			
//			while(sc.hasNextLine()) {
//				//先輸入keyword個數
//				int numOfKeywords=sc.nextInt();
//				ArrayList<Keyword> keywords=new ArrayList<Keyword>();
//				
//				//輸入的keyword放入array list
//				for (int j=0; j<numOfKeywords; j++) {
//					String name=sc.next();
//					double weight=sc.nextDouble();
//					Keyword k=new Keyword(name,weight);
//					keywords.add(k);
//	     		}
//				
//				try {
//					tree.calPostOrderScore(keywords);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					//e.printStackTrace();
//				}
//				//tree.printTree();
				
			
			//System.out.println(""+a+": ");
			 tree.root.calNodeScore(keywords);
			
			}
			 System.out.println("");
			}
			}
			sc.close();
			

		}

		//scanner.close();
	}


