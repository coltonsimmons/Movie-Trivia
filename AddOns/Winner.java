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
 * Winners screen that is painted once all questions were answered.
 * @author Colton Simmons
 *
 */
public class Winner extends JPanel
{

  private BufferedImage image;
  private static int x, y;

  /**
   * Default Constructor.
   */
  public Winner(){
 
    super.setLayout(null);
  
    InputStream stream = this.getClass().getClassLoader().getResourceAsStream("Random/winner.jpg");
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
  

  @Override
  public Dimension getPreferredSize()
  {
    return new Dimension(x, y);
  }
  
  @Override
  protected void paintComponent(Graphics g) {
      //super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.drawImage(image, 0, 0, this); 
      
      
      
  }
}
