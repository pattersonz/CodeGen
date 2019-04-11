class FieldDeclSingle extends FieldDecl implements BG {

	Boolean isFinal;
	OptionalExpr expression;
	public FieldDeclSingle(OptionalFinal f, FieldStart s, OptionalExpr e)
	{
		super(s);
		isFinal = true;
		expression = e;
	}

	public FieldDeclSingle(FieldStart f, OptionalExpr e)
	{
		super(f);
		isFinal = false;
		expression = e;
	}

    public void addVar(int scope) throws Exception
    {
	FullType ft = new FullType(fieldStart.type, false, isFinal);
	hash.insert(fieldStart.id, ft, scope);
	if (expression != null)
	    {	
		if (expression.getType().toString(0) == fieldStart.type.toString(0))
		    throw new Exception("declaration expression type mismatch for " + fieldStart.type.toString(0) + " " + fieldStart.id);
	    }
	else if (isFinal)
	    {
		throw new Exception("Final variable must have expression value: " + this.toString(0));
	    }
    }
    
	public String toString(int t)
	{
		return( T(t) + (isFinal ? "final " : "") + super.toString(t) + 
			(expression != null ? expression.toString(t) : "") + ";\n");
	}

}



