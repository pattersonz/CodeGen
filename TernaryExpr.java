class TernaryExpr extends Expr implements BG {
	Expr condition;
	Expr whenTrue;
	Expr whenFalse;

  public TernaryExpr(Expr a, Expr b, Expr c)
  {
    condition = a;
    whenTrue = b;
    whenFalse = c;
  }

  public String toString(int t)
  {
  	return ("(" + condition.toString(t) + " ? "  + whenTrue.toString(t) + " : " + whenFalse.toString(t) + ")");
  }

  public void analysis() throws Exception
  {
      condition.analysis();
      whenTrue.analysis();
      whenFalse.analysis();
  }

  public FullType getType() throws Exception
  {
    FullType c, a, b;
    c = condition.getType();
    a = whenTrue.getType();
    b = whenFalse.getType();
    if (c.getType().toString(0) == "int" && c.getType().toString(0) == "bool")
      throw new Exception("invalid conditonal statement in ternary expression: " + this.toString(0));
    if (a.getType().toString(0) == b.getType().toString(0))
      throw new Exception("ternary type returns do not match: " +  this.toString(0));
    return a;
  }
}

