package AddOns;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Class used to update the countdown and paint the current number. Is called using a Timer in the EDT.
 * @author Colton Simmons
 *
 */
public class Countdown extends JPanel
{

  private BufferedImage image;
  private static int x, y;
  private static BufferedImage back;

  /**
   * Default Constructor.
   */
  public Countdown(){
    
    super.setLayout(null);
  
    InputStream stream = this.getClass().getClassLoader().getResourceAsStream("Numbers/9.png");
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
   * Setter for the countdown.
   * @param im
   *     the current number in the countdown
   */
  public void setImage(int im){
    InputStream stream = this.getClass().getClassLoader().getResourceAsStream("Numbers/" + im +".png");
    
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
      //super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      setOpaque(false);
      g2.drawImage(image, 0, 0, this);
      
      
      
  }
}
