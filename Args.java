class Args extends BaseGrammarTop implements BG {
	Expr expression;
  Args arguments;
  public Args(Expr e, Args a)
  {
    expression = e;
    arguments = a;
  }

  public Args(Expr e)
  {
    expression = e;
    arguments = null;
  }

  public FullType[] getTypes() throws BaseGrammarException
  {
    FullType[] f;
    if (arguments != null)
    {
      FullType[] fN = arguments.getTypes();
      f = new FullType[fN.length + 1];
      f[0] = expression.getType();
      for (int i = 0; i < fN.length; i++)
        f[i + 1] = fN[i];
    }
    else
    {
      f = new FullType[1];
      f[0] = expression.getType();
    }
    return f;
  }

  public void analysis()
  {
    return;
  }

  public String toString(int t)
  {
  	return(expression.toString(t) +
	       (arguments != null ? ", " + arguments.toString(t) : "") );
  }

    public void gen(int offset) throws Exception
    {
	expression.gen();
	writer.append("ldx $0002\npla\nsta $" + hex(offset) + ", x\ninc $0000\ninc $0000\n");
	if (arguments != null)
	    arguments.gen(offset + 2);
    }
}
