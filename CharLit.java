class CharLit extends TypeLit implements BG {
	String character; //chars may actually be multiple characters in terms of escaping.
  public CharLit(String c)
  {
    character = c;
  }

  public String toString(int t)
  {
  	return character;
  }
  	public FullType getType() throws Exception
	{
		return new FullType( new CharType(), false, true);
	}
}

