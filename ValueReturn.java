class ValueReturn extends NonWhileStmt implements BG {
	Expr expression;
  public ValueReturn(Expr e)
  {
    expression = e;
  }

  public String toString(int t)
  {
  	return(T(t) + "return " + expression.toString(t) + ";\n");
  }

  public boolean analysis(int scope, ReturnType rt) throws Exception
  {
  	expression.analysis();
  	if (expression.getType().getType().toString(0) != rt.toString(0))
  		throw new Exception("invalid type return: " +  this.toString(0));
    return true;
  }
}

