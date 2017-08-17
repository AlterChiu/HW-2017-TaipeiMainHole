package read.Large;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import usualTool.AtArraySort;
import usualTool.AtFileReader;
import usualTool.AtFileWriter;
import usualTool.TimeTranslate;

public class DividInToEach {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		TimeTranslate tt = new TimeTranslate();
		String fileAdd = "D:\\TestFile\\TaipeiMainhole\\original\\";
		String saveAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\orderDateEach\\";
		String StationList[] = new AtFileReader("D:\\TestFile\\TaipeiMainhole\\stationIDList.csv").getContain();

		for (String station : StationList) {
			
			ArrayList<String[]> IDvalue = new ArrayList<String[]>();

			String fileList[] = new File(fileAdd).list();
			for (String l : fileList) {
				int timeIndex =2;
				if(l.contains("2015") || l.contains("2016") || l.contains("2017")){
					timeIndex = 1;
				}
				FileReader tempFile = new FileReader(fileAdd + l);
				BufferedReader br = new BufferedReader(tempFile);
				String line = "";

				try {
					while ((line = br.readLine()) != null) {
						if (line.contains(station)) {
							String[] lineSplit = line.split(",");
							String[] outString = new String[2];
							String time;
							try {
								time = tt.StringGetSelected(lineSplit[timeIndex], "yyyyMMddHHmmss", " yyyyMMddHHmm");
								time = time.substring(0, time.length() - 1) + "0";
							} catch (Exception e) {
								time = lineSplit[timeIndex].split("\"")[1].trim();
								time = time.substring(0, time.length()-1) + "0";
							}
							
							outString[0] = time;
							outString[1] = line.split(",")[3];
							IDvalue.add(outString);
						}
					}
				} catch (Exception e) {
					System.out.println(e);
				}

			}
			String[][] outPut = IDvalue.parallelStream().toArray(String[][]::new);
			
			outPut = new AtArraySort<String>(outPut, 0).sorted().getSorted();
			System.out.println(outPut[0][0]);
			new AtFileWriter(outPut, saveAdd + station+ ".csv").csvWriter();
			System.out.println("end   " + station );
		}
	}
}
