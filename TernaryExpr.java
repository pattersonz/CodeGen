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

  public void analysis() throws BaseGrammarException
  {
      condition.analysis();
      whenTrue.analysis();
      whenFalse.analysis();
  }

  public FullType getType() throws BaseGrammarException
  {
    FullType c, a, b, bool = new FullType(new BoolType(), false, false), i = new FullType(new IntType(), false, false),
            f = new FullType(new FloatType(), false, false);
    c = condition.getType();
    a = whenTrue.getType();
    b = whenFalse.getType();
    if (!c.equals(bool) && !c.equals(i))
      throw new BaseGrammarException("invalid conditonal statement in ternary expression: " + this.toString(0));
    if (! (a.equals(b) ||
            (a.equals(f) && b.equals(i)) || (a.equals(i) && b.equals(f)) ||
            (a.equals(bool) && b.equals(i)) || (a.equals(i) && b.equals(bool)) ))
      throw new BaseGrammarException("ternary type returns do not match: " +  this.toString(0));
    if(a.equals(bool) || b.equals(bool))
      return bool;
    if(a.equals(f) || b.equals(f))
      return f;
    return a;
  }
    public void gen() throws Exception
    {
	int cc = compareCount;
	compareCount++;
	//eval a
	condition.gen();
	//branch if a is 0
	writer.append("plx\ncpx #$0000\nbeq ternaryb" + hex(cc) + "\njmp ternaryS" + hex(cc) +
		      "\nternaryb" + hex(cc) + ":\njmp ternary"+hex(cc)+"\nternaryS" + hex(cc) +
		      ":\n");
	//b
	whenTrue.gen();
	//jmp to end
	writer.append("jmp yranret" + hex(cc) + "\nternary"+hex(cc)+":\n");
	//c
	whenFalse.gen();
	writer.append("yranret"+hex(cc)+":\n");
	//since the value is pushed, and the next action is pulled off the stack there isn't a need to do any further actions
		      
    }
}

