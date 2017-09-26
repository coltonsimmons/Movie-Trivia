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
 * Game over background panel.
 * @author Colton Simmons
 *
 */
public class GameOver extends JPanel
{

  private BufferedImage image;
  private static int x, y;

  /**
   * Default Constructor.
   */
  public GameOver(){
    
    super.setLayout(null);
  
    InputStream stream = this.getClass().getClassLoader().getResourceAsStream("Random/gameover.jpg");
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
