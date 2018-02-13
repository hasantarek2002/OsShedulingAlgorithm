package FileTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class OsFile {

	private static String OUTPUT_FILENAME = "textfolder//OsOutput.txt";
	private static String INPUT_FILENAME = "textfolder//OsInput.txt";

	private static ArrayList<String> arr;

	public static void main(String[] args) {

		arr = readFromFile_BufferReader();
		int[] process = new int[arr.size() - 1];
		int[] art = new int[arr.size() - 1];
		int[] bt = new int[arr.size() - 1];

		for (int i = 0; i < arr.size(); i++) {
			if (i == 0)
				continue;
			else {
				String temp = arr.get(i).toString();
				System.out.println(temp);
				String[] line = temp.split(",");

				process[i - 1] = Integer.parseInt(line[0]);
				art[i - 1] = Integer.parseInt(line[1]);
				bt[i - 1] = Integer.parseInt(line[2]);

			}
		}

		for (int i = 0; i < 3; i++) {
			System.out.println(process[i] + "   " + art[i] + "  " + bt[i]);

		}
		
		writeToFile_BufferWriter(process, art, bt);
	}

	public static ArrayList<String> readFromFile_BufferReader() {

		BufferedReader br = null;
		FileReader fr = null;
		ArrayList<String> lines = new ArrayList<String>();

		try {

			fr = new FileReader(INPUT_FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			// br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
				lines.add(sCurrentLine);
			}

			if (br != null)
				br.close();

			if (fr != null)
				fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return lines;
		}
	}

	public static void writeToFile_BufferWriter(int[] process, int[] art, int[] bt) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			// open filewriter as append mode
			fw = new FileWriter(OUTPUT_FILENAME, true);
			bw = new BufferedWriter(fw);

			for (int i = 0; i < 3; i++) {
				bw.write(process[i] + " " + art[i] + " " + bt[i]+"\n");
				
			}

			System.out.println("Write Done");

			if (bw != null)
				bw.close();

			if (fw != null)
				fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
