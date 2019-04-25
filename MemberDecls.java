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
		//create stack top pointer at $0000
		//tileMap adr at $0002
		//tiles at $0004 through $0804 (2 * 32 * 32)
		Integer top = 4 + (2*32*32);
		writer.append("main:\nrep #$30\nldx #$0004\nstx $0002\nlda #$0000\ntcd\n");
		fieldsAndMethods.gen(top);
	    }
    }
}

