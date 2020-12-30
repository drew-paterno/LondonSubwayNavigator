
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * JUnit Test suite designed to test the basic functionalities of the SubwayBackEnd and LoadLondonSubwaySystem classes.
 * @author Drew Paterno
 *
 */
public class LondonSubwayTester {

  private SubwayBackEnd back; // Reference to the back-end.

  /**
   * Initializes the SubwayBackEnd reference before each test.
   */
  @BeforeEach
  public void loadBackEnd() {
    back = new SubwayBackEnd();
  }

  /**
   * Tests the functionality of the LoadLondonSubwaySystem class.
   */
  @Test
  public void testLoader() {
    // Loads the stations, connections and lines from the .csv files.
    // TODO Replace the name of the files below with your local directory.
    ArrayList<Station> stations = LoadLondonSubwaySystem.loadStation("london.station.csv");
    ArrayList<Connection> connections =
        LoadLondonSubwaySystem.loadConnection("london.connection.csv");
    ArrayList<StationLine> lines = LoadLondonSubwaySystem.loadStationLine("london.line.csv");

    // Makes sure the ArrayLists associated with the stations, connections and lines have sizes consistent with the sizes of their respective .csv files.
    if (stations.size() != 302)
      fail();
    if (connections.size() != 406 * 2)
      fail();
    if (lines.size() != 13)
      fail();

    // Checks to make sure the station "Bond Street" has the correct fields in its station object. (Line 27 of london.stations.csv)
    if (!stations.get(25).getStationID().equals(28)
        || !stations.get(25).getLatitude().equals(51.5142)
        || !stations.get(25).getLongitude().equals(-0.1494)
        || !stations.get(25).getZone().equals(1.0) || !stations.get(25).getTotalLines().equals(2)
        || !stations.get(25).getRail().equals(0))
      fail();

    // Checks to make sure the connection on line 14 of london.connection.csv has the correct information in its connection object.
    if (!connections.get(24).getFromStation().equals(137)
        || !connections.get(24).getToStation().equals(298)
        || !connections.get(24).getLine().equals(1) || !connections.get(24).getTime().equals(3))
      fail();

    // Checks to make sure the inverse connection from line 14 of london.connection.csv has the correct information in its connection object.
    if (!connections.get(25).getFromStation().equals(298)
        || !connections.get(25).getToStation().equals(137)
        || !connections.get(25).getLine().equals(1) || !connections.get(25).getTime().equals(3))
      fail();

    // Checks to make sure the train line from line 5 of the london.line.csv has the correct information in its line object.
    if (!lines.get(3).getStationLine().equals(7)
        || !lines.get(3).getStationColor().equals("Grey Chateau")
        || !lines.get(3).getStationDescr().equals("\"Jubilee Line\"")
        || !lines.get(3).getStripe().contentEquals("NULL"))
      fail();
  }

  /**
   * Tests the functionality of the addStation method of the SubwayBackEnd class.
   */
  @Test
  public void testAddStation() {
    Station newStation = new Station(500, "test", 0.0, 0.0, 0.0, 0, 0);
    if (!back.addStation(newStation))
      fail(); // If the station is unable to be added, test fails.
    if (!back.containsVertex(newStation))
      fail(); // If the station not found in the graph, test fails.
    try {
      back.addStation(null); // Tries to add a null element.
      fail(); // If no exception is thrown, test fails.
    } catch (NullPointerException e) {

    } catch (Exception e) {
      fail(); // If any exception other than a NullPointerException is thrown, test fails.
    }
    if (back.addStation(newStation))
      fail(); // Tests fails if duplicate station is successfully added.
  }

  /**
   * Test the functionality of the addConnection method of the SubwayBackEnd class.
   */
  @Test
  public void testAddConnection() {
    try {
      back.addConnection(null, null, 0); // Attempts to add a connection between null nodes.
      fail(); // Tests fails if no exception is thrown.
    } catch (NullPointerException e) {

    } catch (Exception e1) {
      fail(); // If any exception other than a NullPointerException is thrown, test fails.
    }
    Station newStation1 = new Station(500, "test", 0.0, 0.0, 0.0, 0, 0);
    Station newStation2 = new Station(501, "test", 0.0, 0.0, 0.0, 0, 0);
    try {
      back.addConnection(newStation1, newStation2, 0); // Attempts to add a connection between two nodes that are not in the graph.
      fail(); // Test fails if no exception is thrown.
    } catch (IllegalArgumentException e) {

    } catch (Exception e2) {
      fail(); // If any exception other than a IllegalArgumentException is thrown, test fails.
    }

    back.addStation(newStation1);
    back.addStation(newStation2);

    try {
      if (!back.addConnection(newStation1, newStation2, 2))
        fail(); // Test fails if a connection between two valid vertices is unable to be added to the graph.
    } catch (Exception e) {
      fail(); // Tests fails if an exception is thrown.
    }

    try {
      if (back.addConnection(newStation1, newStation2, 2))
        fail(); // Test fails if duplicate connection is successfully added.
    } catch (Exception e) {
      fail(); // Tests fails if exception is thrown.
    }
  }

  /**
   * Tests methods in the BackEnd such as the search function, numStations, numConnections and clear.
   */
  @Test
  public void testSearch() {
    Station newStation = new Station(500, "NEW STATION", 0.0, 0.0, 0.0, 0, 0);
    if (back.vertices.get(newStation) != null)
      fail(); // Searches for a node that is not in the graph, fails if the program doesn't return null.
    back.addStation(newStation);
    if (!back.vertices.get(newStation).data.equals(newStation))
      fail(); // Test fails if newly added station is unable to be located in the graph.
    try {
      back.vertices.get(null); // Attempts to find the value null in the graph.
      fail(); // Fails if no exception is thrown.
    } catch (NullPointerException e) {

    } catch (Exception e) {
      fail(); // If any exception other than a NullPointerException is thrown, test fails.
    }

    if (back.numStations() != 304) {
      fail();
    }
    if (back.numConnections() != 698) {
      fail();
    }
    // From the original 812 connections, there are 108 Duplicate Connections, and 6 connections
    // with the same source/destination nodes, but with the different weights (which are updated).

    back.clear();
    if (back.numConnections() != 0 || back.numStations() != 0)
      fail(); // Test fails if the number of stations and connections isn't 0 after clearing the graph.
  }

  /**
   * Tests the shortestTimePath and shortestTimeCost methods of the BackEnd.
   */
  @Test
  public void testPaths() {
    try {
      if (back.shortestTimeCost(new Station("\"Picadilly Circus\""),
          new Station("\"Holborn\"")) != 4)
        fail(); // Test fails if the cost of the path between Picadilly Circus and Holborn isn't 4.
      if (!back.shortestTimePath(new Station("\"Picadilly Circus\""), new Station("\"Holborn\""))
          .toString()
          .equals("[\"Picadilly Circus\", \"Leicester Square\", \"Covent Garden\", \"Holborn\"]"))
        fail(); // Test fails if the the path between Picadilly Circus and Holborn isn't correct.
    } catch (Exception e) {
      fail(); // Test fails if an exception is thrown.
    }
    try {
      back.shortestTimeCost(new Station("\"Picadilly Circus\""),
          new Station("\"30th Street Station\"")); // Attempts to find a path from a station to a station that doesn't exist in the graph.
      fail(); // Test fails if an exception isn't thrown.
    } catch (java.util.NoSuchElementException e) {

    } catch (Exception e) {
      fail(); // If any exception other than a NoSuchElementException is thrown, test fails.
    }
    try {
      back.shortestTimeCost(null, null);
      fail(); // Tests fails if no exception is thrown after searching for a path between two null references.
    } catch (NullPointerException e) {

    } catch (Exception e) {
      fail(); // If any exception other than a NullPointerException is thrown, test fails.
    }

    // Checks to see if the correct results are returned when prompted for a path between West Ruislip and Epping.
    try {
      if (back.shortestTimeCost(new Station("\"West Ruislip\""), new Station("\"Epping\"")) != 79)
        fail();
      if (!back.shortestTimePath(new Station("\"West Ruislip\""), new Station("\"Epping\""))
          .toString().equals(
              "[\"West Ruislip\", \"Ruislip Gardens\", \"South Ruislip\", \"Northolt\", \"Greenford\", "
                  + "\"Perivale\", \"Hanger Lane\", \"North Acton\", \"East Acton\", \"White City\", \"Shepherd's Bush (C)\", "
                  + "\"Holland Park\", \"Notting Hill Gate\", \"Queensway\", \"Lancaster Gate\", \"Marble Arch\", \"Bond Street\", "
                  + "\"Oxford Circus\", \"Tottenham Court Road\", \"Holborn\", \"Chancery Lane\", \"St. Paul's\", \"Bank\", "
                  + "\"Liverpool Street\", \"Bethnal Green\", \"Mile End\", \"Stratford\", \"Leyton\", \"Leytonstone\", "
                  + "\"Snaresbrook\", \"South Woodford\", \"Woodford\", \"Buckhurst Hill\", \"Loughton\", \"Debden\", "
                  + "\"Theydon Bois\", \"Epping\"]"))
        fail();
    } catch (Exception e) {
      fail();
    }
  }

}
