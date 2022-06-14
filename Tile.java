import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.ImageObserver;
public class Tile{
  public int x;
  public int y;
  public int taken=-1;
  public String color;
  Color P=new Color(125,30,125);
  Color R=new Color(125,30,30);
  Color G=new Color(30,125,30);
  Color B=new Color(30,30,125);
  Color b=new Color(5,5,5);
  Color Y=new Color(125,125,30);
  boolean real;
  public Tile(String c, int row, int column, int t){
    x=row;
    y=column;
    taken=t;
    color=c;
    real=true;
  }
  public Tile(String c, int row, int column, int t, boolean r){
    x=row;
    y=column;
    taken=t;
    color=c;
    real=r;
  }
  public void draw(Graphics g, ImageObserver observer) {
    if(color.equals("R")){g.setColor(R);}
    if(color.equals("B")){g.setColor(B);}
    if(color.equals("P")){g.setColor(P);}
    if(color.equals("G")){g.setColor(G);}
    if(color.equals("b")){g.setColor(b);}
    if(color.equals("Y")){g.setColor(Y);}
    if(real){g.fillRect(30+x*30,30+y*30,30,30);}
    else{g.fillRect(30+x*42,270,30,30);}

  }
}
