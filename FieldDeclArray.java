class FieldDeclArray extends FieldDecl implements BG {

	int intlit;
	public FieldDeclArray(FieldStart f, int n)
	{
		super(f);
		intlit = n;
	}

    public void addVar(int scope) throws BaseGrammarException
    {
	if (intlit <= 0)
	    throw new BaseGrammarException("invalid array size: " + this.toString(0));
	FullType ft = new FullType(fieldStart.type, true, false);
	hash.insert(fieldStart.id, ft, scope);
    }
    
    
	public String toString(int t)
	{
		return( T(t) + super.toString(t) + "[" + intlit + "];\n");
	}
    
    public int dataSize()
    {
	return 2 + intlit * 2;
    }

    public void gen(int scope, Integer sizeBelow) throws Exception
    {
	FullType ft = new FullType(fieldStart.type, true, false);
	hash.insert(fieldStart.id, ft, scope, sizeBelow);
	writer.append("ldx $0000\ntxa\ninc a\ninc a\nsta 0, x\n");	
    }

}
