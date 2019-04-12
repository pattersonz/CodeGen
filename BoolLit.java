class BoolLit extends TypeLit implements BG {
	String bool;
  public BoolLit(String b)
  {
    bool = b;
  }

  public String toString(int t)
  {
  	return bool;
  }
  	public FullType getType() throws BaseGrammarException
	{
		return new FullType( new BoolType(), false, true);
	}
}

