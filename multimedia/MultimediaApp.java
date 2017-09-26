package multimedia;
public abstract interface MultimediaApp
{
  public abstract void destroy();
  
  public abstract void init();
  
  public abstract void setMultimediaRootPaneContainer(MultimediaRootPaneContainer paramMultimediaRootPaneContainer);
  
  public abstract void start();
  
  public abstract void stop();
}
