package RainFall.check.toMainholl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import usualTool.AtFileReader;
import usualTool.AtTreeMaker;
import usualTool.TimeTranslate;

public class chekMainholl {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		TimeTranslate tt = new TimeTranslate();
		// rainfall usage station
		ArrayList<String> stationList = new ArrayList<String>(
				Arrays.asList(new AtFileReader("D:\\TestFile\\RainFallStation\\雨量站清單.txt").getContain()));
		// loading file
		String rainFileAdd = "D:\\TestFile\\RainFallStation\\eachStation_delta\\";
		String levelFileAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\underStanderValue\\";

		String[] rainFileList = new File(rainFileAdd).list();
		String[] levelFileList = new File(levelFileAdd).list();

		// level Line
		for (String level : levelFileList) {
			// value List
			

			String levelContent[][] = new AtFileReader(levelFileAdd + level).getCsv();
			TreeMap<String, String> levelTree = new AtTreeMaker(levelContent, 0).getSingleTree(1);
			String levelKeys[] = levelTree.keySet().parallelStream().toArray(String[]::new);

			// outPut first column
			System.out.print(level.split(".csv")[0]);
			// rain Line
			for (String rain : rainFileList) {
				if (stationList.contains(rain.split(".csv")[0])) {
					System.out.print("\t" + rain.split(".csv")[0] + "_");
					String rainContent[][] = new AtFileReader(rainFileAdd + rain).getCsv();
					TreeMap<String, String> rainTree = new AtTreeMaker(rainContent, 0).getSingleTree(1);
					double maxCor = 0;
					double delayTime = -1;

					// delay loop
					for (int delay = 0; delay < 6; delay++) {
						ArrayList<Double> rainValueList = new ArrayList<Double>();
						ArrayList<Double> levelValueList = new ArrayList<Double>();
						// Check value isn't exist
						for (int time = 0; time < levelKeys.length; time++) {
							String keyTime = tt.milliToDate(
									(tt.StringToLong(levelKeys[time], "yyyyMMddHHmm") - 600000 * delay),
									"yyyyMMddHHmm");
							if (rainTree.containsKey(keyTime) ) {
								// System.out.println(keyTime + "\t"+rainTree.get(keyTime));
								
								rainValueList.add(Double.parseDouble(rainTree.get(keyTime)));
								levelValueList.add(Double.parseDouble(levelTree.get( levelKeys[time])));
								/**
								try {
									rainValueList.add(Double.parseDouble(rainTree.get(levelKeys[time])));
								} catch (Exception e) {
									rainValueList.add(0.0);
								}
								try {
									levelValueList.add(Double.parseDouble(levelTree.get(keyTime)));
								} catch (Exception e) {
									levelValueList.add(0.0);
								}
								*/
							}
						}
						double corValue = new PearsonsCorrelation().correlation(
								rainValueList.stream().mapToDouble(Double::doubleValue).toArray(),
								levelValueList.stream().mapToDouble(Double::doubleValue).toArray());
						if (corValue > maxCor) {
							maxCor = corValue;
							delayTime = delay;
						}
					}
					System.out.print(delayTime + "_" +new BigDecimal( maxCor).setScale(3, BigDecimal.ROUND_HALF_UP).toString());
				}

			}
			System.out.println();

		}

	}

}
