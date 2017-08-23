package check;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.TreeMap;

import Adjusted.to10Min.Mix10Min;
import check.Limit.CheckUper;
import usualTool.AtFileReader;
import usualTool.AtFileWriter;
import usualTool.AtTreeMaker;

public class RunCheck {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub

		String fileAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\trim\\";
		String[] fileList = new File(fileAdd).list();

		String limitList[][] = new AtFileReader("D:\\TestFile\\TaipeiMainhole\\stationLimit.csv").getCsv(1, 0);
		AtTreeMaker treeMaker = new AtTreeMaker(limitList, 0);
		treeMaker.add("over", 3);
		treeMaker.add("lower", 4);
		TreeMap<String, TreeMap<String, String>> limitTree = treeMaker.getMixTree();

		for (String file : fileList) {
			
			String fileName = file.split(".csv")[0];
			if (limitTree.containsKey(fileName)) {
				TreeMap<String, String> temptTree = limitTree.get(fileName);

				String content[][] = new AtFileReader(fileAdd + file).getCsv();
				String[] missing = new Mix10Min(content, 0, 1).getMissing();
				
				// check the array index of the value column================V==V
				CheckUper check = new CheckUper(content, Double.parseDouble(temptTree.get("over")),
						Double.parseDouble(temptTree.get("lower")), 1);
				

				content = check.getContent();
				content = new Mix10Min(content, 0, 1).getMix();
				
				
				String[][] uper = check.getUper();
				String[][] bottom = check.getBottom();

				new AtFileWriter(content, "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\adjustedValue\\" + file ).csvWriter();
				new AtFileWriter(missing,
						"D:\\TestFile\\TaipeiMainhole\\eachMainhole\\errorData\\missing\\" + file ).csvWriter();
				new AtFileWriter(uper, "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\errorData\\over\\" + file )
						.csvWriter();
				new AtFileWriter(bottom,
						"D:\\TestFile\\TaipeiMainhole\\eachMainhole\\errorData\\lower\\" + file ).csvWriter();
				System.out.println("end " + file);

			}else{
				System.out.println(" no station " + file);
			}
		}

	}

}
