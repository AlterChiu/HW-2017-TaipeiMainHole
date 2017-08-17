package check;

import java.io.IOException;
import java.util.ArrayList;

import usualTool.AtFileReader;
import usualTool.AtFileWriter;

public class CheckValue {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String station = "U0046";
		String fileName = station + ".txt_0601";
		double level = 1.85;
		
		String fileAdd = "D:\\TestFile\\TaipeiMainhole\\中山\\event\\original\\" + fileName;
		String saveAdd = "D:\\TestFile\\TaipeiMainhole\\中山\\event\\AD\\" + station + "\\";
		String content[][] = new AtFileReader(fileAdd).getContent("\t");
		
		ArrayList<String[]> outA = new ArrayList<String[]>();
		for(int i=0;i<content.length;i++){
			if(Double.parseDouble(content[i][3])>=0 && Double.parseDouble(content[i][3])<=level+1.5){
				outA.add(content[i]);
			}
		}
		String[][] outPut = outA.parallelStream().toArray(String[][]::new);
		new AtFileWriter(outPut ,saveAdd + fileName).tabWriter();

	}

}
