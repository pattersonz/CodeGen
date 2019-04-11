class ParenExpr extends Expr implements BG {
	Expr expression;
  public ParenExpr(Expr e)
  {
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
  	return("(" + expression.toString(t) + ")");
  }
}

