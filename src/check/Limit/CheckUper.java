package check.Limit;

import java.util.ArrayList;

public class CheckUper {

	ArrayList<String[]> uper = new ArrayList<String[]>();
	ArrayList<String[]> bottom = new ArrayList<String[]>();
	ArrayList<String[]> out = new ArrayList<String[]>();
	
	
	public CheckUper(String content[][] , double over , double lower,int index){
		for(int i=0;i<content.length;i++){
			try{
			if(Double.parseDouble(content[i][index])>over){
				uper.add(content[i]);
			}else if(Double.parseDouble(content[i][index])<lower){
				bottom.add(content[i]);
			}else{ 
				out.add(content[i]);
			}
			}catch(Exception e){
			}
		}
	}
	
	public String[][] getContent(){
		return this.out.parallelStream().toArray(String[][]::new);
	}
	public String[][] getUper(){
		return this.uper.parallelStream().toArray(String[][]::new);
	}
	public String[][] getBottom(){
		return this.bottom.parallelStream().toArray(String[][]::new);
	}
	
}
