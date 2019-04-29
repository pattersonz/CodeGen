class UnaryStmt extends NonWhileStmt implements BG {
	Name name;
  String operator;
  public UnaryStmt(Name n, String o)
  {
    name = n;
    operator = o;
  }

  public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
  {
    name.analysis();
    FullType t = name.getType();
      if(t.getArray())
      throw new BaseGrammarException("cannot increment/decrement array variables: " + this.toString(0).replace("\n",""));
      if (!t.equals(new FullType(new FloatType(), false, false)) && !t.equals(new FullType(new IntType(), false, false)))
      throw new BaseGrammarException("Cannot increment/decrement variable of type: " + t.getType().toString(0) + " line: " +  this.toString(0).replace("\n",""));
    return false;
  }

  public String toString(int t)
  {
  	return(T(t) + name.toString(t) + operator + "\n");
  }

    public void gen(int scope, Integer sizeBelow, String method) throws Exception
    {
	name.getValue();
	writer.append("plx\n");
	if (operator.equals("++"))
	    writer.append("inx\n");
	else
	    writer.append("dex\n");
	writer.append("phx\n");
	name.asnValue();
	    
    }
}

