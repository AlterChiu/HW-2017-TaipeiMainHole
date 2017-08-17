package CreateChart.Setting;


import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.data.xy.XYDataset;

public class LabelGenerator implements XYItemLabelGenerator {

	// interface of label items
		@Override
		public String generateLabel(XYDataset dataset, int series, int item) {
			LabelXYDataset labelSource = (LabelXYDataset) dataset;
			return labelSource.getLabel(series, item);
		}
	}


