package CreateChart.Setting;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.threed.Line;
import org.apache.commons.math3.geometry.spherical.twod.Circle;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ShapeUtilities;

import usualTool.AtCommonMath;

public class ChartSetting {

	private String lineName = "";
	private String title = "title";
	private String xAxis = "xAxis";
	private String yAxis = "yAxis";
	private XYDataset dataset = null;

	public void axisSetting(String title, String xAxis, String yAxis) {
		this.title = title;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}

	public void dataSetting(XYDataset dataset) {
		this.dataset = dataset;
	}

	public JFreeChart getChart() {
		// title X Y data
		JFreeChart chart = ChartFactory.createXYLineChart(title, xAxis, yAxis, dataset, PlotOrientation.VERTICAL, true,
				true, false);
		Font kfont = new Font("微軟正黑體", Font.PLAIN, 12); // 底部
		chart.getLegend().setItemFont(kfont);
		chart.getBorderStroke().createStrokedShape(new Line2D.Float());

		XYPlot plot = (XYPlot)chart.getPlot();

		// setting xyplot lookLike
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
		// setting label ( the point on the line)
		renderer.setBaseItemLabelGenerator(new LabelGenerator());
		renderer.setBasePositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.BOTTOM_RIGHT));
		renderer.setBaseItemLabelFont(new Font("微軟正黑體", Font.BOLD, 16));
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
		// setting line color and stroke of the line
		renderer.setSeriesItemLabelPaint(0, Color.decode("#188d16"));
		renderer.setSeriesPaint(1, Color.red);
		renderer.setSeriesStroke(1, new BasicStroke(1.0f));
		//renderer.setSeriesShape(1, new Ellipse2D.Double(-4, -4, 8, 8));
		renderer.setSeriesShapesVisible(0, true);
		renderer.setSeriesVisible(1, true);

		float dot[] = { 5.0f };
		renderer.setSeriesItemLabelsVisible(0, false);
		renderer.setSeriesPaint(0, Color.ORANGE);
	//	renderer.setSeriesStroke(0,
				//new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dot, 0.0f));
		//renderer.setSeriesShape(0, new Ellipse2D.Double(-2, -2, 8, 8));
		renderer.setSeriesShapesVisible(1, true);
		renderer.setSeriesVisible(0, true);

		
		renderer.setSeriesItemLabelsVisible(1, false);
		renderer.setSeriesPaint(2, Color.GREEN);
	//	renderer.setSeriesStroke(2,
			//	new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dot, 0.0f));
	//	renderer.setSeriesShape(2, new Ellipse2D.Double(-4, -4, 8, 8));
		renderer.setSeriesShapesVisible(2, true);
		renderer.setSeriesVisible(2, true);

		// X Axis
		DateAxis dateAxis = new DateAxis();
		dateAxis.setDateFormatOverride(new SimpleDateFormat("yyyyMMddHHmm")); 
		
		NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
		domainAxis.setLabelFont(new Font("微軟正黑體", Font.BOLD, 20));
		domainAxis.setTickLabelFont(new Font("微軟正黑體", Font.BOLD, 12));
		domainAxis.setTickLabelPaint(Color.black);
		
		
		double start = plot.getDataset().getXValue(0, plot.getDataset().getItemCount(0) - 1);
		double end = plot.getDataset().getXValue(0, 0);
		
		domainAxis.setAutoTickUnitSelection(false);
		domainAxis.setVerticalTickLabels(true);
		
		domainAxis.setRange(end, start);
		plot.setDomainAxis(dateAxis);

		// Y Axis
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(new Font("微軟正黑體", Font.BOLD, 20));
		rangeAxis.setLabelPaint(Color.black);
		rangeAxis.setTickLabelFont(new Font("微軟正黑體", Font.BOLD, 12));
		
		
		ArrayList<Double> topArray = new ArrayList<Double>();
		ArrayList<Double> bottomArray = new ArrayList<Double>();
		XYDataset temptSet = plot.getDataset();
		for(int i=0;i<temptSet.getItemCount(0);i++){
			topArray.add(temptSet.getYValue(0, i));
			bottomArray.add(temptSet.getYValue(2, i));
		}
		double top = new AtCommonMath(topArray).getMax();
		double min = new AtCommonMath(bottomArray).getMin();
	
		rangeAxis.setRange(min-0.1,top+0.1);

		// save render
		plot.setRenderer(renderer);

		// setting the chart looking
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);
		chart.setTitle(new TextTitle(this.title, new Font("微軟正黑體", java.awt.Font.BOLD, 30)));

		return chart;

	}

}
