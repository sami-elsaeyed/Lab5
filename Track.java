//Track class. Two constructors.
public class Track{
  public String track;
  public String artist;
  public double avgPlays;
  public double avgArtist;
  public Track left;
  public Track right;
  public Track(String x){
    track=x;
    left=null;
    right=null;
  }
  public Track(String x,String y,double g ,double h){
    track=x;
    artist=y;
    avgPlays=g;
    avgArtist=h;
} 
}