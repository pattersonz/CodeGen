class IfBase extends BaseGrammarTop implements BG {
  Expr expression;
  public IfBase(Expr e)
  {
    expression = e;
  }

    public void analysis() throws BaseGrammarException
    {
	expression.analysis();
	if (expression.getType().toString(0) == "int" && expression.getType().toString(0) == "bool"  )
	    throw new BaseGrammarException("condition with type \"" + expression.getType().toString(0)  +"\" cannot be evaluated: " + this.toString(0));
	    
    }
    
  public String toString(int t)
  {
  	return ("if (" + expression.toString(t) + ")\n");
  }

    public void gen(int thisIf) throws Exception
    {
	expression.gen();
	writer.append("pla\ncmp #$0000\nbeq elseb_" + Integer.toString(thisIf) + "\n"+
		      "jmp ifS_" + Integer.toString(thisIf) + "\nelseb_" + Integer.toString(thisIf) + ":\njmp else_" + Integer.toString(thisIf) + "\nifS_" + Integer.toString(thisIf) + ":\n");
    }
}

