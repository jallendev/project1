/**
 * @author Joseph Allen
 * @version 2019-09-08
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class HammingDist
{
	private final int INITIAL_STATION_SIZE = 10;
	private final String FILENAME = "Mesonet.txt";
	private final String STATION_COLUMN_HEADER = "STID";
	private final String THE_STATION_WE_WERE_TOLD_TO_USE = "NRMN";
	String[] stations = new String[INITIAL_STATION_SIZE];
	private String station1;
	private String station2;
	private int hamming1;
	private int hamming2;
	private int hammingStations1;
	private int hammingStations2;
	
	public HammingDist(String str1, String str2) throws IOException{
		readFile();
		station1 = str1;
		station2 = str2;
		hamming1 = calcHamming(THE_STATION_WE_WERE_TOLD_TO_USE, station1);
		hamming2 = calcHamming(THE_STATION_WE_WERE_TOLD_TO_USE, station2);
		hammingStations1 = calcStationsWithHamming(station1, hamming1);
		hammingStations2 = calcStationsWithHamming(station2, hamming2);
	}
	
	@Override
	public String toString() {
		String output = new String("");
		output += "The Hamming Distance between Norman and " + station1 + " is " + hamming1 + " and for " + station2 + ": " + hamming2 + ".\n" + 
				"For " + station1 + ", number of stations with Hamming Distance " + hamming1 + " is " + hammingStations1 + ", and\n" + 
				"for " + station2 + ", number of stations with Hamming Distance " + hamming2 + " is " + hammingStations2 + ".";
		return output;
	}
	
	private void readFile() throws IOException{
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(FILENAME));
			String line = new String("");
			String station = new String("");
			Scanner stationFinder;
			boolean startReading = false;
			int index = 0;
			
			line = fileReader.readLine();
			while(line != null) {
				stationFinder = new Scanner(line);
				station = stationFinder.next();
				if (startReading) {
					if (index >= stations.length) {
						stations = expandStations();
					}
					stations[index] = station;
					++index;
				}
				if (station.equals(STATION_COLUMN_HEADER)){
					startReading = true;
				}
				line = fileReader.readLine();
			}
			fileReader.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private String[] expandStations() {
		String[] tempStations = new String[stations.length + 10];
		
		for (int index = 0; index < stations.length; ++index) {
			tempStations[index] = stations[index];
		}
		
		stations = new String[stations.length + 10];
		return tempStations;
	}
	
	private int calcHamming(String str1, String str2) {
		int hammingCounter = 0;
		for (int currChar = 0; currChar < str1.length(); ++currChar) {
			if (!str1.substring(currChar, currChar + 1).equals(str2.substring(currChar, currChar + 1))) {
				++hammingCounter;
			}
		}
		return hammingCounter;
	}
	
	private int calcStationsWithHamming(String station, int hamming) {
		int matchingStations = 0;
		for (int index = 0; index < stations.length; ++index) {
			if (calcHamming(stations[index], station) == hamming) {
				++matchingStations;
			}
		}
		return matchingStations;
	}
}