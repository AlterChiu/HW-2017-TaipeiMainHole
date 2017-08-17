package statics.errorPersentage;

import java.util.ArrayList;

public class GetList {
	
		ArrayList<String[]> tempt = new ArrayList<String[]>();
	public GetList(String[][] content){
		
		for(String line[] : content){
			if(Double.parseDouble(line[0])>201500000000.){
				tempt.add(line);
			}
		}
	}
	public String[][] getContent(){
		return this.tempt.parallelStream().toArray(String[][]::new);
	}

}
