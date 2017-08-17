package CreateChart.Setting;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;

public class StartXYPlotting  extends JFrame {
	private XYDataset dataset;
	private ChartSetting charset = new ChartSetting();
	private String saveLocation = "C:\\Users\\alter\\Desktop\\000.jpg";
	
	
	// setting the display data and the name of line on xyplot
	public void settingData(TreeMap<Integer,String[][]> data , String[] lineName) throws IOException{
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
	public void Start() throws IOException{
	this.charset.dataSetting(this.dataset);
	JFreeChart chart =this.charset.getChart();
	
	ChartUtilities.saveChartAsJPEG(new File(saveLocation), chart, 1080, 740);
}

}
