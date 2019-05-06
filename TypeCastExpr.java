class TypeCastExpr extends ActionExpr implements BG {
	Type type;
  Expr expression;
  public TypeCastExpr(Type t, Expr e)
  {
    type = t;
    expression = e;
  }

  public void analysis() throws BaseGrammarException
  {
    expression.analysis();
  }

  public FullType getType() throws BaseGrammarException
  {
    return new FullType(type, false, true);
  }

  public String toString(int t)
  {
  	return("((" + type.toString(t) + ")" + expression.toString(t) + ")");
  }

    public void gen(int extra) throws Exception
    {
	expression.gen(extra);
	FullType i = new FullType(new IntType(), false, false), f = new FullType(new FloatType(), false, false);
	if (!expression.getType().equals(f))
	    {
		if (type.toString(0).equals("float"))
		    {
			writer.append("plx\njsr IntToFloat\nphx\n");
		    }
	    }
	else if (expression.getType().equals(f))
	    {
		if (!type.toString(0).equals("float"))
		    {
			writer.append("plx\njsr FloatToInt\nphx\n");
		    }
	    }
    }
}

