package CreateChart.Setting;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.ShadowGenerator;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ShapeUtilities;

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
		JFreeChart chart = ChartFactory.createXYLineChart(title, xAxis, yAxis , dataset,
				PlotOrientation.VERTICAL, true, true, false);

		XYPlot plot = chart.getXYPlot();

		// setting xyplot lookLike
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
		// setting label
		renderer.setBaseItemLabelGenerator(new LabelGenerator());
		renderer.setBaseItemLabelPaint(Color.decode("#188d16"));
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE2, TextAnchor.TOP_LEFT));
		renderer.setBaseItemLabelFont(new Font("宋體", Font.BOLD, 16));
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());

		
		// SERIES0
		// setting line
		float dot[] = { 10.0f };
		renderer.setSeriesStroke(0,
				new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dot, 0.0f));
		// setting point
		renderer.setSeriesShape(0, new Ellipse2D.Double(-4, -4, 8, 8));
		renderer.setSeriesFillPaint(0, Color.RED);
		renderer.setSeriesOutlinePaint(0, Color.BLUE);
		renderer.setSeriesShapesVisible(0, true);
	

		// SEIRES1
		// setting line
		//renderer.setSeriesPaint(1, Color.DARK_GRAY);
		renderer.setSeriesStroke(1,
				new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dot, 0.0f));
		// setting point
		renderer.setSeriesShape(1, new Ellipse2D.Double(-4, -4, 8, 8));
		renderer.setSeriesShapesFilled(1, true);
		renderer.setSeriesFillPaint(1, Color.BLACK);
		renderer.setSeriesOutlinePaint(1, Color.BLACK);
		renderer.setSeriesShapesVisible(1, true);

		// X Axis
		ValueAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(new Font("宋體", Font.BOLD, 20));
		domainAxis.setTickLabelFont(new Font("宋體", Font.BOLD, 12));
		domainAxis.setTickLabelPaint(Color.black);

		// Y Axis
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(new Font("宋體", Font.BOLD, 12));
		rangeAxis.setLabelPaint(Color.black);
		rangeAxis.setTickLabelFont(new Font("宋體", Font.BOLD, 12));

		// save render
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);
		// the scalar line of chart base (row)
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.black);
		// the scalar line of chart base (column)
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.black);
	
		//		base color  => seriesPaint
		//  	outLineColor => outLineColor    boolean
		//		FillPaint => fillPaint    boolean
		
		renderer.setUseFillPaint(true);
		renderer.setUseOutlinePaint(true);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(new TextTitle("Average Salary per Age", new Font("Serif", java.awt.Font.BOLD, 18)));

		return chart;
	}

}
