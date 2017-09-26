package multimedia;
import java.util.Properties;

public abstract class MultimediaApplication
  extends JApplication
  implements MultimediaRootPaneContainer
{
  private MultimediaApp app;
  private Properties params;
  
  public MultimediaApplication(String[] paramArrayOfString, MultimediaApp paramMultimediaApp, int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    
    this.app = paramMultimediaApp;
    paramMultimediaApp.setMultimediaRootPaneContainer(this);
    
    this.params = new Properties();
    for (int i = 0; i < paramArrayOfString.length; i++) {
      this.params.put(Integer.toString(i), paramArrayOfString[i]);
    }
  }
  
  public void destroy()
  {
    this.app.destroy();
  }
  
  protected MultimediaApp getMultimediaApp()
  {
    return this.app;
  }
  
  public String getParameter(String paramString)
  {
    return this.params.getProperty(paramString);
  }
  
  public void init()
  {
    this.app.init();
  }
  
  public void start()
  {
    this.app.start();
  }
  
  public void stop()
  {
    this.app.stop();
  }
}
