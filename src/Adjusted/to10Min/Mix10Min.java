package Adjusted.to10Min;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.text.AbstractDocument.Content;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import usualTool.AtTreeFunction;
import usualTool.TimeTranslate;

public class Mix10Min {
	
	private ArrayList<String[]> out = new ArrayList<String[]>();
	private ArrayList<String> miss = new ArrayList<String>();
	private DescriptiveStatistics ds;
	private TimeTranslate tt = new TimeTranslate();
	private  TreeMap<String,ArrayList<Double>> tree = new TreeMap<String,ArrayList<Double>>();
	private String content[][];
	private int key;
	private int value;
	
	public Mix10Min(String[][] content , int key , int value){
		this.content = content;
		this.key = key;
		this.value = value;
		
		for(int i=0;i<content.length;i++){
			try{
			tree = new AtTreeFunction<String,Double>(tree).checkTree(content[i][key], Double.parseDouble(content[i][value]));
			}catch(Exception e){
			}
		}
		
	}
	
	public String[] getMissing() throws ParseException{
		String start = content[0][key];
		String end = content[content.length-1][key];
		
		System.out.println(start + "             " + end);
		
		while(!start.equals(end)){
		
			if(tree.get(start)==null){
				miss.add(start);
			}
			start = tt.milliToDate(tt.StringToLong(start, "yyyyMMddHHmm") + 600000, "yyyyMMddHHmm");
		}
		
		return this.miss.parallelStream().toArray(String[]::new);
	}
	
	public String[][] getMix(){
		String keys[] = tree.keySet().parallelStream().toArray(String[]::new);
		for(int i=0;i<keys.length;i++){
			double[] values = tree.get(keys[i]).stream().mapToDouble(Double::doubleValue).toArray();
			ds =  new DescriptiveStatistics(values);
			String[] tempt = new String[2];
			tempt[0] = (keys[i]);
			tempt[1] = (new BigDecimal(ds.getMean()).setScale(3,BigDecimal.ROUND_HALF_UP).toString());
			out.add(tempt);
		}
		return out.parallelStream().toArray(String[][]::new);
	}
	
	
}
