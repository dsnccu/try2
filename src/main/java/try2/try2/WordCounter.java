package try2.try2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WordCounter {
	public String urlStr;
	public String content;
	
	public WordCounter(String urlStr) {
		this.urlStr=urlStr;
	}

	private String fetchContent() throws Exception {
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		InputStream in=conn.getInputStream(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		String retVal=""; 
		String line=null; 
		
		while((line=br.readLine())!=null) {
			retVal = retVal+line+"/n";
		}
		
		return retVal;
	}
	

	public double countKeyword(String keyword) throws Exception {
		if(content==null) {
			content=fetchContent();
		}
		
		content=content.toUpperCase(); 
		keyword=keyword.toUpperCase();
		
		int count=0;
		int i = content.indexOf(keyword);
		while(i!=-1) {
			count++;
			content=content.substring(i+keyword.length(), content.length());
			i=content.indexOf(keyword);
		}
		
		return count;
	}
	
	
}

