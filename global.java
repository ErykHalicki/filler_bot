import javax.swing.*;
import java.util.Scanner;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.ArrayList;

public class global {
    public static Tile[][] tiles;
    public static board b;
    static display game;
    public static boolean waiting=true;
    public static String select;

    public static void main(String[] args)throws Exception{
        Scanner inp = new Scanner(new File("board.txt"));
        Scanner word = new Scanner(System.in);
        inp.useDelimiter(",");
        String[][] temp=new String[7][8];
        for(int i=0;i<7;i++){
          temp[i]=inp.nextLine().split(",",0);
        }
        tiles=new Tile[7][8];
        for(int y=0;y<temp.length;y++){
          for(int x=0;x<temp[0].length;x++){
            tiles[y][x]=new Tile(temp[y][x],x,y,-1);
          }
        }
        tiles[6][0].taken=0;
        tiles[0][7].taken=1;
        b=new board(tiles, new String[] {tiles[6][0].color,tiles[0][7].color},0);
        initScreen();
        game.tiles=b.layout;
        while(b.over==false){
          String m=bestMove(b);
          System.out.println(m);
          b=b.move(m);
          game.tiles=b.layout;
          int i=0;
          while(true){
            if(game.select!=" " && game.select!=b.PlayerColors[0] && game.select!=b.PlayerColors[1]){
              b=b.move(game.select);
              display.select=" ";
              break;
            }
          }
          game.tiles=b.layout;
          //TimeUnit.SECONDS.sleep(1);
        }
        game.tiles=b.layout;
    }

    public static String bestMove(board boar) throws CloneNotSupportedException{
      board _b=(board)boar.clone();
      String best="R";
      String[] colors=new String[4];
      int[] bestScores=new int[4];
      int bestScore=0;
      if(_b.turn==0){
        bestScore=-1000;
        int l=0;
        for(board n: _b.createMoves()){
          int s=minimax(n,7);
          colors[l]=n.PlayerColors[0];
          bestScores[l]=s;
          if(s>bestScore){
            best=n.PlayerColors[0];
            bestScore=s;
          }
          l++;
        }
      }
      if(_b.turn==1){
        bestScore=1000;
        int l=0;
        for(board n: _b.createMoves()){
          int s=minimax(n,7);
          colors[l]=n.PlayerColors[1];
          bestScores[l]=s;
          if(s<bestScore){
            best=n.PlayerColors[1];
            bestScore=s;
          }
          l++;
        }
      }
      ArrayList<String> options=new ArrayList<String>(4);
      for(int l=0;l<4;l++){
        if(bestScores[l]==bestScore){options.add(colors[l]);}
      }
      return options.get(new Random().nextInt(options.size()));
    }
    public static int minimax(board _b, int d){
      int value;
      if(d==0 || _b.over){
        return _b.netScore;
      }
      if(_b.turn==0){
        value=-1000;
        for(board n: _b.createMoves()){
          value=Math.max(value,minimax(n,d-1));
        }
        return value;
      }
      else{
        value=1000;
        for(board n: _b.createMoves()){
          value=Math.min(value,minimax(n,d-1));
        }
        return value;
      }
    }
    public static void initScreen() throws Exception{
        JFrame window = new JFrame("game");//creates window using JFrame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//sets closing windows with x to end application
        game=new display();
        window.add(game);
        window.addMouseListener(game);
        window.setVisible(true);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
    }
}
