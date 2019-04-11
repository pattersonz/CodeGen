class VoidType extends ReturnType implements BG
{ 

	public VoidType()
	{
	}

	public String toString(int t)
	{
		return "void";
	}

	public FullType getType() throws Exception
	{
		return new FullType(this, false, true);
	}
}