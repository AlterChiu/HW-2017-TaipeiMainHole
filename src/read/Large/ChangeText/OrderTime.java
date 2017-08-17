package read.Large.ChangeText;

import java.io.File;
import java.io.IOException;

import usualTool.AtArraySort;
import usualTool.AtFileReader;
import usualTool.AtFileWriter;

public class OrderTime {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\adjustedValue\\";
		String saveAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\orderDateEach\\";
		
		String fileList[] = new File(fileAdd).list();
		
		for(String file:fileList){
			String[][] content = new AtFileReader(fileAdd+file).getCsv();
			content = new AtArraySort<String>(content,2).sorted().getSorted();
			new AtFileWriter(content,saveAdd+file).csvWriter();
		}

	}

}
