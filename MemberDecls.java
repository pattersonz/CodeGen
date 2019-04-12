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
      fieldsAndMethods.analysis();
    }
}

