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

    public void gen() throws Exception
    {
	return;
    }
}

