package CreateChart;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.TreeMap;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import CreateChart.Setting.StartXYPlotting;
import usualTool.AtFileReader;
import usualTool.AtFileWriter;
import usualTool.AtTreeMaker;
import usualTool.FileFunction;
import usualTool.TimeTranslate;

public class RunChart {

	public static void main(String[] args) throws IOException, NumberFormatException, ParseException {
		// TODO Auto-generated method stub
		// base function
		FileFunction ff = new FileFunction();
		TimeTranslate tt = new TimeTranslate();
		String[] lineName = { "uper", "original", "bottom" };

		// standar list
		String[][] stdList = new AtFileReader("D:\\TestFile\\TaipeiMainhole\\ID_std_unit.csv").getContent("\t");
		TreeMap<String, String> stdTree = new AtTreeMaker(stdList, 0).getSingleTree(1);

		// folder list
		String folderAdd = "D:\\TestFile\\TaipeiMainhole\\Delta\\";
		String folderList[] = new File(folderAdd).list();

		// svae location
		String saveAdd = "D:\\TestFile\\TaipeiMainhole\\pictureShow\\";

		// error location
		String errorAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\errorData\\";

		// event list
		String[][] eventList = new AtFileReader("D:\\TestFile\\TaipeiMainhole\\event\\ID_events.csv").getContent("\t");
		TreeMap<String, String> eventTree = new AtTreeMaker(eventList, 0).getSingleTree(1);

		// Go FileList
		for (String folder : folderList) {

			String fileAdd = folderAdd + folder + "\\";
			String fileList[] = new File(fileAdd).list();
			// Create folder
			ff.newFolder(saveAdd + folder);
			ArrayList<String[]> standarUp = new ArrayList<String[]>();
			ArrayList<String[]> standarDown = new ArrayList<String[]>();

			for (String file : fileList) {

				try {
					// read file check std
					String[][] content = new AtFileReader(fileAdd + file).getCsv();
					DataCheck dataCheck = new DataCheck(content, stdTree.get(folder));
					TreeMap<Integer, String[][]> plotContent = dataCheck.getDataCollection();
					standarUp = dataCheck.getStandarUp(standarUp);
					standarDown = dataCheck.getStandarDown(standarDown);
					String[][] standatContent = plotContent.get(1);

					// check persantage
					double persantage = ((double) standatContent.length) / content.length;
					if (persantage >= 0.5) {
						// magical error i can't fix
						String fileName;
						if (eventTree.get(file) == null) {
							fileName = "20170613豪雨";
						} else {
							fileName = eventTree.get(file);
						}

						// plotting
						StartXYPlotting plotting = new StartXYPlotting();
						plotting.settingData(plotContent, lineName);
						plotting.settingChart(folder + "__" + fileName, "time", "value");
						plotting.settingSaveingLocation(saveAdd + folder + "\\" + fileName + ".jpg");
						plotting.Start();

						// outPut in standar content
						ff.newFolder("D:\\TestFile\\TaipeiMainhole\\eachMainhole\\underStanderValue\\" + folder);
						new AtFileWriter(standatContent,
								"D:\\TestFile\\TaipeiMainhole\\eachMainhole\\underStanderValue\\" + folder + "\\"
										+ fileName + ".csv").csvWriter();
						System.out.println("end  \t" + folder + "___" + file);
					} else {
						System.out.println("data uden 50% \t" + folder + " ___" + file);
					}

				} catch (Exception e) {
					System.out.println("error when \t" + folder + " ___ " + file);
					// output over and lower std 90% value
				}

				new AtFileWriter(standarDown.parallelStream().toArray(String[][]::new),
						errorAdd + "lowerStandar\\" + folder + ".csv").csvWriter();
				new AtFileWriter(standarUp.parallelStream().toArray(String[][]::new),
						errorAdd + "overStandar\\" + folder + ".csv").csvWriter();

			}

		}
	}
}