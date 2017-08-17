package Adjusted;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import usualTool.AtFileReader;
import usualTool.AtFileWriter;

public class trimValue {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String fileAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\orderDateEach\\";
		String fileList[] = new File(fileAdd).list();

		String saveAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\trim\\";
		
		for(String file : fileList){
			String content[]  = new AtFileReader(fileAdd + file).getContain();
			
			for(int line = 0;line<content.length;line++){
					content[line] = content[line].trim();
			}
			FileWriter fw = new FileWriter(saveAdd + file);
			for(String line:content){
				fw.write(line + "\r\n");
			}
			fw.close();
		}
		
	}

}
