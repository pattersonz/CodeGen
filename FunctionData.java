class FunctionData extends Data
{
	FullType[] argTypes;

    FunctionData(String i, FullType r, FullType[] t, int s)
    {
	super(i, s, r, true);
	argTypes = t;
    }

    public FullType[] returnArgs()
    {
	return argTypes;
    }
}
