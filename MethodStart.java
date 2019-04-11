class MethodStart extends BaseGrammarTop implements BG
{
	ReturnType returnType;
	String id;

	public MethodStart(ReturnType r, String i)
	{
		returnType = r;
		id = i;
	}

	public String toString(int t)
	{
    	return( returnType.toString(t) + " " + id);
	}

	public ReturnType getType() throws Exception
	{
		return returnType;
	}
}