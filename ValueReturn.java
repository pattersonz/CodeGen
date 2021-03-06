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

  public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
  {
      FullType ft = new FullType(rt, false, false);
  	expression.analysis();
      if (! (ft.equals(expression.getType()) ||
              (ft.equals(new FullType(new BoolType(), false, false)) && expression.getType().equals(new FullType(new IntType(), false, false))) ||
              (ft.equals(new FullType(new FloatType(), false, false)) && expression.getType().equals(new FullType(new IntType(), false, false)))  ))
  		throw new BaseGrammarException("invalid type return: " +  this.toString(0).replace("\n",""));
    return true;
  }
    public void gen(int scope, Integer sizeBelow, String method) throws Exception
    {
	expression.gen();
	writer.append("pla\nldy AR\nsta $0000, y\njmp method_" + method + "_end\n");
    }
}

