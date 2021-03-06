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

    public void gen(int extra) throws Exception
    {
	
	//these may not evaluate both sides
	if (operator.equals("||"))
	    {
		int cc = compareCount;
		compareCount++;
		//gen left
		leftHandSide.gen(extra);
		//if not zero its valid
		writer.append("plx\ncpx #$0000\nbne isTrueB" + hex(cc) +"\njmp doRight" + hex(cc) + "\nisTrueB" + hex(cc) + ":\njmp compare" + hex(cc) + "\ndoRight" + hex(cc) + ":\n");
		//else gen right
		rightHandSide.gen(extra);
		//either push 1 if valid or 0 if negative
		writer.append("plx\ncpx #$0000\nbne compare" + hex(cc) +"\n");
		writer.append("ldx #$0000\njmp erapmoc" + hex(cc) + "\ncompare" + hex(cc) +
			      ":\nldx #$0001\n\nerapmoc" + hex(cc) + ":\nphx\n");	
	    }
	else if (operator.equals("&&"))
	    {
		//very similar to || but conditions are flipped
		int cc = compareCount;
		compareCount++;
		leftHandSide.gen(extra);
		writer.append("plx\ncpx #$0000\nbeq isFalseB" + hex(cc) +"\njmp doRight" + hex(cc) + "\nisFalseB" + hex(cc) + ":\njmp compare" + hex(cc) + "\ndoRight" + hex(cc) + ":\n");
		rightHandSide.gen(extra);
		writer.append("plx\ncpx #$0000\nbeq compare" + hex(cc) +"\n");
		writer.append("ldx #$0001\njmp erapmoc" + hex(cc) + "\ncompare" + hex(cc) +
			      ":\nldx #$0000\nerapmoc" + hex(cc) + ":\nphx\n");	
	    }
	else
	    {
		//coerc if needed
		FullType l = leftHandSide.getType(), r = rightHandSide.getType(),
		    i = new FullType(new IntType(), false, false),f = new FullType(new FloatType(), false, false);
		if (l.equals(f) || r.equals(f))
		    {
			if (l.equals(f))
			    leftHandSide.gen(extra);
			else
			    {
				TypeCastExpr tc = new TypeCastExpr(new FloatType(), leftHandSide);
				tc.gen(extra);
			    }
			if (r.equals(f))
			    rightHandSide.gen(extra);
			else
			    {
				TypeCastExpr tc = new TypeCastExpr(new FloatType(), rightHandSide);
				tc.gen(extra);
			    }
		    }
		else
		    {
			leftHandSide.gen(extra);
			rightHandSide.gen(extra);
		    }
		
		//these always will
		if (operator.equals("+"))
		    {
			if (l.equals(f) || r.equals(f))
			    {
				writer.append("ply\nplx\njsr FloatAdd\nphx\n");
			    }
			else
			    {
				writer.append("plx\nply\nlda $0000\npha\nstx $0000\ntya\nclc\nadc $0000\nplx\nstx $0000\npha\n");
			    }
		    }
		else if (operator.equals("-"))
		    if (l.equals(f) || r.equals(f))
			    {
				writer.append("pla\nplx\neor #$8000\ntay\njsr FloatAdd\nphx\n");
			    }
			else
			    {
		   writer.append("ply\nplx\nlda $0000\npha\nsty $0000\ntxa\nsec\nsbc $0000\nplx\nstx $0000\npha\n");
			    }
		else if (operator.equals("*"))
		    if (l.equals(f) || r.equals(f))
			    {
				writer.append("ply\nplx\njsr FloatMult\nphx\n");
			    }
			else
			    { 
				writer.append("ply\nplx\njsr Multiply\nphx\n");
			    }
		else if (operator.equals("/"))
		    if (l.equals(f) || r.equals(f))
			    {
				writer.append("ply\nplx\njsr FloatDiv\nphx\n");
			    }
			else
			    { 
				writer.append("ply\nplx\njsr Divide\nphx\n");
			    }
		else if (operator.equals("<"))
		    {
			if (l.equals(f) || r.equals(f))
			    {
				writer.append("plx\nply\njsr FloatGtr\nphx\n");
			    }
			else
			    {
			int cc = compareCount;
			compareCount++;
			writer.append("ply\npla\nldx $0000\nsty $0000\ncmp $0000\nbpl compare" +
				      hex(cc) + "\nlda #$0001\njmp erapmoc" +
				      hex(cc) + "\ncompare" + hex(cc) +
				      ":\nlda #$0000\nerapmoc" + hex(cc) +
				      ":\nstx $0000\npha\n");
			    }
		    }
		else if (operator.equals(">"))
		    {
			if (l.equals(f) || r.equals(f))
			    {
				writer.append("ply\nplx\njsr FloatGtr\nphx\n");
			    }
			else
			    {
			int cc = compareCount;
			compareCount++;
			writer.append("pla\nply\nldx $0000\nsty $0000\ncmp $0000\nbpl compare" +
				      hex(cc) + "\nlda #$0001\njmp erapmoc" +
				      hex(cc) + "\ncompare" + hex(cc) +
				      ":\nlda #$0000\nerapmoc" + hex(cc) +
				      ":\nstx $0000\npha\n");
			    }
		    }
		else if (operator.equals("<="))
		    {
			if (l.equals(f) || r.equals(f))
			    {
				writer.append("ply\nplx\njsr FloatGtr\ntxa\neor #$0001\npha\n");
			    }
			else
			    {
			int cc = compareCount;
			compareCount++;
			writer.append("pla\nply\nldx $0000\nsty $0000\ncmp $0000\nbpl compare" +
				      hex(cc) + "\nlda #$0000\njmp erapmoc" +
				      hex(cc) + "\ncompare" + hex(cc) +
				      ":\nlda #$0001\nerapmoc" + hex(cc) +
				      ":\nstx $0000\npha\n");
			    }
		    }
		else if (operator.equals(">="))
		    {
			if (l.equals(f) || r.equals(f))
			    {
				writer.append("plx\nply\njsr FloatGtr\ntxa\neor #$0001\npha\n");
			    }
			else
			    {
			int cc = compareCount;
			compareCount++;
			writer.append("ply\npla\nldx $0000\nsty $0000\ncmp $0000\nbpl compare" +
				      hex(cc) + "\nlda #$0000\njmp erapmoc" +
				      hex(cc) + "\ncompare" + hex(cc) +
				      ":\nlda #$0001\nerapmoc" + hex(cc) +
				      ":\nstx $0000\npha\n");
			    }
		    }
		else if (operator.equals("=="))
		    {
			int cc = compareCount;
			compareCount++;
			writer.append("ply\npla\nldx $0000\nsty $0000\ncmp $0000\nbeq compare" +
				      hex(cc) + "\nlda #$0000\njmp erapmoc" +
				      hex(cc) + "\ncompare" + hex(cc) +
				      ":\nlda #$0001\nerapmoc" + hex(cc) +
				      ":\nstx $0000\npha\n");
		    }
		else if (operator.equals("<>"))
		    {
			int cc = compareCount;
			compareCount++;
			writer.append("ply\npla\nldx $0000\nsty $0000\ncmp $0000\nbne compare" +
				      hex(cc) + "\nlda #$0000\njmp erapmoc" +
				      hex(cc) + "\ncompare" + hex(cc) +
				      ":\nlda #$0001\nerapmoc" + hex(cc) +
				      ":\nstx $0000\npha\n");
		    }
	    }
    }
}

