import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
abstract class BaseGrammarTop implements BG{

  protected static DataHash hash;
    protected static File file;
    protected static FileWriter writer;
    protected static int ifCount;
    protected static int whileCount;
  public String T(int t)
  {
  	String tabs = "";
  	for (int i = 0; i < t; i++)
  		tabs = tabs + "\t";
  	return tabs;
  }

  public String toString(int t)
  {
  	return "";
  }

    public void store() throws Exception
    {
	writer.append("pha\nphx\nphy\n");
    }

    public void load() throws Exception
    {
	writer.append("ply\nplx\npla\n");
    }
    public String hex(int val)
    {
	String output = Integer.toHexString(val);
	if (output.length() % 2 == 1)
	    output = "0" + output;
	return output;
    }

}
