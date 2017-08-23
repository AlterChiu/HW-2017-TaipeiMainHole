package Corilation.display;

import java.io.IOException;
import java.util.TreeMap;

import usualTool.AtFileReader;
import usualTool.AtFileWriter;
import usualTool.AtTreeMaker;

public class IDtoName {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String[][] rainStationList = new AtFileReader("D:\\TestFile\\rainfallStationList.txt").getContent("\t");
		TreeMap<String,String> rainStationTree = new AtTreeMaker(rainStationList,0).getSingleTree(1);
	String[][] mainHoleStationList = new AtFileReader("D:\\TestFile\\mainHoleStationList.txt").getContent("\t");
		TreeMap<String,String> mainHoleStationTree = new AtTreeMaker(mainHoleStationList,0).getSingleTree(1);
	
		String[][] corilation = new AtFileReader("D:\\TestFile\\corilation\\corilate_ID.csv").getCsv();
		String[][] delay = new AtFileReader("D:\\TestFile\\corilation\\delay_ID.csv").getCsv();
		
		for(int i=1;i<corilation[0].length;i++){
			String name = rainStationTree.get(corilation[0][i].trim());
			corilation[0][i] =  name;
			delay[0][i] =  name;
		}
		
		for(int i=1;i<corilation.length;i++){
			String name = mainHoleStationTree.get(corilation[i][0]);
			corilation[i][0] =  name;
			delay[i][0] =  name;
		}
	 
		
		new AtFileWriter(corilation,"D:\\TestFile\\corilation\\corilate_Name.csv").csvWriter();
		new AtFileWriter(delay,"D:\\TestFile\\corilation\\delay_Name.csv").csvWriter();

	}

}
