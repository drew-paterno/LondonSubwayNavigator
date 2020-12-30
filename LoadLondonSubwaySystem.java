
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class LoadLondonSubwaySystem {
  // private static ArrayList<Station> station;
  private static ArrayList<Connection> connection;
  private static ArrayList<StationLine> line;

  public static ArrayList<Station> loadStation(String stations) {
    ArrayList<Station> stationList = new ArrayList<Station>();

    try {
      File myFile = new File(stations);
      FileReader myFileReader = new FileReader(myFile);
      BufferedReader myBufferedReader = new BufferedReader(myFileReader);
      String line = "";
      String[] myLineContents;
      Station myStation;

      line = myBufferedReader.readLine(); // skipping first line
      line = myBufferedReader.readLine();

      while (line != null) {
        myLineContents = line.split(",");

        if (myLineContents[4].startsWith(" ")) {
          String str1 = myLineContents[3];
          String str2 = myLineContents[4];
          String str3 = myLineContents[5];
          String str4 = myLineContents[6];

          myLineContents[3] = str1 + "," + str2;
          myLineContents[3] = myLineContents[3].substring(1, myLineContents[3].length() - 1);
          myLineContents[4] = str3 + "," + str4;
          myLineContents[4] = myLineContents[4].substring(1, myLineContents[4].length() - 1);

          myLineContents[5] = myLineContents[7];
          myLineContents[6] = myLineContents[8];
          myLineContents[7] = myLineContents[9];
        }
        Integer id = Integer.parseInt(myLineContents[0]);
        Double latitude = Double.parseDouble(myLineContents[1]);
        Double longitude = Double.parseDouble(myLineContents[2]);
        String stationName = myLineContents[3];

        Double zone = Double.parseDouble(myLineContents[5]);
        Integer totalLines = Integer.parseInt(myLineContents[6]);
        Integer rail = Integer.parseInt(myLineContents[7]);

        myStation = new Station(id, stationName, longitude, latitude, zone, totalLines, rail);
        stationList.add(myStation);
        line = myBufferedReader.readLine();
      }
      myBufferedReader.close();


    } catch (IOException e1) {
      e1.printStackTrace();
    }

    return stationList;
  }

  public static ArrayList<Connection> loadConnection(String connections) {
    ArrayList<Connection> connectionList = new ArrayList<Connection>();

    try {
      File myFile = new File(connections);
      FileReader myFileReader = new FileReader(myFile);
      BufferedReader myBufferedReader = new BufferedReader(myFileReader);
      String line = "";
      String[] myLineContents;
      Connection myConnection;

      line = myBufferedReader.readLine(); // skipping first line
      line = myBufferedReader.readLine();

      while (line != null) {
        myLineContents = line.split(",");
        Integer fromStation = Integer.parseInt(myLineContents[0]);
        Integer toStation = Integer.parseInt(myLineContents[1]);
        Integer lineNum = Integer.parseInt(myLineContents[2]);
        Integer time = Integer.parseInt(myLineContents[3]);

        myConnection = new Connection(fromStation, toStation, lineNum, time);
        connectionList.add(myConnection);
        connectionList.add(new Connection(toStation, fromStation, lineNum, time));
        line = myBufferedReader.readLine();
      }
      myBufferedReader.close();
    } catch (IOException e2) {
      e2.printStackTrace();
    }
    return connectionList;
  }

  public static ArrayList<StationLine> loadStationLine(String stationLines) {
    ArrayList<StationLine> stationLineList = new ArrayList<StationLine>();
    Hashtable<String, String> colorList = new Hashtable<String, String>();
    colorList = loadColor("colorList.csv");

    try {
      File myFile = new File(stationLines);
      FileReader myFileReader = new FileReader(myFile);
      BufferedReader myBufferedReader = new BufferedReader(myFileReader);
      String line = "";
      String[] myLineContents;
      StationLine myStationLine;

      line = myBufferedReader.readLine(); // skipping first line
      line = myBufferedReader.readLine();

      while (line != null) {
        myLineContents = line.split(",");
        Integer lineNum = Integer.parseInt(myLineContents[0]);
        String name = myLineContents[1];
        // String colour = myLineContents[2];
        String colour = colorList.get(myLineContents[2]);
        // System.out.println("myLineContents[2]: " + myLineContents[2]);
        // System.out.println("colorList.get(): " + colorList.get(myLineContents[2]));

        String stripe = myLineContents[3];

        myStationLine = new StationLine(lineNum, name, colour, stripe);
        stationLineList.add(myStationLine);
        line = myBufferedReader.readLine();
      }
      myBufferedReader.close();
    } catch (IOException e3) {
      e3.printStackTrace();
    }
    return stationLineList;
  }

  public static Hashtable<String, String> loadColor(String colors) {
    Hashtable<String, String> colorList = new Hashtable<String, String>();

    try {
      File myFile = new File(colors);
      FileReader myFileReader = new FileReader(myFile);
      BufferedReader myBufferedReader = new BufferedReader(myFileReader);
      String line = "";
      String[] myLineContents;
      String colorHex;
      String colorName;

      line = myBufferedReader.readLine(); // skipping first line
      line = myBufferedReader.readLine();

      while (line != null) {
        myLineContents = line.split(",");
        colorHex = myLineContents[0];
        colorName = myLineContents[1];
        colorList.put("\"" + colorHex + "\"", colorName);

        line = myBufferedReader.readLine();
      }
      myBufferedReader.close();
    } catch (IOException e4) {
      e4.printStackTrace();
    }
    return colorList;
  }
}
