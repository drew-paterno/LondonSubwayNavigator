
import java.util.List;

/**
 * Contains an interface of all methods to implement for the backend class
 * 
 * @author Andy Lin
 *
 */
public interface SubwayADT {
  public boolean addStation(Station T) throws NullPointerException; // returns true if station added
  // correctly, otherwise false or a nullpointer if the input is null.

  public boolean addConnection(Station origin, Station destination, int time) // adds an edge
      throws NullPointerException, IllegalArgumentException;
  // between station origin and destination with a certain weight.
  // throws NullPointer if either or both stations or null, and IllegalArgument if either or both
  // stations cannot be found within the graph.

  public Station search(Station station); // searches for a station within the graph.

  public void clear(); // clears all stations.

  public int numStations(); // returns total number of stations.

  public int numConnections(); // returns total number of connections.

  public List<Station> shortestTimePath(Station origin, Station destination); // returns data
  // sequence for shortest path from origin station to destination

  public Integer shortestTimeCost(Station origin, Station destination); // returns the cost of the
  // path from origin station to destination

  public Connection getConnection(Station firstStation, Station secondStation); // returns the
  // connection from  first station to second station
  
  public void printConnections(); // prints all connections within the graph.
  
  public void printStations(); // prints all stations within the graph.
  
  
}

