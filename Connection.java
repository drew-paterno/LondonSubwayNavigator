
public class Connection implements Comparable<Connection>{
  private Integer fromStation; 
  private Integer toStation; 
  private Integer line; 
  private Integer time; // weight
  
  public Connection() {
    this.fromStation = 0; 
    this.toStation = 0; 
    this.line = 0; 
    this.time = 0; 
  }
  
  public Connection(Integer fromStation, Integer toStation, Integer line, Integer time) {
    this.fromStation = fromStation; 
    this.toStation = toStation; 
    this.line = line; 
    this.time = time; 
  }
  
  public Integer getFromStation() {
    return this.fromStation; 
  }
  
  public void setFromStation(Integer fromStation) {
    this.fromStation = fromStation; 
  }
  
  public Integer getToStation() {
    return this.toStation; 
  }
  
  public void setToStation(Integer toStation) {
    this.toStation = toStation; 
  }
  
  public Integer getLine() {
    return this.line; 
  }
  
  public void setLine(Integer line) {
    this.line = line; 
  }
  
  public Integer getTime() {
    return this.time; 
  }
  
  public void setTime(Integer time) {
    this.time = time; 
  }
  
  @Override
  public String toString() {
    String line1 = "From Station: " + fromStation + ", To Station: " + toStation + "\n"; 
    String line2 = "Line: " + line + ", Time (in minutes): " + time + " minutes\n"; 
    
    return line1 + line2; 
  }
  
  @Override
  public int compareTo(Connection otherConnection) {
    return this.fromStation.compareTo(otherConnection.getFromStation()); 
  }
}