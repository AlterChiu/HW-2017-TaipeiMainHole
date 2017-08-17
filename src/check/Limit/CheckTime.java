package check.Limit;

import java.io.IOException;
import java.util.ArrayList;

import usualTool.AtFileReader;
import usualTool.AtFileWriter;

public class CheckTime {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileName = "U0044.txt";
		String fileAdd = "D:\\TestFile\\TaipeiMainhole\\中山\\original\\";
		String saveAdd = "D:\\TestFile\\TaipeiMainhole\\中山\\event\\";
		String content[][] = new AtFileReader(fileAdd + fileName).getContent("\t",1,0);
		
		ArrayList<String[]> outA = new ArrayList<String[]>();
		for(int i=0;i<content.length;i++){
			if(Double.parseDouble(content[i][1])>=20170616210000. && Double.parseDouble(content[i][1])<=20170617180000.){
				//if(Double.parseDouble(content[i][1])>=20170601220000. && Double.parseDouble(content[i][1])<=20170604120000.)
				outA.add(content[i]);
			}
		}
		String[][] outPut = outA.parallelStream().toArray(String[][]::new);
		new AtFileWriter(outPut ,saveAdd + fileName+"_0616").tabWriter();

	}

}
