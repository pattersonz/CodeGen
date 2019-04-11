class Program extends BaseGrammarTop implements BG {
	String id;
	MemberDecls memberDeclarations;
  public Program(String i, MemberDecls m)
  {
  	id = i;
  	memberDeclarations = m;
	hash = new DataHash();
  }

    public void analysis() throws Exception
    {
	memberDeclarations.analysis();
    }

  public String toString(int t)
  {
  	return("class " + id + "\n{\n" + memberDeclarations.toString(t + 1) + "}\n");
  }
}

