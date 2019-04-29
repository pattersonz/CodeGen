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

    public void asnValue() throws Exception
    {
	VarData var = hash.getVar(id);
	if (var.getScope() == 0)
	    {
		writer.append("plx\nstx $" + hex(var.getOffset()) + "\n");
	    }
	else
	    {
		writer.append("pla\nldy $0002\nsta $" +
			      hex(var.getOffset()) + ", y\n");
	    }
    }

    public void getValue() throws Exception
    {
	VarData var = hash.getVar(id);
	if (var.getScope() == 0)
	    writer.append("ldy $" + hex(var.getOffset()) + "\nphy\n");
	else
	    writer.append("ldx $0002\nlda $" + hex(var.getOffset()) + ", x\npha\n");
    }
}
