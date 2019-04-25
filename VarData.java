class VarData extends Data
{
    Integer varOffset;
    VarData(String i, FullType t, int s)
    {
	super(i, s, t, false);
	varOffset = 0;
    }

    VarData(String i, FullType t, int s, int o)
    {
	super(i, s, t, false);
	varOffset = o;
    }

    public FullType[] returnArgs()
    {
	return null;
    }
}
