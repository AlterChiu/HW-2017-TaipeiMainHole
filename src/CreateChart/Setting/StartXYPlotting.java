package CreateChart.Setting;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;

import usualTool.TimeTranslate;



public class StartXYPlotting  extends JFrame {
	private XYDataset dataset;
	private ChartSetting charset = new ChartSetting();
	private String saveLocation = "C:\\Users\\alter\\Desktop\\000.jpg";
	private TimeTranslate tt = new TimeTranslate();
	
	
	// setting the display data and the name of line on xyplot
	public void settingData(TreeMap<Integer,String[][]> data , String[] lineName) throws IOException, NumberFormatException, ParseException{
		this.dataset = new DataSetting(data , lineName).getDataset();
	}
	// setting the x , y Axis and title on the xy plot 
	public void settingChart(String title , String xAxis , String yAxis){
		this.charset.axisSetting(title, xAxis, yAxis);
	}
	// setting the JPG saving location
	public void settingSaveingLocation(String saveLocation){
		this.saveLocation = saveLocation;
	}
			
	// start plotting 
	public void Start() throws IOException, ParseException{
	this.charset.dataSetting(this.dataset);
	JFreeChart chart =this.charset.getChart();
	
	XYPlot xyplot = (XYPlot) chart.getPlot();
	XYDataset dataset = xyplot.getDataset();
	long start =(long) dataset.getXValue(1, dataset.getItemCount(1)-1);
	long end = (long)dataset.getXValue(1, 0);
	int space = (int)( (start - end +  1)/600000);
	
	ChartUtilities.saveChartAsJPEG(new File(saveLocation), chart, space*20, 800);
}

}
