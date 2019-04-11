class NameArray extends Name implements BG {
	Expr expression;
  public NameArray(String i, Expr e)
  {
    super(i);
    expression = e;
  }

    public boolean analysis(int scope, ReturnType rt) throws Exception
    {
	expression.analysis();
	ReturnType type = expression.getType().getType();
	if (type.toString(0) != "int")
	    throw new Exception("array cannot be accessed with non int value: " +  this.toString(0));
    return false;
    }

    public void analysis() throws Exception
    {
      expression.analysis();
  ReturnType type = expression.getType().getType();
  if (type.toString(0) != "int")
      throw new Exception("array cannot be accessed with non int value: " +  this.toString(0));
    }
    
  public String toString(int t)
  {
  	return(id + "[" + expression.toString(t) + "]");
  }

    public boolean wasDeref()
    {
	return true;
    }
}
