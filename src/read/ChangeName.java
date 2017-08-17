package read;

import java.io.File;
import java.io.IOException;

import usualTool.AtFileReader;
import usualTool.AtFileWriter;
import usualTool.FileFunction;

public class ChangeName {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String orAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\errorData\\lower\\";
		String[] fileList = new File(orAdd).list();
		FileFunction ff = new FileFunction();
		
		for(String file:fileList){
			String name = file.split(".csv.csv")[0];
			ff.reNameFile(orAdd + file, orAdd + name);
			
		}
		
	}
	
}
