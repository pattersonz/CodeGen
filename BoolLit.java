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
    public void gen() throws Exception
    {
	writer.append("ldx #$" + hex( (bool.equals("true")) ? 1 : 0 ) + "\nphx\n");
    }
}

