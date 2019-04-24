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
		int top = fieldsAndMethods.globalSize();
		writer.append("fieldDecls:\nrep $#20\ntsc\nsbc #" + top.toString() +
			      "\ntcs\n"); // set aside space for all global vars
		writer.append("tsc\ntax\nphx\n"); //set the global pointer
		fieldsAndMethods.gen(top);
	    }
    }
}

