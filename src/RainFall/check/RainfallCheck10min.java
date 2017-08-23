package RainFall.check;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.TreeMap;

import usualTool.AtFileReader;
import usualTool.AtFileWriter;
import usualTool.AtTreeFunction;
import usualTool.TimeTranslate;

public class RainfallCheck10min {

	public static void main(String[] args) throws IOException, NumberFormatException, ParseException {
		// TODO Auto-generated method stub

		TimeTranslate tt = new TimeTranslate();
		double[] hourList = { 0, 67.5, 66.5, 61.5, 96.5, 133, 12.5, 150, 324, 172.5, 177, 130.5, 101 };
		double[] minList = { 0, 25.5, 20.5, 23.5, 31.5, 34, 44.5, 56.5, 78, 48.5, 40, 29, 29 };
		double[] dayList = { 0, 260, 256.5, 174.5, 291.5, 547.5, 777, 1255, 1157, 1187, 7735, 954, 1043.5 };


		
		
		String fileAdd = "D:\\TestFile\\RainFallStation\\eachStation_original\\";
		String saveAdd = "D:\\TestFile\\RainFallStation\\eachStation_Adjusted\\";
		
		String[] fileList = new File(fileAdd).list();
		for (String file : fileList) {
			ArrayList<String[]> outList = new ArrayList<String[]>();
			String content[][] = new AtFileReader(fileAdd + file).getCsv();

			for(String[] line : content){
				int month = Integer.parseInt(tt.StringGetSelected(line[0], "yyyyMMddHHmm", "MM"));
				if(Double.parseDouble(line[1])<= minList[month]){
					outList.add(line);
				}
			}
				
			new AtFileWriter(outList.parallelStream().toArray(String[][]::new),saveAdd + file).csvWriter();
				
		}
	}
}
