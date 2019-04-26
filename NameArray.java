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

    public void asnValue()
    {
	VarData var = hash.getVar(id);
	expression.gen();
	writer.append(
		      //pull index and value to set
		      "ply\nplx\n" + 
		      //push data bank
		      "phd\n" +
		      //push value to set then index
		      "phx\nphy\n");
	if (var.getScope() == 0)
	    {
		writer.append(
		//set data bank to 0
			      "lda #$0000\ntcd\n");
	    }
	    writer.append(
			  //load address of pointer to a
			  "ldx #$" + hex(var.getOffset()) + "\n" +
			  //load address [0] to acc
			  "lda 0, x\n" +
			  //set databank address to acc
			  "tcd\n" +
			  //pull index to acc, then double it, then move to y
			  "pla\nasl a\ntay\n" +
			  //pull value to store into accumulator
			  "pla\n" +
			  //store acc to databank offset to y
			  "sta 0, y\n" +
			  //restore databank
			  "phd\n";
			  );
    }
}
