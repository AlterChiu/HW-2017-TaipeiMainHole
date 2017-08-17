package statics.errorPersentage;

import java.io.IOException;

import usualTool.AtFileReader;
import usualTool.AtFileWriter;

public class ErrorPersentage {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String missingAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\errorData\\missing\\";
		String lowerAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\errorData\\lower\\";
		String overAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\errorData\\over\\";
		String adjustAdd = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\adjustedValue\\";
		
		String missingSave = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\After2015\\missing\\";
		String lowerSave = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\After2015\\lower\\";
		String overSave = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\After2015\\over\\";
		String adjustSvae = "D:\\TestFile\\TaipeiMainhole\\eachMainhole\\After2015\\adjusted\\";

		String[][] station = new AtFileReader("D:\\TestFile\\TaipeiMainhole\\stationLimit.csv").getCsv(1, 0);

		System.out.println("ID \t name \t missing \t lower \t over \t adjusted");
		
		for (int i = 0; i < station.length; i++) {
			String name = station[i][0];
			System.out.print( station[i][0] + "\t"  +  station[i][1]  + "\t");
			try {
				String[][] content = new GetList(new AtFileReader(missingAdd + name + ".csv").getCsv()).getContent();
				new AtFileWriter(content , missingSave + name).csvWriter();
				System.out.print(content.length + "\t");
			} catch (Exception e) {
			}
			try {
				String[][] content = new GetList(new AtFileReader(lowerAdd + name + ".csv").getCsv()).getContent();
				new AtFileWriter(content , lowerSave + name).csvWriter();
				System.out.print(content.length + "\t");
			} catch (Exception e) {
			}
			try {
				String[][] content = new GetList(new AtFileReader(overAdd + name + ".csv").getCsv()).getContent();
				new AtFileWriter(content , overSave + name).csvWriter();
				System.out.print(content.length + "\t");
			} catch (Exception e) {
			}
			try {
				String[][] content = new GetList(new AtFileReader(adjustAdd + name + ".csv").getCsv()).getContent();
				new AtFileWriter(content , adjustSvae + name).csvWriter();
				System.out.print(content.length + "\t");
			} catch (Exception e) {
			}
			System.out.println();
		}

	

	}

}
