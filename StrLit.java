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

  	public FullType getType() throws Exception
	{
		return new FullType(new StrType(), false, true);
	}
}

