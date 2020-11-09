/*Program inputs files and enters data into a binary search tree. The number of plays are accumalated and averaged  
 * for the time period of a quarter. Each artist has an average plays per week for the quarter. 
 * Sami Elsaeyed 11/8/2020 Lab5
 */
import java.io.*;
import java.util.*;
public class Lab5{
  public static void main(String[]args)throws Exception{
    String[] quarter=new String[]{"MusicData0103.csv","MusicData0110.csv","MusicData0124.csv","MusicData1031.csv",
                                  "MusicData0207.csv","MusicData0214.csv","MusicData0221.csv","MusicData0228.csv",
                                  "MusicData0306.csv","MusicData0313.csv","MusicData0320.csv","MusicData0327.csv"};
    BSTree truth = new BSTree();
    for(int k=0;k<quarter.length;k++){
      truth=unique(quarter[k], truth);
    }
    PrintWriter output = new PrintWriter("Artists-Quarter1-2020.txt");
    //Produce report
    truth.display(truth.root,output);
    output.close();
  }//Method enters data into a 2D array then nito the binary search tree
  public static BSTree unique(String s, BSTree truth)throws Exception{
    File file = new File(s);
    String h;
    Scanner scan= new Scanner(file,"UTF-8").useDelimiter("\\s*,\\s*");
    String [][] data=new String[190][4];
    //nested for loop into the 2D array
    for(int i=0;i<190;i++){
      for(int k=0;k<4;k++){
        data[i][k]=scan.next();
      }  
      h=scan.nextLine(); 
      //create track with all associated data
      Track ent =new Track(data[i][1]);
      ent.artist=data[i][2];
      ent.avgPlays=Double.parseDouble(data[i][3]);
      ent.avgArtist=Double.parseDouble(data[i][3]);
      //insert into tree
      truth.insert(ent);
    } 
    return truth;
  } 
} 