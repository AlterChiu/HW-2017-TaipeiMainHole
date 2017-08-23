package CreateChart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.SwingUtilities;

import CreateChart.Setting.StartXYPlotting;
import usualTool.AtFileReader;
import usualTool.AtTreeMaker;

public class Runtimes {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		/**
		 * SwingUtilities.invokeLater(() -> { Example ex; try { ex = new
		 * Example(); ex.setVisible(false); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * });
		 **/

		// Example ex = new Example();
		// *
		// event list
		String[][] eventList = new AtFileReader("D:\\TestFile\\TaipeiMainhole\\event\\ID_events.csv").getContent("\t");
		TreeMap<String, String> eventTree = new AtTreeMaker(eventList, 0).getSingleTree(1);
		/**
		 * String[] keys =
		 * eventTree.keySet().parallelStream().toArray(String[]::new); for (int
		 * i = 0; i < keys.length; i++) { System.out.println(keys[i] + "\t" +
		 * eventTree.get(keys[i])); }
		 */
		String folderAdd = "D:\\TestFile\\TaipeiMainhole\\Delta\\";

		String fileAdd = folderAdd + "U0043\\";
		String fileList[] = new File(fileAdd).list();
		for (String file : fileList) {
			System.out.println(file + "\t" + eventTree.get(file));
			System.out.println(( eventTree.get(file))==null);
		}
	
	}
}
