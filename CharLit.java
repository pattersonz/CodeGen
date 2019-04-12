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
  	public FullType getType() throws BaseGrammarException
	{
		return new FullType( new CharType(), false, true);
	}
}

