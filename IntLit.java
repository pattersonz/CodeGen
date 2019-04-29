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

		public FullType getType() throws BaseGrammarException
	{
		return new FullType(new IntType(), false, true);
	}

    public void gen() throws Exception
    {
	writer.append("ldx #$" + hex(integer) + "\nphx\n");
    }
}

