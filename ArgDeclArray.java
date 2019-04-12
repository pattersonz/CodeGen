class ArgDeclArray extends ArgDecl implements BG {
  
  public ArgDeclArray(Type t, String i)
  {
    super(t, i);
  }

  public String toString(int t)
  {
  	return (super.toString(t) + "[]");
  }

  public FullType getType()throws BaseGrammarException
  {
  	return new FullType(type, true, false);
  }
}

