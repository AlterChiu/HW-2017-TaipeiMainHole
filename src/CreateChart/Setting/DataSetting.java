package CreateChart.Setting;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import usualTool.TimeTranslate;

public class DataSetting {
	private LabelXYDataset xydataset;
	private TimeTranslate tt = new TimeTranslate();

	// input a string array (3column : x , y , label)

	public DataSetting(TreeMap<Integer, String[][]> dataCollection, String[] lineName) throws IOException, NumberFormatException, ParseException {

		this.xydataset = new LabelXYDataset();

		if (dataCollection.get(0)[0].length < 3) {
			for (int i = 0; i < dataCollection.size(); i++) {
				// getting data
				String[][] data = dataCollection.get(i);
				// setting Line name

				// setting data
				for (int k = 0; k < data.length; k++) {
					xydataset.add(tt.StringToLong((data[k][0]), "yyyyMMddHHmm"), Double.parseDouble(data[k][1]), "");
				}
				xydataset.setSeriesName(lineName[i]);
				xydataset.endSeries(i);
			}

		} else {
			for (int i = 0; i < dataCollection.size(); i++) {
				// getting data
				String[][] data = dataCollection.get(i);
				// setting Line name

				// setting data
				for (int k = 0; k < data.length; k++) {
					xydataset.add(tt.StringToLong((data[k][0]), "yyyyMMddHHmm"), Double.parseDouble(data[k][1]), data[k][2]);
				}
				xydataset.setSeriesName(lineName[i]);
				xydataset.endSeries(i);

			}
		}

	}

	public XYDataset getDataset() {
		return this.xydataset;
	}

}
