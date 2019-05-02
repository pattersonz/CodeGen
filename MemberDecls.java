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
		//activation record at $0002
		//joypad raws, helds, press
		//tiles at $0010 through $0810 (2 * 32 * 32)
		Integer top = 2 * (8) + (2*32*32);
		writer.append("main:\nrep #$30\nlda #$0000\ntcd\n"+
			      "lda #$" + hex(top) + "\nsta TOP\n");
		fieldsAndMethods.gen(top);
	    }
    }
}

