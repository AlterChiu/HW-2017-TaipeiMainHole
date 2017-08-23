package Corilation.display;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import usualTool.AtFileReader;
import usualTool.AtFileWriter;
import usualTool.AtTreeMaker;

public class DisPlay {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String[][] rainStationList = new AtFileReader("D:\\TestFile\\rainfallStationList.txt").getContent("\t");
			TreeMap<String,String> rainStationTree = new AtTreeMaker(rainStationList,0).getSingleTree(1);
		String[][] mainHoleStationList = new AtFileReader("D:\\TestFile\\mainHoleStationList.txt").getContent("\t");
			TreeMap<String,String> mainHoleStationTree = new AtTreeMaker(mainHoleStationList,0).getSingleTree(1);
		String[][] corilation = new AtFileReader("D:\\TestFile\\corilation.csv").getCsv();
		 
		ArrayList<String[]> delayOut = new ArrayList<String[]>();
		ArrayList<String[]> corilateOut= new ArrayList<String[]>();
		ArrayList<String> idList  = new ArrayList<String>();
		
		for(String line[] : corilation){
		
			ArrayList<String> delay = new ArrayList<String>();
			ArrayList<String> corilate = new ArrayList<String>();
			ArrayList<String> id = new ArrayList<String>();
			delay.add(line[0]);
			corilate.add(line[0]);
			id.add("\t");
			
			for(int i=1;i<line.length;i++){
				id.add(line[i].split("_")[0]);
				delay.add(line[i].split("_")[1]);
				corilate.add(line[i].split("_")[2]);
			}
			
			delayOut.add(delay.parallelStream().toArray(String[]::new));
			corilateOut.add(corilate.parallelStream().toArray(String[]::new));
			idList = id;
		}
		
		new AtFileWriter(delayOut.parallelStream().toArray(String[][]::new),"D:\\TestFile\\delay.csv").csvWriter();
		new AtFileWriter(corilateOut.parallelStream().toArray(String[][]::new),"D:\\TestFile\\corilate.csv").csvWriter();
		System.out.println(idList);
		
	}

}
