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

    public void addVar(int scope) throws BaseGrammarException
    {
	FullType ft = new FullType(fieldStart.type, false, isFinal);
	if (expression != null)
	    {	
		if (! (ft.equals(expression.getType()) ||
				(ft.equals(new FullType(new BoolType(), false, false)) && expression.getType().equals(new FullType(new IntType(), false, false))) ||
				(ft.equals(new FullType(new FloatType(), false, false)) && expression.getType().equals(new FullType(new IntType(), false, false)))  ))
		    throw new BaseGrammarException("declaration expression type mismatch for " + this.toString(0).replace("\n",""));
	    }
	else if (isFinal)
	    {
		throw new BaseGrammarException("Final variable must have expression value: " + this.toString(0));
	    }
		hash.insert(fieldStart.id, ft, scope);
    }
    
	public String toString(int t)
	{
		return( T(t) + (isFinal ? "final " : "") + super.toString(t) + 
			(expression != null ? expression.toString(t) : "") + ";\n");
	}

    public int dataSize()
    {
	return 2;
    }

    public void gen(int scope, Integer sizeBelow) throws Exception
    {
	FullType ft = new FullType(fieldStart.type, false, isFinal);
	hash.insert(fieldStart.id, ft, scope, sizeBelow);
	if (expression != null)
	    {
		expression.gen();
		writer.append("pla\nstx #" + sizeBelow.toString() + "\nsta 0, x\n");
		
	    }	    
	
    }
		    
	

}



