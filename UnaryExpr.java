class UnaryExpr extends NonTypeCastExpr implements BG {
	String operator;
  Expr expression;
  public UnaryExpr(String o, Expr e)
  {
    operator = o;
    expression = e;
  }

  public void analysis() throws BaseGrammarException
  {
    expression.analysis();
  }

  public FullType getType() throws BaseGrammarException
  {
    FullType ft = expression.getType();
    FullType iT = new FullType(new IntType(), false, false), fT = new FullType(new FloatType(), false, false), bt = new FullType(new BoolType(), false, false);
    if (ft.getArray())
      throw new BaseGrammarException("Array cannot be used in this way");
    if (!(operator == "~") && !(ft.equals(iT) || ft.equals(fT)))
      throw new BaseGrammarException("Type " + ft.getType().toString(0) + " cannot be incremented/decremented");
    else if (operator == "~" && !(ft.equals(iT) || ft.equals(bt)))
      throw new BaseGrammarException("Type " + ft.getType().toString(0) + " cannot be negated");
    if (operator == "~")
      return new FullType(new BoolType(), false, false);
    return ft;
  }

  public String toString(int t)
  {
  	return("(" + operator + expression.toString(t) + ")");
  }
}

