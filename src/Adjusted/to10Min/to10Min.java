package Adjusted.to10Min;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import usualTool.AtFileReader;
import usualTool.AtFileWriter;
import usualTool.TimeTranslate;

public class to10Min {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		TimeTranslate tt = new TimeTranslate();
		
			String fileAdd = "D:\\TestFile\\TaipeiMainhole\\中山\\original\\";
			String saveAdd = "D:\\TestFile\\TaipeiMainhole\\中山\\AD\\";
			String[] fileList = new File(fileAdd).list();
			
			ArrayList<String[]> outA = new ArrayList<String[]>();
			for(int i=0;i<fileList.length;i++){
				String[][] content = new AtFileReader(fileAdd + fileList[i]).getContent("\t", 1, 0);
				for(int line = 0;line<content.length;line++){
					String base = tt.StringGetSelected(content[line][1], "yyyyMMddHHmmss", "yyyyMMddHHmm");
					base = base.substring(0,base.length()-1) + "0";
					String[] tempt = new String[3];
					tempt[0] = content[line][0];
					tempt[1] = base;
					tempt[2] = content[line][3];
					outA.add(tempt);
				}
				String outS[][] = outA.parallelStream().toArray(String[][]::new);
				new AtFileWriter(outS,saveAdd + fileList[i]).tabWriter();
			}
		
	}

}
