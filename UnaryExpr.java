class UnaryExpr extends NonTypeCastExpr implements BG {
	String operator;
  Expr expression;
  public UnaryExpr(String o, Expr e)
  {
    operator = o;
    expression = e;
  }

  public void analysis() throws BaseGrammarException
  {
    expression.analysis();
  }

  public FullType getType() throws BaseGrammarException
  {
    FullType ft = expression.getType();
    FullType iT = new FullType(new IntType(), false, false), fT = new FullType(new FloatType(), false, false), bt = new FullType(new BoolType(), false, false);
    if (ft.getArray())
      throw new BaseGrammarException("Array cannot be used in this way");
    if (!(operator == "~") && !(ft.equals(iT) || ft.equals(fT)))
      throw new BaseGrammarException("Type " + ft.getType().toString(0) + " cannot be incremented/decremented");
    else if (operator == "~" && !(ft.equals(iT) || ft.equals(bt)))
      throw new BaseGrammarException("Type " + ft.getType().toString(0) + " cannot be negated");
    if (operator == "~")
      return new FullType(new BoolType(), false, false);
    return ft;
  }

  public String toString(int t)
  {
  	return("(" + operator + expression.toString(t) + ")");
  }

    public void gen(int extra) throws Exception
    {
	expression.gen(extra);
	FullType t = expression.getType(), i = new FullType(new IntType(), false, false);
	if (operator.equals("~"))
	    {
		//pull the value, compare with zero, move processor to ac and mask all but zero flag push value
		writer.append("pla\ncmp #$0000\nphp\nsep #$20\npla\nand #$02\nlsr a\ntax\nrep #$20\nphx\n");
	    }
	else if (operator.equals("-"))
	    {
		if (t.equals(i))
		//this works with the concept of 2s compliment. value is flipped bits, then adding 1
		    writer.append("pla\neor #$FFFF\ninc a\npha\n");
		else
		    writer.append("pla\neor #$8000\npha\n");
	    }
	//"+" this character doesn't actually do anything...
    }
}

