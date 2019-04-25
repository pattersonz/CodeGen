class MemberDecls extends BaseGrammarTop implements BG {
	FieldsNMethods fieldsAndMethods;
  public MemberDecls( FieldsNMethods f)
  {
  	fieldsAndMethods = f;
  }

  public String toString(int t)
  {
  	return (fieldsAndMethods != null ? fieldsAndMethods.toString(t) : "");
  }

    public void analysis() throws BaseGrammarException
    {
	if (fieldsAndMethods != null)
      fieldsAndMethods.analysis();
    }

    public void gen() throws Exception
    {
	if (fieldsAndMethods != null)
	    {
		fieldsAndMethods.globalSize();
		//create stack top pointer at $0000
		//tileMap adr at $0002
		//tiles at $0004 through $0804 (2 * 32 * 32)
		top += 4 + (2*32*32) + 2; //plus 2 for the next free space
		writer.append("");
		fieldsAndMethods.gen(top);
	    }
    }
}

