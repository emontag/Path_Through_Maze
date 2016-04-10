import java.io.BufferedReader;
import java.io.FileReader;
/**
 * Path through Maze
 * @author Ephraim Montag
 *
 */
public class Project3 {
   static int [][] maze,temp;//2d arrays to hold maze
   static int n,nr,nc;//n size, next row, next column
   static boolean found,backtrack;//variables to keep track of "state"
   public static void main(String[] args) {
      if(args.length==0) System.out.println("No input file! ");//if no file found with argument to main
      else{
         FileReader theFile;//object to file read
         BufferedReader inFile;//use a BufferedReader
         String line="";//empty string to start off line
         try {
            theFile=new FileReader(args[0]);//new FileReader with argument to main
            inFile=new BufferedReader(theFile);//use BufferedReader to read text
            while(true){//while line not return null
               line=inFile.readLine();
               String[] array=line.split(" ");
               n=array.length;//get length of array to use in 2d arrays
               maze=new int[n][n];//make arrays
               temp=new int[n][n];
               for(int i=0;i<n;i++){//place maze numbers in array
                  for(int j=0;j<n;j++){
                     maze[i][j]=Integer.parseInt(array[j]);
                     temp[i][j]=Integer.parseInt(array[j]);
                  }
                  if(i!=n-1){//not overshoot in array
                     line=inFile.readLine();
                     array=line.split(" ");
                  } 
               }
               found=false;//set variable to be path not found 
               FindWay(0,0,n-1,n-1);//finds a path or not
               if(found) printWay(maze);//if there is print it
               else System.out.println("No Path Found!");//otherwise
               if(inFile.readLine()==null) break;//skip empty lines 
               System.out.println();//empty line
            }//end while
         } catch (Exception e) {
            System.err.println(e);//print out any exceptions to console
         }//end catch            
      }//end else
   }//end main
   /**
    * calculates path if there is one through maze
    * @param sr current row
    * @param sc current column
    * @param dr destination row
    * @param dc destination column
    */
   static void FindWay(int sr, int sc, int dr, int dc){ 
      if(sr==dr && sc==dc){//base case
         maze[dr][dc]=2;//set destination as marked
         found=true;//found path
      }
      else{
         temp[sr][sc]=1;//set it as used
         while(!found && PossibleToMove(sr,sc)){//check for places
            backtrack=false;//not currently in backtrack
            FindWay(nr,nc,dr,dc);//recursion
            //if backtracking print
            if(backtrack) System.out.println("Backtracking from ["+nr+", "+nc+"] to ["+sr+", "+sc+"].");
         }
         if(found) maze[sr][sc]=2;//set path if found
      }
   }//end findWay
   /**
    * check if possible to go in any direction
    * otherwise start backtracking
    * sets next row and column 
    * @param sr source row
    * @param sc source column
    * @return
    */
   static boolean PossibleToMove(int sr, int sc){
      if(sc<n-1 && temp[sr][sc+1]==0){//check east first 
         nr=sr;
         nc=sc+1;
         return true;
      }//east
      else if(sr>0 && temp[sr-1][sc]==0){//north next
         nr=sr-1;
         nc=sc;
         return true;
      }//north
      else if(sc>0 &&  temp[sr][sc-1]==0){//then west
        nr=sr;
        nc=sc-1;
        return true;
      }//west
      else if(sr<n-1 &&  temp[sr+1][sc]==0){//last is south
        nr=sr+1;
        nc=sc;
        return true;
      }//south
      else{//if no place to go start backtracking
         //if just started backtracking do:
         if(!backtrack) System.out.println("Start backtracking from ["+sr+", "+sc+"].");
         nr=sr;//set next to current one
         nc=sc;
         backtrack=true;//starting backtrack
         return false;//don't enter while
      }//no opening
   }//end possible move
   /**
    * prints finished maze if found path 
    * like other 2d arrays with nested loop
    * @param maze completed maze
    */
   static void printWay(int [][] maze){
      for(int i=0;i<maze.length;i++){
            for(int j=0;j<maze[i].length;j++){
               System.out.print(maze[i][j]+" ");
            }
            System.out.println();
      } 
   }//end print

}//end class    
		                                                                                                               