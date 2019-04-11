class BinaryExpr extends NonTypeCastExpr implements BG {
	Expr leftHandSide;
	String operator;
	Expr rightHandSide;

  public BinaryExpr(Expr l, String b, Expr r)
  {
    leftHandSide = l;
    operator = b;
    rightHandSide = r;
  }

  public void analysis() throws Exception
  {
    leftHandSide.analysis();
    rightHandSide.analysis();
  }

  public FullType getType() throws Exception
  {
    FullType l, r;
    l = leftHandSide.getType();
    r = rightHandSide.getType();
    if (l.getType().toString(0) != r.getType().toString(0))
      throw new Exception("Operands of binary expression are invalid! " + toString(0));
    return l;
  }

  public String toString(int t)
  {
  	return ("(" + leftHandSide.toString(t) + " " + operator + " " + rightHandSide.toString(t) + ")");
  }
}

