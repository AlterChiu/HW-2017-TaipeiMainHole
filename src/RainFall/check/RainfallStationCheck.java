package RainFall.check;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.TreeMap;

import usualTool.AtCommonMath;
import usualTool.AtFileReader;
import usualTool.AtTreeFunction;
import usualTool.TimeTranslate;

public class RainfallStationCheck {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		TimeTranslate tt = new TimeTranslate();
		double[] hourList = { 0, 67.5, 66.5, 61.5, 96.5, 133, 12.5, 150, 324, 172.5, 177, 130.5, 101 };
		double[] minList = { 0, 25.5, 20.5, 23.5, 31.5, 34, 44., 56.5, 78, 48.5, 40, 29, 29 };
		double[] dayList = { 0, 260, 256.5, 174.5, 291.5, 547.5, 777, 1255, 1157, 1187, 7735, 954, 1043.5 };
		
		ArrayList<String> fileError = new ArrayList<String>();
		ArrayList<String> yearError = new ArrayList<String>();
		ArrayList<String> monthError = new ArrayList<String>();
		ArrayList<String> dayError = new ArrayList<String>();
		

		String fileAdd = "D:\\TestFile\\RainFallStation\\eachStation_original\\";
		String[] fileList = new File(fileAdd).list();
		for (String file : fileList) {
			String content[][] = new AtFileReader(fileAdd + file).getCsv();

			// create year tree
			TreeMap<Integer, ArrayList<String[]>> yearTree = new TreeMap<Integer, ArrayList<String[]>>();
			try {
				for (String[] line : content) {
					for (int year = 2006; year <= 2017; year++) {
						if (year == Integer.parseInt((tt.StringGetSelected(line[0], "yyyyMMddHHmm", "yyyy")))) {
							yearTree = new AtTreeFunction<Integer, String[]>(yearTree).checkTree(year, line);
						}
					}
				}
			} catch (Exception e) {
				fileError.add(file);
			}

			// run Year
			for (int year = 2006; year <= 2017; year++) {
				try{
				content = yearTree.get(year).parallelStream().toArray(String[][]::new);
				TreeMap<Integer, ArrayList<String[]>> monthTree = new TreeMap<Integer, ArrayList<String[]>>();

				for (String[] line : content) {
					// create month tree
					for (int month = 1; month <= 12; month++) {
						if (month == Integer.parseInt((tt.StringGetSelected(line[0], "yyyyMMddHHmm", "MM")))) {
							monthTree = new AtTreeFunction<Integer, String[]>(monthTree).checkTree(month, line);
							if (Double.parseDouble(line[1]) > minList[month]) {
								System.out.println(file + "\t" + line[0] + "\t" + line[1]);
							}
						}
					}
				}

				// run month
				for (int month = 1; month <= 12; month++) {
					try{
					content = monthTree.get(month).parallelStream().toArray(String[][]::new);
					TreeMap<Integer, ArrayList<Double>> dayDoubleTree = new TreeMap<Integer, ArrayList<Double>>();
					TreeMap<Integer, ArrayList<String[]>> dayTree = new TreeMap<Integer, ArrayList<String[]>>();

					for (String[] line : content) {
						// create day tree
						for (int day = 1; day <= 31; day++) {
							if (day == Integer.parseInt((tt.StringGetSelected(line[0], "yyyyMMddHHmm", "dd")))) {
								dayDoubleTree = new AtTreeFunction<Integer, Double>(dayDoubleTree).checkTree(day,
										Double.parseDouble(line[1]));
							}
						}
					}
					// run day
					for (int day = 1; day <= 31; day++) {
						if (new AtCommonMath(dayDoubleTree.get(day).stream().mapToDouble(Double::doubleValue).toArray())
								.getSum() > dayList[month]) {
							System.out.println(file + "\t" + year + "\t" + month + "\t" + day);
						}

						TreeMap<Integer, ArrayList<Double>> hourTree = new TreeMap<Integer, ArrayList<Double>>();
						content = dayTree.get(day).parallelStream().toArray(String[][]::new);

						for (String[] line : content) {
							// create day tree
							for (int hour = 0; day <= 23; day++) {
								if (hour == Integer.parseInt((tt.StringGetSelected(line[0], "yyyyMMddHHmm", "HH")))) {
									hourTree = new AtTreeFunction<Integer, Double>(hourTree).checkTree(hour,
											Double.parseDouble(line[1]));
								}
							}
						}

						// run hour
						for (int hour = 0; hour <= 23; hour++) {
							if (new AtCommonMath(hourTree.get(hour).stream().mapToDouble(Double::doubleValue).toArray())
									.getSum() > hourList[month]) {
								System.out.println(file + "\t" + year + "\t" + month + "\t" + day + "\t" + hour);
							}
						}

					}
				}catch(Exception e){
					monthError.add(file + "\t" + year + "\t" + month);
				}
			}}catch(Exception e){
					yearError.add(file + "\t" + year);
				}
			}

		}
	}
}
