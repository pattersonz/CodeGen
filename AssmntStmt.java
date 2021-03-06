class AssmntStmt extends NonWhileStmt implements BG {
	Name name;
  Expr expression;
  public AssmntStmt(Name n, Expr e)
  {
    name = n;
    expression = e;
  }

    public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
    {
	expression.analysis();
	name.analysis();
	FullType ft = name.getType();
        if (! (ft.equals(expression.getType()) ||
                (ft.equals(new FullType(new BoolType(), false, false)) && expression.getType().equals(new FullType(new IntType(), false, false))) ||
                (ft.equals(new FullType(new FloatType(), false, false)) && expression.getType().equals(new FullType(new IntType(), false, false)))  ))
	    throw new BaseGrammarException("type mismatch between " + name.toString(0) + " and "
				+ expression.toString(0));
	if (ft.getFinal())
	    throw new BaseGrammarException("Final type value can not be reassigned! " + this.toString(0).replace("\n",""));

    return false;
    }
    
  public String toString(int t)
  {
  	return(T(t) + name.toString(t) + " = " + expression.toString(t) + ";\n");
  }
    
    public void gen(int scope, Integer sizeBelow, String method) throws Exception
    {
	FullType l = name.getType(), r = expression.getType(),  f = new FullType(new FloatType(), false, false);
		if (l.equals(f))
		    {
			if (r.equals(f))
			    expression.gen(0);
			else
			    {
				TypeCastExpr tc = new TypeCastExpr(new FloatType(), expression);
				tc.gen(0);
			    }
		    }
		else
		    expression.gen(0);
	name.asnValue();
    }
}

