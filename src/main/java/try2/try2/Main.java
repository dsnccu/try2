//§Ú¬Ouna baby
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
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please Enter Your Keyword: ");
		while (scanner.hasNextLine()) {
			String keyword = scanner.next();
			GoogleQuery googleQuery = new GoogleQuery(keyword);
			ArrayList<String> searchR = new ArrayList<String>();
			googleQuery.query();
			searchR = googleQuery.getSearchR();
			System.out.println("2:" + searchR.get(1));

			String url = searchR.get(1);
			Document doc = null;
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Element body = doc.body();
			Elements es = body.select("a");
			for (Iterator it = es.iterator(); it.hasNext();) {
				Element e = (Element) it.next();

				// e.text()+" "+

				System.out.println(e.attr("href"));
			}

		}

		scanner.close();
	}

};
