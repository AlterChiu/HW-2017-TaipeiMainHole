package statics.eachStatics;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import usualTool.AtArrayFunction;
import usualTool.AtCommonMath;
import usualTool.AtFileReader;

public class EachMainholeSTD {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String folderAdd = "D:\\TestFile\\TaipeiMainhole\\Delta\\";
		String folderList[] = new File(folderAdd).list();

		for (String folder : folderList) {
			String fileAdd = folderAdd + folder + "\\";
			String[] fileList = new File(fileAdd).list();
			ArrayList<Double> staticsArray = new ArrayList<Double>();
			for(String file: fileList){
				String content[][] = new AtFileReader(fileAdd + file).getCsv();
				for(String line[]:content){
					staticsArray.add(Double.parseDouble(line[1]));
				}
			}
			AtCommonMath ds = new AtCommonMath(staticsArray.stream().mapToDouble(Double::doubleValue).toArray());
			System.out.print(folder+ "\t");
			System.out.print(ds.getMean() + "\t");
			System.out.print(ds.getStd() + "\t");
			System.out.print(ds.getMax() + "\t");
			System.out.println(ds.getMin()+"\t");
		}
	}

}
