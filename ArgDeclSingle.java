class ArgDeclSingle extends ArgDecl implements BG {
  public ArgDeclSingle(Type t, String i)
  {
    super(t, i);
  }

  public String toString(int t)
  {
  	return super.toString(t);
  }

  public FullType getType() throws Exception
  {
  	return new FullType(type, false, false);
  }

}

