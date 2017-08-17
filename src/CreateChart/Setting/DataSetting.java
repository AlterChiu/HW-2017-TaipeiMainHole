package CreateChart.Setting;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DataSetting {
	private LabelXYDataset xydataset;

	// input a string array (3column : x , y , label)

	public DataSetting(TreeMap<Integer, String[][]> dataCollection, String[] lineName) throws IOException {

		this. xydataset = new LabelXYDataset();
		for (int i = 0; i < dataCollection.size(); i++) {
		
			// getting data
			String[][] data = dataCollection.get(i);
			//setting Line name
		
			// setting data
			for (int k = 0; k < data.length; k++) {
				xydataset.add(Double.parseDouble(data[k][0]), Double.parseDouble(data[k][1]), data[k][2]);
			}
			xydataset.endSeries(i);
		}
	
		
		
	}

	public XYDataset getDataset() {
		
		return this.xydataset;
	}

}
