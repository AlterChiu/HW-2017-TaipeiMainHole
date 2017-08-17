package divid.event;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import usualTool.AtFileReader;
import usualTool.AtFileWriter;
import usualTool.FileFunction;
import usualTool.TimeTranslate;

public class DividEachStationToEaven {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		FileFunction ff = new FileFunction();
		TimeTranslate tt = new TimeTranslate();

		ArrayList<String> stationList = new ArrayList<String>(
				Arrays.asList(new AtFileReader("D:\\TestFile\\TaipeiMainhole\\StationID_final.txt").getContain()));

		String fileAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\adjustedValue\\";
		String fileList[] = new File(fileAdd).list();

		String[][] event = new AtFileReader("D:\\TestFile\\TaipeiMainhole\\event\\event.csv").getCsv();

		String saveAdd = "D:\\TestFile\\TaipeiMainhole\\event\\eachEvent\\";

		// Show title
		for (int i = 0; i < event.length; i++) {
			System.out.print("\t" + event[i][1] + event[i][2]);
		}
		System.out.println();

		// Go FileList
		for (String file : fileList) {
			// check isn't in final list
			if (stationList.contains(file.split(".csv")[0])) {

				// Create folder
				String tempSave = saveAdd + file.split(".csv")[0];
				ff.newFolder(tempSave);
				TreeMap<String, ArrayList<String[]>> tree = new TreeMap<String, ArrayList<String[]>>();
				String[][] content = new AtFileReader(fileAdd + file).getCsv();

				System.out.print(file.split(".csv")[0]);

				// go event

				for (int i = 0; i < event.length; i++) {
					ArrayList<String[]> temptList = new ArrayList<String[]>();
					String start = tt.StringGetSelected(event[i][3], "yyyy/MM/dd HH:mm", "yyyyMMddHHmm");
					String end = tt.StringGetSelected(event[i][4], "yyyy/MM/dd HH:mm", "yyyyMMddHHmm");

					// go dataConent
					for (int data = 0; data < content.length; data++) {
						if (Double.parseDouble(content[data][0]) >= Double.parseDouble(start)
								&& Double.parseDouble(content[data][0]) < Double.parseDouble(end)) {
							temptList.add(content[data]);
						}
					}
					String[][] outContent = temptList.parallelStream().toArray(String[][]::new);
					try {
						new AtFileWriter(outContent, tempSave + "\\" + event[i][0]).csvWriter();
						System.out.print("\t" + outContent.length);
					} catch (Exception e) {
						System.out.print("\tNULL");
					}
				}
				// out Put
				System.out.println();
			}

		}

	}
}
