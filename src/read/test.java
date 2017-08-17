package read;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

import usualTool.TimeTranslate;

public class test {

	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub

			String file = "D:\\TestFile\\dbo.STAGE_TEMP_REALTIME_HIS.Table.sql";
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			for(int i=0;i<100;i++){
				System.out.println(br.readLine());
			}
	}

}
