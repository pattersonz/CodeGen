import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
class Program extends BaseGrammarTop implements BG {
	String id;
	MemberDecls memberDeclarations;
  public Program(String i, MemberDecls m)
  {
  	id = i;
  	memberDeclarations = m;
	hash = new DataHash();
	ifCount = 0;
	whileCount = 0;
  }

    public void analysis() throws BaseGrammarException
    {
        try {
            memberDeclarations.analysis();
            FullType rt = hash.lookup("main", null);
        }
        catch(BaseGrammarException ex)
        {
            try {
                hash.lookup("main", null);
            }
            catch (BaseGrammarException e)
            {
                ex.add(e);
            }
            ex.prepend("class<" + id + ">");
            throw ex;
        }
	hash.leaveScope(0);
    }

    public void gen() throws Exception
    {
	file = new File("snes/main.asm");
	writer = new FileWriter(file);
	writer.write("");
	writer.flush();
	writer.append(".include \"setup.inc\"\n");
	memberDeclarations.gen();
	writer.append(".ENDS\n");
	writer.close();
	hash.leaveScope(0);
    }

  public String toString(int t)
  {
  	return("class " + id + "\n{\n" + memberDeclarations.toString(t + 1) + "}\n");
  }
}

