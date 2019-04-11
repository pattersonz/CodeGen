class VarData extends Data
{
    VarData(String i, FullType t, int s)
    {
	super(i, s, t, false);
    }

    public FullType[] returnArgs()
    {
	return null;
    }
}
