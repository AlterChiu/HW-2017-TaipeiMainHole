package statics.eachStatics.delta;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import usualTool.AtCommonMath;
import usualTool.AtFileReader;
import usualTool.AtFileWriter;
import usualTool.FileFunction;
import usualTool.TimeTranslate;

public class EachEventDelta {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		FileFunction ff = new FileFunction();
		TimeTranslate tt = new TimeTranslate();

		String folderAdd = "D:\\TestFile\\TaipeiMainhole\\event\\eachEvent\\";
		String folderList[] = new File(folderAdd).list();

		String saveAdd = "D:\\TestFile\\TaipeiMainhole\\Delta\\";
		// Go FileList
		for (String folder : folderList) {
			String fileAdd = folderAdd + folder + "\\";
			String fileList[] = new File(fileAdd).list();
			// Create folder
			ff.newFolder(saveAdd + folder);

			for (String file : fileList) {
				String[][] content = new AtFileReader(fileAdd + file).getCsv();
				ArrayList<String[]> contentArray = new ArrayList<String[]>();
				// go event
				for(int i=1;i<content.length;i++){
					if((tt.StringToLong(content[i][0], "yyyyMMddHHmm") - tt.StringToLong(content[i-1][0], "yyyyMMddHHmm"))<600050){
						String tempt[] = new String[2];
						tempt[0] = content[i][0];
						tempt[1] = Double.parseDouble(content[i][1]) -Double.parseDouble( content[i-1][1]) + "";
						tempt[1] = new BigDecimal(tempt[1]).setScale(3, BigDecimal.ROUND_HALF_UP).toString();
						contentArray.add(tempt);
					}
				}
				String[][] out = contentArray.parallelStream().toArray(String[][]::new);
				new AtFileWriter(out,saveAdd + folder + "\\" + file).csvWriter();
			}

		}
	}

}
