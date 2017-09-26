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
 * Background for the quotes.
 * @author Colton Simmons
 *
 */
public class Quote extends JPanel
{

  private BufferedImage image;

  /**
   * Default Constructor.
   */
  public Quote(){
    
    super.setLayout(null);
  
  
    InputStream stream = this.getClass().getClassLoader().getResourceAsStream("Random/popcorn.jpg");
 
    try
    {
      image = ImageIO.read(stream);
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
   
  }
  

  @Override
  public Dimension getPreferredSize()
  {
    return new Dimension(image.getWidth(), image.getHeight());
  }
  
  @Override
  protected void paintComponent(Graphics g) {
      //super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.drawImage(image, 0, 0, this); 
     
      
      
      
  }
}
