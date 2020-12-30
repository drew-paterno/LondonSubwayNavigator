
public class StationLine implements Comparable<StationLine>{
  private Integer stationLine; 
  private String stationDescr; 
  private String stationColor; 
  private String stripe; 
  
  public StationLine() {
    this.stationLine = 0; 
    this.stationDescr = ""; 
    this.stationColor = ""; 
    this.stripe = ""; 
  }
  
  public StationLine(Integer stationLine, String stationDescr, String stationColor, String stripe) {
    this.stationLine = stationLine; 
    this.stationDescr = stationDescr; 
    this.stationColor = stationColor; 
    this.stripe = stripe; 
  }
  
  public Integer getStationLine() {
    return this.stationLine; 
  }
  
  public void setStationLine(Integer stationLine) {
    this.stationLine = stationLine; 
  }
  
  public String getStationDescr() {
    return this.stationDescr; 
  }
  
  public void setStationDescr(String stationDescr) { 
    this.stationDescr = stationDescr; 
  }
  
  public String getStationColor() {
    return stationColor; 
  }
  
  public void setStationColor(String stationColor) {
    this.stationColor = stationColor; 
  }
  
  public String getStripe() {
    return this.stripe; 
  }
  
  public void setStripe(String stripe) {
    this.stripe = stripe; 
  }
  
  @Override
  public String toString() {
    String line1 = "Line: " + stationDescr + "\n"; 
    String line2 = "Line Color: " + stationColor + ", stripe: " + stripe + "\n"; 
    
    return line1 + line2; 
  }
  
  
  @Override
  public int compareTo(StationLine otherStationLine) {
    return stationLine.compareTo(otherStationLine.getStationLine()); 
  }
}