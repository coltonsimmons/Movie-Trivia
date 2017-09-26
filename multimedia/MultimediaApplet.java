package multimedia;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public abstract class MultimediaApplet
  extends JApplet
  implements MultimediaRootPaneContainer
{
  private MultimediaApp app;
  
  public MultimediaApplet(MultimediaApp paramMultimediaApp)
  {
    this.app = paramMultimediaApp;
    setLayout(null);
    paramMultimediaApp.setMultimediaRootPaneContainer(this);
  }
  
  public void destroy()
  {
    if (SwingUtilities.isEventDispatchThread()) {
      this.app.destroy();
    } else {
      try
      {
        SwingUtilities.invokeAndWait(new DestroyRunnable());
      }
      catch (Exception localException) {}
    }
  }
  
  protected MultimediaApp getMultimediaApp()
  {
    return this.app;
  }
  
  public void init()
  {
    if (SwingUtilities.isEventDispatchThread()) {
      this.app.init();
    } else {
      try
      {
        SwingUtilities.invokeAndWait(new InitRunnable());
      }
      catch (Exception localException) {}
    }
  }
  
  public void start()
  {
    if (SwingUtilities.isEventDispatchThread()) {
      this.app.start();
    } else {
      try
      {
        SwingUtilities.invokeAndWait(new StartRunnable());
      }
      catch (Exception localException) {}
    }
  }
  
  public void stop()
  {
    if (SwingUtilities.isEventDispatchThread()) {
      this.app.stop();
    } else {
      try
      {
        SwingUtilities.invokeAndWait(new StopRunnable());
      }
      catch (Exception localException) {}
    }
  }
  
  private class DestroyRunnable
    implements Runnable
  {
    private DestroyRunnable() {}
    
    public void run()
    {
      MultimediaApplet.this.app.destroy();
    }
  }
  
  private class InitRunnable
    implements Runnable
  {
    private InitRunnable() {}
    
    public void run()
    {
      MultimediaApplet.this.app.init();
    }
  }
  
  private class StartRunnable
    implements Runnable
  {
    private StartRunnable() {}
    
    public void run()
    {
      MultimediaApplet.this.app.start();
    }
  }
  
  private class StopRunnable
    implements Runnable
  {
    private StopRunnable() {}
    
    public void run()
    {
      MultimediaApplet.this.app.stop();
    }
  }
}
