package read.Large;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class ReadLargeFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileAdd = "C:\\Users\\alter\\Desktop\\TaipeiMainhole\\original\\";
		String saveAdd = "C:\\Users\\alter\\Desktop\\TaipeiMainhole\\";

		HashSet<String> idList = new HashSet<String>();

		String fileList[] = new File(fileAdd).list();
		for (String l : fileList) {
			FileReader tempFile = new FileReader(fileAdd + l);
			BufferedReader br = new BufferedReader(tempFile);
			String line = "";

			try {
				while ((line = br.readLine()) != null) {
					String tempLine[] = line.split(",");
					idList.add(tempLine[1]);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			System.out.println("end " + l);
		}
		FileWriter fw = new FileWriter(saveAdd + "stationIDList.csv");
		String tempIDList[] = idList.parallelStream().toArray(String[]::new);
		for(String d:tempIDList){
			fw.write(d+"\r\n");
		}
		fw.close();
	}

}
