class WhileBase extends BaseGrammarTop implements BG {
  Expr expression;
  public WhileBase(Expr e)
  {
    expression = e;
  }

  public String toString(int t)
  {
  	return(T(t) + "while(" + expression.toString(t) + ")\n");
  }

  public void analysis() throws Exception
  {
  	expression.analysis();
  	FullType ft = expression.getType();
  	if (ft.getType().toString(0) == "bool" && ft.getType().toString(0) == "int" && !ft.getArray())
  		throw new Exception("Invalid type in while condition: " + this.toString(0));
  }
}

