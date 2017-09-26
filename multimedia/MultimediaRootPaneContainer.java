package multimedia;
import javax.swing.RootPaneContainer;

public abstract interface MultimediaRootPaneContainer
  extends RootPaneContainer
{
  public abstract String getParameter(String paramString);
}
