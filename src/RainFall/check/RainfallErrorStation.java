package RainFall.check;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import usualTool.AtFileReader;
import usualTool.AtFileWriter;
import usualTool.TimeTranslate;

public class RainfallErrorStation {

	public static void main(String[] args) throws IOException, NumberFormatException, ParseException {
		// TODO Auto-generated method stub

		String content[][] = new AtFileReader("D:\\TestFile\\rainfall_2006_2017_errorStation.csv").getCsv();
		TimeTranslate tt = new TimeTranslate();
		ArrayList<String> out006 = new ArrayList<String>();
		ArrayList<String> out06 = new ArrayList<String>();
		ArrayList<String> out009 = new ArrayList<String>();
		ArrayList<String> out09 = new ArrayList<String>();
		ArrayList<String> out014 = new ArrayList<String>();
		ArrayList<String> out14 = new ArrayList<String>();
		ArrayList<String> out015 = new ArrayList<String>();
		ArrayList<String> out15 = new ArrayList<String>();
		
		double[] minList = { 0, 25.5, 20.5, 23.5, 31.5, 34, 44.5, 56.5, 78, 48.5, 40, 29, 29 };

		for (int i = 0; i < content.length; i++) {
			switch (content[i][0].trim()) {

			case ("006"):
					for(int k=0 ; k<6;k++){
						int month = Integer.parseInt(tt.StringGetSelected(content[i][2], "yyyyMMddHH", "MM"));
						if(Double.parseDouble(content[i][k+3]) <=  minList[month]){
							out006.add(content[i][2] + k + "0," + content[i][k+3]);
						}
					}
			case ("06"):
				for(int k=0 ; k<6;k++){
					int month = Integer.parseInt(tt.StringGetSelected(content[i][2], "yyyyMMddHH", "MM"));
					if(Double.parseDouble(content[i][k+3]) <=  minList[month]){
						out06.add(content[i][2] + k + "0," + content[i][k+3]);
					}
				}
			case ("009"):
				for(int k=0 ; k<6;k++){
					int month = Integer.parseInt(tt.StringGetSelected(content[i][2], "yyyyMMddHH", "MM"));
					if(Double.parseDouble(content[i][k+3]) <=  minList[month]){
						out009.add(content[i][2] + k + "0," + content[i][k+3]);
					}
				}
			case ("09"):
				for(int k=0 ; k<6;k++){
					int month = Integer.parseInt(tt.StringGetSelected(content[i][2], "yyyyMMddHH", "MM"));
					if(Double.parseDouble(content[i][k+3]) <=  minList[month]){
						out09.add(content[i][2] + k + "0," + content[i][k+3]);
					}
				}
			case ("014"):
				for(int k=0 ; k<6;k++){
					int month = Integer.parseInt(tt.StringGetSelected(content[i][2], "yyyyMMddHH", "MM"));
					if(Double.parseDouble(content[i][k+3]) <=  minList[month]){
						out014.add(content[i][2] + k + "0," + content[i][k+3]);
					}
				}
			case ("14"):
				for(int k=0 ; k<6;k++){
					int month = Integer.parseInt(tt.StringGetSelected(content[i][2], "yyyyMMddHH", "MM"));
					if(Double.parseDouble(content[i][k+3]) <=  minList[month]){
						out14.add(content[i][2] + k + "0," + content[i][k+3]);
					}
				}
			case ("015"):
				for(int k=0 ; k<6;k++){
					int month = Integer.parseInt(tt.StringGetSelected(content[i][2], "yyyyMMddHH", "MM"));
					if(Double.parseDouble(content[i][k+3]) <=  minList[month]){
						out015.add(content[i][2] + k + "0," + content[i][k+3]);
					}
				}
			case ("15"):
				for(int k=0 ; k<6;k++){
					int month = Integer.parseInt(tt.StringGetSelected(content[i][2], "yyyyMMddHH", "MM"));
					if(Double.parseDouble(content[i][k+3]) <=  minList[month]){
						out15.add(content[i][2] + k + "0," + content[i][k+3]);
					}
				}
			}
		}
		
		new AtFileWriter(out006.parallelStream().toArray(String[]::new),"D:\\TestFile\\006.csv").csvWriter();
		new AtFileWriter(out06.parallelStream().toArray(String[]::new),"D:\\TestFile\\ 06.csv").csvWriter();
		new AtFileWriter(out009.parallelStream().toArray(String[]::new),"D:\\TestFile\\ 009.csv").csvWriter();
		new AtFileWriter(out09.parallelStream().toArray(String[]::new),"D:\\TestFile\\09.csv").csvWriter();
		new AtFileWriter(out014.parallelStream().toArray(String[]::new),"D:\\TestFile\\014.csv").csvWriter();
		new AtFileWriter(out14.parallelStream().toArray(String[]::new),"D:\\TestFile\\14.csv").csvWriter();
		new AtFileWriter(out015.parallelStream().toArray(String[]::new),"D:\\TestFile\\ 015.csv").csvWriter();
		new AtFileWriter(out15.parallelStream().toArray(String[]::new),"D:\\TestFile\\15.csv").csvWriter();
	}

}
