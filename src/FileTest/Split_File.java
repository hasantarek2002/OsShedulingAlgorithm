package FileTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Split_File {

	public String FILENAME = "textFolder\\file.txt";

	public void splitOfLineTest() {

		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			// br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
				String[] line = sCurrentLine.split(",");
				System.out.println(line[2]+" "+line[3]+" "+line[4]);
				
//				System.out.println(line.length);
//				for (String w : line) {
//					System.out.print(w + " ");
//				}
//				System.out.println();
			}

			if (br != null)
				br.close();

			if (fr != null)
				fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
