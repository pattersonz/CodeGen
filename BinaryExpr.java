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

  public void analysis() throws BaseGrammarException
  {
    leftHandSide.analysis();
    rightHandSide.analysis();
  }

  public FullType getType() throws BaseGrammarException
  {
    FullType l, r, i = new FullType(new IntType(), false, false), c= new FullType(new CharType(), false, false)
            , b= new FullType(new BoolType(), false, false), f= new FullType(new FloatType(), false, false),
            s = new FullType(new StrType(), false, false);
    l = leftHandSide.getType();
    r = rightHandSide.getType();
    if (l.getArray() || r.getArray())
      throw new BaseGrammarException("arrays cannot be used in binary operations " + toString(0));

    if (l.equals(s) || r.equals(s) ) {

      if (operator.equals("+") )
        return s;
      throw new BaseGrammarException("invalid operation with strings " + toString(0));
    }
    else if (!(operator != "+") || !(operator != "*") || !(operator != "/") || !(operator != "-"))
    {
      if (!( (l.equals(i) || l.equals(f)) && (r.equals(i) || r.equals(f))))
        throw new BaseGrammarException("Operands of binary expression are incompatible " + toString(0));

      return (l.equals(f) || r.equals(f) ? f : i );
    }
    //binary operators
    else
    {
      if (! (l.equals(r) ||
              (l.equals(f) && r.equals(i)) || (l.equals(i) && r.equals(f)) ||
              (l.equals(b) && r.equals(i)) || (l.equals(i) && r.equals(b)) ))
        throw new BaseGrammarException("Operands can not be compared " + toString(0));

      return b;
    }

  }

  public String toString(int t)
  {
  	return ("(" + leftHandSide.toString(t) + " " + operator + " " + rightHandSide.toString(t) + ")");
  }
}

