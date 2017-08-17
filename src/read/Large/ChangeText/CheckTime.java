package read.Large.ChangeText;

import java.io.File;
import java.io.IOException;

import usualTool.AtFileReader;
import usualTool.AtFileWriter;
import usualTool.AtStringFunction;

public class CheckTime {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\originalEach\\";
		String saveAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\adjustedValue\\";
		
		String fileList[] = new File(fileAdd).list();
		for(String file:fileList){
			String content[][] = new AtFileReader(fileAdd + file).getCsv();
			for(int i=0;i<content.length;i++){
				String time =content[i][2];
				if(time.contains("\"")){
					time = new AtStringFunction(time).Clip("\"").trim();
					time = new AtStringFunction(time).FillBack("0", time.length()+2);
					content[i][2]=time;
				}
			}
			new AtFileWriter(content,saveAdd + file+".csv").csvWriter();
		}

	}

}
