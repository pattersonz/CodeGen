class UnaryExpr extends NonTypeCastExpr implements BG {
	String operator;
  Expr expression;
  public UnaryExpr(String o, Expr e)
  {
    operator = o;
    expression = e;
  }

  public void analysis() throws Exception
  {
    expression.analysis();
  }

  public FullType getType() throws Exception
  {
    return expression.getType();
  }

  public String toString(int t)
  {
  	return("(" + operator + expression.toString(t) + ")");
  }
}

