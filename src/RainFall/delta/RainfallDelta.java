package RainFall.delta;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

import usualTool.AtFileReader;
import usualTool.AtFileWriter;
import usualTool.TimeTranslate;

public class RainfallDelta {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		TimeTranslate tt = new TimeTranslate();

		String fileAdd = "D:\\TestFile\\RainFallStation\\eachStation_Adjusted_10min\\";
		String saveAdd = "D:\\TestFile\\RainFallStation\\eachStation_delta\\";

		String[] fileList = new File(fileAdd).list();
		for (String file : fileList) {
			ArrayList<String[]> outList = new ArrayList<String[]>();
			String content[][] = new AtFileReader(fileAdd + file).getCsv();

			for (int i = 1; i < content.length; i++) {
				if ((tt.StringToLong(content[i][0], "yyyyMMddHHmm")
						- tt.StringToLong(content[i - 1][0], "yyyyMMddHHmm")) < 700000) {
					String[] tempt = new String[2];
					tempt[0] = content[i][0];
					tempt[1] = new BigDecimal(Double.parseDouble(content[i][1]) - Double.parseDouble(content[i - 1][1]))
							.setScale(3, BigDecimal.ROUND_HALF_UP).toString();
					outList.add(tempt);
				}
			}
			new AtFileWriter(outList.parallelStream().toArray(String[][]::new) , saveAdd + file).csvWriter();
		}
	}

}
