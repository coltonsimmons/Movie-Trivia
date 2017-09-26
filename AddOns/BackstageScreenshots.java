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
 * Class used to paint the random screenshot to the Panel.
 * @author Colton Simmons
 *
 */
public class BackstageScreenshots extends JPanel
{

  private BufferedImage image;
  private static int x, y;
  private static BufferedImage back;

  /**
   * Default Constructor.
   */
  public BackstageScreenshots(){
    
    
    
    super.setLayout(null);
  
    InputStream stream = this.getClass().getClassLoader().getResourceAsStream("Random/Backstage.jpg");
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
   * Sets the random image.
   * @param im 
   *      the randomly generated photo to load.
   */
  public void setImage(String im){
    InputStream stream = this.getClass().getClassLoader().getResourceAsStream("Screenshots/" + im);
    
    try
    {
      back = ImageIO.read(stream);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    
  
    x = back.getWidth();
    y = back.getHeight();
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
      //super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.drawImage(image, 0, 0, this); 
      g2.drawRect(310, 80, 590, 450);
      g2.fillRect(310, 80, 590, 450);
      g2.drawImage(back, 315, 85, this);
      
      
      
  }
}
