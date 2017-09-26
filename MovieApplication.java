
import javax.swing.*;

import multimedia.MultimediaApplication;



public class MovieApplication extends MultimediaApplication
{

  public static void main(String[] args) throws Exception
  {
    SwingUtilities.invokeAndWait(new MovieApplication(args, 1200, 750));
  }

  public MovieApplication(String[] args, int width, int height)
  {
    super(args, new MovieApp(), width, height);
  }

  private void exit()
  {
    int response;
    response = JOptionPane.showConfirmDialog(mainWindow, "Exit this application?", "Exit?",
        JOptionPane.YES_NO_OPTION);
    if (response == JOptionPane.YES_OPTION)
    {
      mainWindow.setVisible(false);
      stop();
      mainWindow.dispose();
    }
  }

}
