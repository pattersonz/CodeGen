class AssmntStmt extends NonWhileStmt implements BG {
	Name name;
  Expr expression;
  public AssmntStmt(Name n, Expr e)
  {
    name = n;
    expression = e;
  }

    public boolean analysis(int scope, ReturnType rt) throws Exception
    {
	expression.analysis();
	name.analysis();
	FullType typeGot = hash.lookup(name.id);
	if (typeGot.getType().toString(0) != expression.getType().getType().toString(0))
	    throw new Exception("type mismatch between " + name.toString(0) + " and "
				+ expression.toString(0));
	if (typeGot.getFinal())
	    throw new Exception("Final type value can not be reassigned! " + this.toString(0));

	if (typeGot.getArray() != name.wasDeref())
	    throw new Exception("invalid array access: " + this.toString(0));
    return false;
    }
    
  public String toString(int t)
  {
  	return(T(t) + name.toString(t) + " = " + expression.toString(t) + ";\n");
  }
}

