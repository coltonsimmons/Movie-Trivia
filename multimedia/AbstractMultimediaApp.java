package multimedia;

public abstract class AbstractMultimediaApp
  implements MultimediaApp
{
  protected MultimediaRootPaneContainer rootPaneContainer;
  
  public void destroy() {}
  
  public void init() {}
  
  public void setMultimediaRootPaneContainer(MultimediaRootPaneContainer paramMultimediaRootPaneContainer)
  {
    this.rootPaneContainer = paramMultimediaRootPaneContainer;
  }
  
  public void start() {}
  
  public void stop() {}
}
