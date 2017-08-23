package RainFall.check;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import usualTool.AtCommonMath;
import usualTool.AtFileReader;
import usualTool.AtFileWriter;
import usualTool.TimeTranslate;

public class RainfallCheckDay {

	public static void main(String[] args) throws IOException, NumberFormatException, ParseException {
		// TODO Auto-generated method stub
		TimeTranslate tt = new TimeTranslate();
		double[] hourList = { 0, 67.5, 66.5, 61.5, 96.5, 133, 122.5, 150, 324, 172.5, 177, 130.5, 101 };
		double[] minList = { 0, 25.5, 20.5, 23.5, 31.5, 34, 44.5, 56.5, 78, 48.5, 40, 29, 29 };
		double[] dayList = { 0, 260, 256.5, 174.5, 291.5, 547.5, 777, 1255, 1157, 1187, 7735, 954, 1043.5 };

		String fileAdd = "D:\\TestFile\\RainFallStation\\eachStation_Adjusted_10min_1\\";
		String saveAdd = "D:\\TestFile\\RainFallStation\\eachStation_Adjusted_hour_2\\";

		String[] fileList = new File(fileAdd).list();
		for (String file : fileList) {
			ArrayList<String[]> outList = new ArrayList<String[]>();
			String content[][] = new AtFileReader(fileAdd + file).getCsv();

			for (int year = 2006; year <= 2017; year++) {
				String yearS = year + "";
				for (int month = 1; month <= 12; month++) {
					String monthS = String.format("%02d", month);
					for (int day = 1; day <= 31; day++) {
						String dayS = String.format("%02d", day);

						String time = yearS + monthS + dayS ;
						ArrayList<Double> hourTemp = new ArrayList<Double>();
						for (String[] line : content) {
							if (line[0].substring(0, 8).equals(time)) {
								hourTemp.add(Double.parseDouble(line[1]));
							}
						}
						if (new AtCommonMath(hourTemp).getSum() > dayList[month]) {
							System.out.println(file + "\t" + year + "\t" + month + "\t" + day );
						}
					}

				}
			}
			System.out.println("done" + file);
		}

	}

}
