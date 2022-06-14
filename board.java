import java.util.ArrayList;
public class board implements Cloneable{
  String[] colors={"R","G","B","b","P","Y"};
  public Tile[][] layout;
  public String[] PlayerColors;
  public int turn;
  public board[] states;
  int[] Scores;
  public boolean over;
  public int netScore;

  public board(Tile[][] l,String[] color, int t){//p1= player 1 color
    layout=l;//tile layput of the board
    PlayerColors=color;//[player0,player1]
    turn=t;//0=player 0, 1=player 1
    Scores=calculateScore();
    if(Scores[0]+Scores[1]==56){
      over=true;
    }
    else{
      over=false;
    }
    netScore=Scores[0]-Scores[1];
    //printBoard();
    //System.out.println(Scores[0]+ " "+ Scores[1]);
  }
  @Override
  public Object clone() throws CloneNotSupportedException {
    Tile[][] temp= new Tile[7][8];
    for(int y=0;y<7;y++){
      for(int x=0;x<8;x++){
        temp[y][x]=new Tile(layout[y][x].color,x,y,layout[y][x].taken);
      }
    }
        return new board(temp, PlayerColors, turn);
    }
  public board[] createMoves(){
    //return the 4 possible new game states
    board[] boards=new board[4];
    ArrayList<String> validColors=new ArrayList<String>(4);
    for(int i=0;i<6;i++){
      if(!colors[i].equals(PlayerColors[0])&& !colors[i].equals(PlayerColors[1])){
        validColors.add(colors[i]);
      }
    }
    for(int i=0;i<4;i++){
      boards[i]=move(validColors.get(i));//creates a new board for each possible move
    }
    return boards;
  }

  public int[] calculateScore(){
    int[] scores=new int[2];
    for(Tile[] t:layout){
      for(Tile i: t){
        if(i.taken!=-1){
          scores[i.taken]++;
        }
      }
    }
    return scores;
  }

  public board move(String color){
    int NEWturn;
    Tile[][] NEWlayout= new Tile[7][8];
    for(int y=0;y<7;y++){
      for(int x=0;x<8;x++){
        NEWlayout[y][x]=new Tile(layout[y][x].color,x,y,layout[y][x].taken);
      }
    }
    String[] NEWPlayerColors=new String[2]; //declaring and inittializing the new boards game info
    for(int i=0;i<2;i++){
      if(PlayerColors[i]=="R"){NEWPlayerColors[i]="R";}
      if(PlayerColors[i]=="G"){NEWPlayerColors[i]="G";}
      if(PlayerColors[i]=="B"){NEWPlayerColors[i]="B";}
      if(PlayerColors[i]=="P"){NEWPlayerColors[i]="P";}
      if(PlayerColors[i]=="Y"){NEWPlayerColors[i]="Y";}
      if(PlayerColors[i]=="b"){NEWPlayerColors[i]="b";}
    }
    NEWPlayerColors[turn]=color;
    //capture surrounding squares of the same color and add them to the players captured squares
    for(int h=0;h<5;h++)//run the scan multiple times to make sure no tiles are missed
    {
      for(int y=0;y<7;y++){
        for(int x=0;x<8;x++){
          if(NEWlayout[y][x].color.equals(color) && NEWlayout[y][x].taken==-1){//if this tile is the selected color and is not already captured
            if(y<6){
              if(NEWlayout[y+1][x].taken==turn){//if direction is already captured
                NEWlayout[y][x].taken=turn;//add square to captured squares
              }
            }
            if(y>0){
              if(NEWlayout[y-1][x].taken==turn){
                NEWlayout[y][x].taken=turn;
              }
            }
            if(x<7){
              if(NEWlayout[y][x+1].taken==turn){
                NEWlayout[y][x].taken=turn;
              }
            }
            if(x>0){
              if(NEWlayout[y][x-1].taken==turn){
                NEWlayout[y][x].taken=turn;
              }
            }
          }
        }
      }
    }
    for(int y=0;y<7;y++){
      for(int x=0;x<8;x++){
        if(NEWlayout[y][x].taken==turn){
          NEWlayout[y][x].color=color;
        }
      }
    }
    if(turn==0){NEWturn=1;}
    else{NEWturn=0;}
    return new board(NEWlayout, NEWPlayerColors, NEWturn);
  }
  
}
