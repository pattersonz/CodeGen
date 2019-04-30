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
		leftHandSide.gen(extra);
		writer.append("plx\ncpx #$0000\nbne compare" + hex(cc) +"\n");
		rightHandSide.gen(extra);
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
		writer.append("plx\ncpx #$0000\nbeq compare" + hex(cc) +"\n");
		rightHandSide.gen(extra);
		writer.append("plx\ncpx #$0000\nbeq compare" + hex(cc) +"\n");
		writer.append("ldx #$0001\njmp erapmoc" + hex(cc) + "\ncompare" + hex(cc) +
			      ":\nldx #$0000\nerapmoc" + hex(cc) + ":\nphx\n");	
	    }
	else
	    {
		leftHandSide.gen(extra);
		rightHandSide.gen(extra);
		//these always will
		if (operator.equals("+"))
		    writer.append("plx\nply\nlda $0000\npha\nstx $0000\ntya\nclc\nadc $0000\nplx\nstx $0000\npha\n");
		else if (operator.equals("-"))
		   writer.append("ply\nplx\nlda $0000\npha\nsty $0000\ntxa\nsec\nsbc $0000\nplx\nstx $0000\npha\n");
		else if (operator.equals("*"))
		    writer.append("plx\nply\nlda $0000\npha\nstx $0000\ntya\nclc\nadc $0000\nplx\nstx $0000\npha\n");
		else if (operator.equals("/"))
		   writer.append("plx\nply\nlda $0000\npha\nsty $0000\ntxa\nsec\nsbc $0000\nplx\nstx $0000\npha\n");
		else if (operator.equals("<"))
		    {
			int cc = compareCount;
			compareCount++;
			writer.append("ply\npla\nldx $0000\nsty $0000\ncmp $0000\nbpl compare" +
				      Integer.toString(cc) + "\nlda #$0001\njmp erapmoc" +
				      Integer.toString(cc) + "\ncompare" + Integer.toString(cc) +
				      ":\nlda #$0000\nerapmoc" + Integer.toString(cc) +
				      ":\nstx $0000\npha\n");
		    }
		else if (operator.equals(">"))
		    {
			int cc = compareCount;
			compareCount++;
			writer.append("pla\nply\nldx $0000\nsty $0000\ncmp $0000\nbpl compare" +
				      Integer.toString(cc) + "\nlda #$0001\njmp erapmoc" +
				      Integer.toString(cc) + "\ncompare" + Integer.toString(cc) +
				      ":\nlda #$0000\nerapmoc" + Integer.toString(cc) +
				      ":\nstx $0000\npha\n");
		    }
		else if (operator.equals("<="))
		    {
			int cc = compareCount;
			compareCount++;
			writer.append("pla\nply\nldx $0000\nsty $0000\ncmp $0000\nbpl compare" +
				      Integer.toString(cc) + "\nlda #$0000\njmp erapmoc" +
				      Integer.toString(cc) + "\ncompare" + Integer.toString(cc) +
				      ":\nlda #$0001\nerapmoc" + Integer.toString(cc) +
				      ":\nstx $0000\npha\n");
		    }
		else if (operator.equals(">="))
		    {
			int cc = compareCount;
			compareCount++;
			writer.append("ply\npla\nldx $0000\nsty $0000\ncmp $0000\nbpl compare" +
				      Integer.toString(cc) + "\nlda #$0000\njmp erapmoc" +
				      Integer.toString(cc) + "\ncompare" + Integer.toString(cc) +
				      ":\nlda #$0001\nerapmoc" + Integer.toString(cc) +
				      ":\nstx $0000\npha\n");
		    }
		else if (operator.equals("=="))
		    {
			int cc = compareCount;
			compareCount++;
			writer.append("ply\npla\nldx $0000\nsty $0000\ncmp $0000\nbeq compare" +
				      Integer.toString(cc) + "\nlda #$0000\njmp erapmoc" +
				      Integer.toString(cc) + "\ncompare" + Integer.toString(cc) +
				      ":\nlda #$0001\nerapmoc" + Integer.toString(cc) +
				      ":\nstx $0000\npha\n");
		    }
		else if (operator.equals("<>"))
		    {
			int cc = compareCount;
			compareCount++;
			writer.append("ply\npla\nldx $0000\nsty $0000\ncmp $0000\nbne compare" +
				      Integer.toString(cc) + "\nlda #$0000\njmp erapmoc" +
				      Integer.toString(cc) + "\ncompare" + Integer.toString(cc) +
				      ":\nlda #$0001\nerapmoc" + Integer.toString(cc) +
				      ":\nstx $0000\npha\n");
		    }
	    }
    }
}

