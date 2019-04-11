class UnaryStmt extends NonWhileStmt implements BG {
	Name name;
  String operator;
  public UnaryStmt(Name n, String o)
  {
    name = n;
    operator = o;
  }

  public boolean analysis(int scope, ReturnType rt) throws Exception
  {
    name.analysis();
    FullType t = name.getType();
    if (t.getArray() && !name.wasDeref())
      throw new Exception("cannot increment/decrement array variables: " + this.toString(0));
    if (t.getType().toString(0) == "int" || t.getType().toString(0) == "float")
      throw new Exception("Cannot increment/decrement variable of type: " + t.getType().toString(0) + " line: " +  this.toString(0));
    return false;
  }

  public String toString(int t)
  {
  	return(T(t) + name.toString(t) + operator + "\n");
  }
}

