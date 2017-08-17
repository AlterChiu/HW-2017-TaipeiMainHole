package CreateChart;
import java.io.IOException;
import java.util.TreeMap;

import javax.swing.SwingUtilities;

import CreateChart.Setting.StartXYPlotting;




public class Runtimes {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		/**SwingUtilities.invokeLater(() -> {
			Example ex;
			try {
				ex = new Example();
				ex.setVisible(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});**/
		
		 //Example ex = new Example();
 //*
		String data1[][] = {{"0","1","0"},{"1","2","1"},{"2","3","2"}};
		String data2[][] = {{"10","1","0"},{"20","2","1"},{"30","3","2"}};
		TreeMap<Integer, String[][]> dataTree = new TreeMap<Integer, String[][]>();
		dataTree.put(0, data1);
		dataTree.put(1, data2);
		String nameList[] = {"0001" , "0002"};
		
		StartXYPlotting plotting =  new StartXYPlotting();
		plotting.settingChart("TEST", "累積多站平均雨量(mm)", "單站累計數年平均雨量(mm)");
		plotting.settingData(dataTree, nameList);
		plotting.settingSaveingLocation(
				"C:\\Users\\alter\\Desktop\\暑期實習\\出圖\\台北市開源檔案\\雨量站\\大量出圖\\00001.jpg");
		plotting.Start();
	//*/	
	}

}
