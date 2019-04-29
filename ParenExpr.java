class ParenExpr extends Expr implements BG {
	Expr expression;
  public ParenExpr(Expr e)
  {
    expression = e;
  }

  public void analysis() throws BaseGrammarException
  {
  	expression.analysis();
  }

  public FullType getType() throws BaseGrammarException
  {
  	return expression.getType();
  }

  public String toString(int t)
  {
  	return("(" + expression.toString(t) + ")");
  }
    public void gen() throws Exception
    {
	expression.gen();
    }
}

