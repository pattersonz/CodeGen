class VoidType extends ReturnType implements BG
{ 

	public VoidType()
	{
	}

	public String toString(int t)
	{
		return "void";
	}

	public FullType getType() throws BaseGrammarException
	{
		return new FullType(this, false, true);
	}
}