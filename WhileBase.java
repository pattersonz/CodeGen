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

  public void analysis() throws BaseGrammarException
  {
  	expression.analysis();
  	FullType ft = expression.getType();
  	if (ft.getType().toString(0) != "bool" && ft.getType().toString(0) != "int" && !ft.getArray())
  		throw new BaseGrammarException("Invalid type in while condition: " + this.toString(0));
  }

    public void gen(int thisWhile) throws Exception
    {
	expression.gen();
        writer.append("pla\ncmp #$0000\nbeq elihwb_" + Integer.toString(thisWhile) + "\n"+
		      "jmp whileS_" + Integer.toString(thisWhile) + "\nelihwb_" + Integer.toString(thisWhile) + ":\njmp elihw_" + Integer.toString(thisWhile) + "\nwhileS_" + Integer.toString(thisWhile) + ":\n");
    }

    
}

