package AddOns;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Lives that change whenever you are wrong. Sets lives between value of [1-3].
 * @author Colton Simmons
 *
 */
public class Lives extends JPanel
{

  private BufferedImage image;
  private static int x, y;
  private static BufferedImage back;

  /**
   * Default Constructor.
   */
  public Lives(){
    
    super.setLayout(null);
  
    InputStream stream = this.getClass().getClassLoader().getResourceAsStream("Random/life3.png");
    try
    {
      image = ImageIO.read(stream);
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    this.x = image.getWidth(); 
    this.y = image.getHeight();
  }
  
  /**
   * Set the number of lives.
   * @param im
   *    Life to paint to screen
   */
  public void setImage(int im){
    InputStream stream = this.getClass().getClassLoader().getResourceAsStream("Random/life" + im + ".png");
    
    try
    {
      image = ImageIO.read(stream);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    
  
    x = image.getWidth();
    y = image.getHeight();
  }
  
  public BufferedImage getImage(){
    return back;
  }
  @Override
  public Dimension getPreferredSize()
  {
    return new Dimension(x, y);
  }
  
  @Override
  protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.drawImage(image, 0, 0, this); 
      
      
      
  }
}
