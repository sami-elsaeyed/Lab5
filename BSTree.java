/*Sami Elsaeyed 11/8/2020 Lab5
 * Class for the Binary search tree. Methods for deleting and inserting. "Find" method deals with averages.
 * "Display" method producs report in an outfile.
 */
import java.io.*;
public class BSTree{
  public Track root;
  public BSTree(){
    root=null;
  }
  //insert method
  public void insert(Track x){
    //call find method on each track to update artist and song plays data on each new insert
    find(x);
    if(root==null){ 
      root=x;
      return;}
    Track finder =root;
    while(finder!=null){
      if(x.track.compareTo(finder.track)>0){
        if(finder.right==null){
          finder.right=x;
          return;}
        finder=finder.right;
      }
       if(x.track.compareTo(finder.track)<0){
         if(finder.left==null){
           finder.left=x;
           return;
         }
        finder=finder.left;
      }
       if(x.track.compareTo(finder.track)==0)
         finder=null;
    }
    return;
  }
  
  public void delete(Track x) throws Exception{
    if(root==null){
      System.out.println("This Song isnt in the Playlist");
      return;
    }
    Track finder =root;
    //delete root
    if(x.track==root.track){
      finder =finder.right;
      //find inorder succeser
      while(finder.left.left!=null){
        finder=finder.left;}
          Track inord =finder.left;
          finder.left=null;
          inord.right=root.right;
          inord.left=root.left;
          root=inord;
          return;
    } 
    while(finder!=null){
      Track end = finder;
      if(x.track.compareTo(finder.track)>0){
        if(x.track==finder.right.track){
          if(finder.right.left!=null&&finder.right.right!=null){
           end=finder.right.right;
           //find inorder succesor
          while(end.left.left!=null){
            end=end.left;}
          Track inord=end.left;
          end.left=null;
          inord.right=finder.right.right;
          inord.left=finder.right.left;
          finder.right=inord;
          return;
          }
          if(finder.right.left!=null){
            finder.right=finder.right.left;
            return;
          }
          else if(finder.right.right!=null){
                 finder.right=finder.right.right;
                 return;
          }
          else{
            finder.right=null;
            return;
          }
        }
        else
          finder=finder.right;
      }
       Track stop = finder;
      if(x.track.compareTo(finder.track)<0){
        if(x.track==finder.left.track){
          if(finder.left.left!=null&&finder.left.right!=null){
          stop=finder.left.right;
          while(stop.left.left!=null){
            stop=stop.left;}
          Track inord=stop.left;
          stop.left=null;
          inord.right=finder.left.right;
          inord.left=finder.left.left;
          finder.left=inord;
          return;}
        
          if(finder.left.left!=null){
            finder.left=finder.left.left;
            return;
          }
          else if(finder.left.right!=null){
                 finder.left=finder.left.right;
                 return;
          }
          else{
            finder.left=null;
            return;
           }
        }
        else
          finder=finder.left;
      }
    }
    System.out.println("This Song isnt in the Playlist fr");
  }//traverses the tree and edits artists and song average information
  public void find(Track x){
    if(root==null)
      return ;
      if(x.track.compareTo(root.track)==0){
        root.avgPlays+=x.avgPlays;
        root.avgArtist+=x.avgArtist;
        return;
      }
      if(x.artist.compareTo(root.artist)==0){
        root.avgArtist+=x.avgArtist;
        x.avgArtist=root.avgArtist;
      }
    Track finder =root;
    while(finder.left!=null ||finder.right!=null){
      if(x.track.compareTo(finder.track)==0){
             finder.avgPlays+=x.avgPlays;
             finder.avgArtist+=x.avgArtist;
             return;}
      if(x.track.compareTo(finder.track)>0){
        if(x.artist.compareTo(finder.artist)==0){
               finder.avgArtist+=x.avgArtist;
               x.avgArtist=finder.avgArtist;
             }
          finder=finder.right;
        }
      
       if(x.track.compareTo(finder.track)<0){
          if(x.artist.compareTo(finder.artist)==0){
               finder.avgArtist+=x.avgArtist;
               x.avgArtist=finder.avgArtist;
               finder=finder.left;
             }
          finder=finder.left;
        }
       return;
    }
  }//Recursively prints each node inord traversal.
  public static void display(Track here,PrintWriter output) throws Exception{
    output.format("%-70s%-20s%-15s%-15s\n","Track Name","Artist Name","Average number if plays in this quarter /week","Average number of plays for artist /week");
    if(here.left==null&&here.right==null){
      output.format("%-70s%-20s%-15.2f%-15.2f\n",here.track,here.artist,here.avgPlays/12,here.avgArtist/12);
      return;
    }
    if(here.left!=null)
      display(here.left,output);
    output.format("%-70s%-20s%-15.2f%-15.2f\n",here.track,here.artist,here.avgPlays,here.avgArtist);
    if(here.right!=null)
      display(here.right,output);
  }
}
  