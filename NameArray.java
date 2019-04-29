class NameArray extends Name implements BG {
	Expr expression;
  public NameArray(String i, Expr e)
  {
    super(i);
    expression = e;
  }

    public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
    {
	expression.analysis();
	FullType type = expression.getType();
	if (!type.equals(new FullType(new IntType(), false, false)))
	    throw new BaseGrammarException("array cannot be accessed with non int value: " +  this.toString(0));
    return false;
    }

    public void analysis() throws BaseGrammarException
    {
      expression.analysis();
        FullType type = expression.getType();
        if (!type.equals(new FullType(new IntType(), false, false)))
      throw new BaseGrammarException("array cannot be accessed with non int value: " +  this.toString(0));
    }
    
  public String toString(int t)
  {
  	return(id + "[" + expression.toString(t) + "]");
  }

    public FullType getType() throws BaseGrammarException
    {
        FullType ft = hash.lookup(id);
        if (!ft.getArray())
            throw new BaseGrammarException("attempt to dereference non-array variable: " +  this.toString(0));
        return new FullType(ft.getType(), false, false);

    }

    public boolean wasDeref()
    {
	return true;
    }

    public void asnValue() throws Exception
    {
	VarData var = hash.getVar(id);
	expression.gen();
	//back up stack top
	    writer.append(";asn val\nplx\nply\nlda $0000\npha\nphy\nphx\n");
	    if (var.getScope() == 0)
		{
		    writer.append("ldx #$0000\n");
		}
	    else
		{
		    writer.append("ldx $0002\n");
		}
	    writer.append("lda $" + hex(var.getOffset()) + ", x\n" +
			  //get index double it, add original offset move into x
			  "sta $0000\npla\nasl a\nclc\nadc $0000\ntax\n" +
			  //grab val to asign, store it at offset, restore $0
			  "pla\nsta $0000, x\nplx\nstx $0000\n");
    }
    
    public void getValue() throws Exception
    {
	VarData var = hash.getVar(id);
	expression.gen();
	//back up stack top
	    writer.append(";getVal\nply\nlda $0000\npha\nphy\n");
	    if (var.getScope() == 0)
		{
		    writer.append("ldx #$0000\n");
		}
	    else
		{
		    writer.append("ldx $0002\n");
		}
	    writer.append("lda $" + hex(var.getOffset()) + ", x\n" +
			  //get index double it, add original offset move into x
			  "sta $0000\npla\nasl a\nclc\nadc $0000\ntax\n" +
			  //grab value at index point, restore $0 push val out
			  "lda $0000, x\nplx\nstx $0000\npha\n");
    }
    
}
