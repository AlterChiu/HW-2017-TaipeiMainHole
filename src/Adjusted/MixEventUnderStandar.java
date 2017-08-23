package Adjusted;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import usualTool.AtFileReader;
import usualTool.AtFileWriter;

public class MixEventUnderStandar {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

			String folderAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\underStanderValue_event\\";
			String saveAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\underStanderValue\\";
			String[] folderList = new File(folderAdd).list();
			
			for(String folder:folderList){
				String fileList[] = new File(folderAdd + folder + "\\" ).list();
				ArrayList<String[]> outArray = new ArrayList<String[]>();
				
				
				for(String file : fileList){
				String add = folderAdd + folder + "\\" + file;
				ArrayList<String[]> current = new ArrayList<String[]>(Arrays.asList(new AtFileReader(add).getCsv()));	
				outArray.addAll(current);	
				}
				
				new AtFileWriter(outArray.parallelStream().toArray(String[][]::new) , saveAdd + folder + ".csv").csvWriter();
			}
		
	}

}
