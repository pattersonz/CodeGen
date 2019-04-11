class IfBase extends BaseGrammarTop implements BG {
  Expr expression;
  public IfBase(Expr e)
  {
    expression = e;
  }

    public void analysis() throws Exception
    {
	expression.analysis();
	if (expression.getType().toString(0) == "int" && expression.getType().toString(0) != "bool"  )
	    throw new Exception("condition with type \"" + expression.getType().toString(0)  +"\" cannot be evaluated: " + this.toString(0));
	    
    }
    
  public String toString(int t)
  {
  	return ("if (" + expression.toString(t) + ")\n");
  }
}

