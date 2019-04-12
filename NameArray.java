class NameArray extends Name implements BG {
	Expr expression;
  public NameArray(String i, Expr e)
  {
    super(i);
    expression = e;
  }

    public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
    {
	expression.analysis();
	FullType type = expression.getType();
	if (!type.equals(new FullType(new IntType(), false, false)))
	    throw new BaseGrammarException("array cannot be accessed with non int value: " +  this.toString(0));
    return false;
    }

    public void analysis() throws BaseGrammarException
    {
      expression.analysis();
        FullType type = expression.getType();
        if (!type.equals(new FullType(new IntType(), false, false)))
      throw new BaseGrammarException("array cannot be accessed with non int value: " +  this.toString(0));
    }
    
  public String toString(int t)
  {
  	return(id + "[" + expression.toString(t) + "]");
  }

    public FullType getType() throws BaseGrammarException
    {
        FullType ft = hash.lookup(id);
        if (!ft.getArray())
            throw new BaseGrammarException("attempt to dereference non-array variable: " +  this.toString(0));
        return new FullType(ft.getType(), false, false);

    }

    public boolean wasDeref()
    {
	return true;
    }
}
