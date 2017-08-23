package CreateChart;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.TreeMap;

import usualTool.TimeTranslate;

public class DataCheck {
	private ArrayList<String[]> uper = new ArrayList<String[]>();
	private ArrayList<String[]> bottom = new ArrayList<String[]>();
	private ArrayList<String[]> standarUp = new ArrayList<String[]>();
	private ArrayList<String[]> standarDown = new ArrayList<String[]>();
	private ArrayList<String[]> adjusted = new ArrayList<String[]>();
	private TreeMap<Integer, String[][]> tree = new TreeMap<Integer, String[][]>();

	private TimeTranslate tt = new TimeTranslate();

	public DataCheck(String[][] content, String stds) throws ParseException {
		double std = Double.parseDouble(stds);

		int times = 0;
		int base = 1;

		for (int i = 1; i < content.length; i++) {
			String up[] = new String[2];
			String down[] = new String[2];

			times = times + checkTimes(content[i][0], content[i - 1][0]);
			up[0] = content[i][0];
			down[0] = content[i][0];

			if (times <= 3) {
				up[1] = (Double.parseDouble(content[i - base][1]) + times * std * 2) + "";
				down[1] = (Double.parseDouble(content[i - base][1]) - times * std * 2) + "";

				if (Double.parseDouble(content[i][1]) > Double.parseDouble(up[1])
						|| Double.parseDouble(content[i][1]) < Double.parseDouble(down[1])) {

					if (Double.parseDouble(content[i][1]) > Double.parseDouble(up[1])) {
						standarUp.add(content[i]);
					} else {
						standarDown.add(content[i]);
					}
					// below 3 lines have to delete

					base++;
					// adjusted.add(content[i]);
					 uper.add(up);
					bottom.add(down);
				} else {
					
					adjusted.add(content[i]);
					uper.add(up);
					bottom.add(down);
				
				}
				uper.add(up);
				bottom.add(down);
				times = 0;
				base = 1;
			} else {
				up[1] = content[i][1];
				down[1] = content[i][1];
				adjusted.add(content[i]);
				uper.add(up);
				bottom.add(down);
				times = 0;
				base = 1;
			}

		}
		tree.put(0, uper.parallelStream().toArray(String[][]::new));
		tree.put(1, adjusted.parallelStream().toArray(String[][]::new));
		tree.put(2, bottom.parallelStream().toArray(String[][]::new));

	}

	private int checkTimes(String start, String end) throws ParseException {
		int times = (int) ((tt.StringToLong(start, "yyyyMMddHHmm") - tt.StringToLong(end, "yyyyMMddHHmm") + 1)
				/ 600000);

		return times;
	}

	public TreeMap<Integer, String[][]> getDataCollection() {
		return this.tree;
	}

	public ArrayList<String[]> getStandarUp(ArrayList<String[]> temp) {
		String[][] temptString = temp.parallelStream().toArray(String[][]::new);
		for (String[] line : temptString) {
			standarUp.add(line);
		}
		return this.standarUp;
	}

	public ArrayList<String[]> getStandarDown(ArrayList<String[]> temp) {
		String[][] temptString = temp.parallelStream().toArray(String[][]::new);
		for (String[] line : temptString) {
			standarDown.add(line);
		}
		return this.standarDown;
	}

}
