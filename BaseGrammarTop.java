import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
abstract class BaseGrammarTop implements BG{

  protected static DataHash hash;
    protected static File file;
    protected static FileWriter writer;
    protected static int ifCount;
    protected static int whileCount;
    protected static int compareCount;
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
	return output.toUpperCase();
    }

    public String bin(int val, int bits)
    {
	String binary = "";
	for(int i = 0; i < bits; i++)
	    {
		if (val >= Math.pow(2, bits - i - 1))
		    {
			binary += "1";
			val -= Math.pow(2, bits - i - 1);
		    }
		else
		    {
			binary += "0";
		    }
	    }
	System.out.println(binary);
	return binary;
	
    }

}
