
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of back end class that utilizes Dijkstra's Shortest algorithm from Graph.java
 * between two stations and uses the SubwayADT interface.
 * 
 * @author Andy Lin
 *
 */
public class SubwayBackEnd extends Graph<Station> implements SubwayADT {
  private ArrayList<Station> allStations; // contains all stations.
  private ArrayList<Connection> allConnections; // contains all connections.
  private ArrayList<StationLine> allLines; // contains all subwayLines.

  /**
   * Constructor that initializes all vertices and edges, and station lines.
   */
  public SubwayBackEnd() {
    this.allStations = LoadLondonSubwaySystem.loadStation("london.station.csv");
    this.allConnections = LoadLondonSubwaySystem.loadConnection("london.connection.csv");
    this.allLines = LoadLondonSubwaySystem.loadStationLine("london.line.csv");
    allStations.add(new Station(189, "", 0.0, 0.0, 0.0, 0, 0));
    Collections.sort(allStations); // Sorts stations by ID.
    for (Station station : allStations) { // inserts all vertices into the graph.
      this.insertVertex(station);
    }
    for (Connection connection : allConnections) { // creates edges between stations specified by
                                                   // allConnections.
      
      this.insertEdge(allStations.get(connection.getFromStation() - 1),
          allStations.get(connection.getToStation() - 1), connection.getTime());

    }
  }

  /**
   * Adds a station to the graph and allStations array list.
   * 
   * @param station - the given station to add.
   * @return - true if station was successfully added, otherwise false.
   * @throws NullPointerException - when input is null.
   */
  @Override
  public boolean addStation(Station station) throws NullPointerException {
    if (this.insertVertex(station)) {
      allStations.add(station);
      return true;
    } else
      return false;
  }

  /**
   * Adds a connection by creating a edge between two stations and adds it to allConnections.
   * 
   * @param origin      - the station to create a edge from.
   * @param destination - the station to create a edge to.
   * @param time        - the time associated to the two stations to be used as a weight for the
   *                    edge.
   * 
   * @return - true if the connection was successfully added, otherwise false.
   * @throws NullPointerException     - If either origin or destination is null.
   * @throws IllegalArgumentException - If either origin or destination does not exist within the
   *                                  graph.
   */
  @Override
  public boolean addConnection(Station origin, Station destination, int time)
      throws NullPointerException, IllegalArgumentException {
    if (this.insertEdge(origin, destination, time)) {
      allConnections
          .add(new Connection(origin.getStationID(), destination.getStationID(), 0, time));
      return true;
    } else
      return false;
  }

  /**
   * Finds a given station within the graph.
   * 
   * @param station - The given station to search for.
   * @return The given station within the graph.
   * @throws NullPointerException - if the specified station is null.
   */
  @Override
  public Station search(Station station) {
    return this.vertices.get(station).data;
  }

  /**
   * Clears the graph and array lists storing the data.
   */
  @Override
  public void clear() {
    allStations.clear();
    allConnections.clear();
    allLines.clear();
    this.vertices.clear();
  }

  /**
   * Shows the number of stations within the graph.
   * 
   * @return the size of the graph.
   */
  @Override
  public int numStations() {
    return this.getVertexCount();
  }

  /**
   * Returns the number of connections between stations.
   * 
   * @return the number of edges within the graph.
   */
  @Override
  public int numConnections() {
    return this.getEdgeCount();
  }

  /**
   * Shows which stations to take in order to find the minimum time needed to get from origin place
   * to destination.
   * 
   * @param origin      - the station to start the path from.
   * @param destination - the station to look for.
   * @return a list of the data sequence of the path of the origin station to the destination
   *         station.
   * @throw NoSuchElementException - if either origin or destination does not exist within the
   *        graph.
   */
  @Override
  public List<Station> shortestTimePath(Station origin, Station destination) {
    return this.shortestPath(origin, destination);
  }

  /**
   * Shows minimum time needed from origin station to destination station.
   * 
   * @param origin      - the station to start the path from.
   * @param destination - the station to look for.
   * @return The allocated time from origin to destination.
   * @throws NoSuchElementException - if either origin or destination does not exist within the
   *                                graph.
   */
  @Override
  public Integer shortestTimeCost(Station origin, Station destination) {
    return this.getPathCost(origin, destination);
  }

  /**
   * Finds the connection between one station to another station.
   * 
   * @param firstStation  - the station to find the connection from.
   * @param secondStation - the station to find the connection to.
   * @return The connection between firstStation and secondStation if found, otherwise null.
   * @throws NullPointerException - if one or both of the input stations are null.
   */
  @Override
  public Connection getConnection(Station firstStation, Station secondStation) {
    if (this.vertices.contains(firstStation)) {
      for (Edge edge : this.vertices.get(firstStation).edgesLeaving) { // traverses through all the
                                                                       // firstStation's edges
        if (edge.target.equals(this.vertices.get(secondStation))) {
          for (Connection connection : allConnections) { // traverses through all connections if the
                                                         // firstStation's edge led to
                                                         // secondStation.
            if (connection.getFromStation().equals(firstStation.getStationID())
                && connection.getToStation().equals(secondStation.getStationID())) { // return the
                                                                                     // connection
                                                                                     // if
                                                                                     // connection
                                                                                     // was found.
              return connection;
            }
          }
        }
      }
    }
    return null;
  }
  
  /**
   * Prints all stations within the graph.
   */
  @Override
  public void printStations() {
    for (Station s : allStations) {
      if (!s.getStringName().equals(""))
        System.out.println(s.printStation() + "\n");
    }
  }
  
  /**
   * Prints all Connections within the graph.
   */
  @Override
  public void printConnections() {
    for (Connection c : allConnections) {
      System.out.println("From: " + allStations.get(c.getFromStation() - 1).getStringName()
          + " to: " + allStations.get(c.getToStation() - 1).getStringName());
      System.out.print(allLines.get(c.getLine() - 1));
      System.out.println("Time: " + c.getTime() + " minute(s)\n");
    }
  }

}
