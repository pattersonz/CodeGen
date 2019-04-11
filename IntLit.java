class IntLit extends TypeLit implements BG {
	int integer;
  public IntLit(int i)
  {
    integer = i;
  }

  public String toString(int t)
  {
  	return Integer.toString(integer);
  }

		public FullType getType() throws Exception
	{
		return new FullType(new IntType(), false, true);
	}
}

