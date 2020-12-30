
public class Station implements Comparable<Station>{
  private Integer stationID; 
  private String stringName; 
  // private String stationDescr; 
  // private String stationLine; 
  private Double longitude; 
  private Double latitude; 
  private Double zone; 
  private Integer totalLines; 
  private Integer rail; 
  
  public Station() {
    this.stationID = 0; 
    this.stringName = ""; 
    // this.stationLine = ""; 
    this.longitude = 0.0; 
    this.latitude = 0.0;
    this.zone = 0.0; 
    this.totalLines = 0; 
    this.rail = 0; 
  }
  
  public Station(String stringName) {
    this.stationID = 0; 
    this.stringName = stringName; 
    // this.stationLine = ""; 
    this.longitude = 0.0; 
    this.latitude = 0.0;
    this.zone = 0.0; 
    this.totalLines = 0; 
    this.rail = 0;
  }
  
  public Station(Integer stationID, String stringName, 
      Double longitude, Double latitude, Double zone, Integer totalLines, 
      Integer rail) {
    this.stationID = stationID; 
    this.stringName = stringName; 
    // this.stationLine = stationLine; 
    this.longitude = longitude; 
    this.latitude = latitude; 
    this.zone = zone; 
    this.totalLines = totalLines; 
    this.rail = rail; 
  }
  
  public Integer getStationID() {
    return this.stationID; 
  }
  
  public void setStationID(Integer stationID) {
    this.stationID = stationID; 
  }
  
  public String getStringName() {
    return this.stringName; 
  }
  
  public void setStringName(String stringName) {
    this.stringName = stringName; 
  }
  

  
  public Double getLongitude() {
    return this.longitude; 
  }
  
  public void setLongitude(Double longitude) {
    this.longitude = longitude; 
  }
  
  public Double getLatitude() {
    return this.latitude; 
  }
  
  public void setLatitude(Double latitude) {
    this.latitude = latitude; 
  }
  
  public Double getZone() {
    return this.zone;  
  }
  
  public void setZone(Double zone) {
    this.zone = zone; 
  }
  
  public Integer getTotalLines() {
    return this.totalLines; 
  }
  
  public void setTotalLines(Integer totalLines) {
    this.totalLines = totalLines; 
  }
  
  public Integer getRail() {
    return this.rail; 
  }
  
  public void setRail(Integer rail) {
    this.rail = rail; 
  }
  
  @Override
  public String toString() {
	  return this.getStringName();
  }
  
  public String printStation() {
    String line1 = "Station ID: " + stationID + ", Station Name: " + stringName + "\n"; 
    String line2 = "Longitude: " + longitude + ", Latitude: " + latitude + "\n"; 
    String line3 = "Zone: " + zone + ", Total Lines: " + totalLines + "\n"; 
    String line4 = "Rail: " + rail; 
    
    return line1 + line2 + line3 + line4; 
  }
  
  @Override 
  public int compareTo(Station otherStation) {
    return this.stationID.compareTo(otherStation.getStationID()); 
  }
  
  @Override
  public int hashCode() {
    return stringName.hashCode(); 
  }
  
  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true; 
    }
    
    if (!(o instanceof Station)) {
      return false; 
    }
    Station s = (Station)o; 
    return this.getStringName().equals(s.getStringName()); 
  }
  
  
}