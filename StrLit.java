class StrLit extends TypeLit implements BG {
	String string;
  public StrLit(String s)
  {
    string  = s;
  }

  public String toString(int t)
  {
  	return string;
  }

  	public FullType getType() throws BaseGrammarException
	{
		return new FullType(new StrType(), false, true);
	}

    public void gen() throws Exception
    {
	writer.append("ldx #$0000\nphx\n");
    }
}

