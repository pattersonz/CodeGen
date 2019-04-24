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
    }

    public void gen() throws Exception
    {
	hash = new StackHash();
	file = new File("snes/main.asm");
	writer = new FileWriter(file);
	writer.write("");
	writer.flush();
	writer.append(".incude\"setup.inc\"\n");
	memberDeclarations.gen();
	writer.append(".ENDS\n");
	writer.close();
    }

  public String toString(int t)
  {
  	return("class " + id + "\n{\n" + memberDeclarations.toString(t + 1) + "}\n");
  }
}

