import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

public class display extends JPanel implements ActionListener, MouseListener{
  private Timer timer;
  public Tile[][] tiles;
  Tile[] selectable;
  public int[] scores;
  volatile static public String select=" ";//volatile keyword because it is being accessed by another thread (global loop)
  public display() throws Exception{
      setPreferredSize(new Dimension(300,330));
      setBackground(new Color(255, 255, 255));
      selectable=new Tile[6];
      selectable[0]=new Tile("R",0,0,-2,false);
      selectable[1]=new Tile("G",1,0,-2,false);
      selectable[2]=new Tile("B",2,0,-2,false);
      selectable[3]=new Tile("Y",3,0,-2,false);
      selectable[4]=new Tile("P",4,0,-2,false);
      selectable[5]=new Tile("b",5,0,-2,false);
      scores=new int[2];
      timer = new Timer(100, this);
      timer.start();
    }
  @Override
  public void actionPerformed(ActionEvent e){
    repaint();
  }
  public void mousePressed(MouseEvent e) {
  }
  public void mouseReleased(MouseEvent e) {
  }
  public void mouseEntered(MouseEvent e) {
  }
  public void mouseExited(MouseEvent e) {
  }
  public void mouseClicked(MouseEvent e) {
    Point p = e.getPoint();
    for(Tile t: selectable){
      if(p.x>=30+t.x*42 && p.x<=60+t.x*42 && p.y>300 &&p.y<330){
        select=t.color;
      }
    }
  }
  @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);
      for(Tile[] i: tiles){
        for(Tile t: i){
          t.draw(g,this);
        }
      }
      for(Tile t: selectable){
        t.draw(g,this);
      }
      Toolkit.getDefaultToolkit().sync();
  }
}
