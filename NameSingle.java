class NameSingle extends Name implements BG {
    public NameSingle(String i)
    {
        super(i);
    }

    public String toString(int t)
    {
        return(id);
    }

    public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
    {
        return false;
    }

    public void analysis() throws BaseGrammarException
    {
        return;
    }

    public FullType getType() throws BaseGrammarException
    {
        FullType ft = hash.lookup(id);
        return ft;

    }

    public boolean wasDeref()
    {
        return false;
    }

    public void asnValue()
    {
	VarData var = hash.getVar(id);
	if (var.getScope() == 0)
	    {
		writer.append("plx\nphd\nlda #$0000\ntcd\nldy $" +
			      hex(var.getOffset()) + "\ntxa\nsta 0, y\npld\n");
	    }
	else
	    {
		writer.append("plx\nldy $" +
			      hex(var.getOffset()) + "\ntxa\nsta 0, y\n");
	    }
    }
}
