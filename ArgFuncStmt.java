class ArgFuncStmt extends FuncStmt implements BG {
	Args arguments;
  public ArgFuncStmt(String i, Args a)
  {
    super(i);
    arguments = a;
  }

  public String toString(int t)
  {
  	return(T(t) + id + "(" + arguments.toString(t) + ");\n");
  }

  public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
  {
    FullType[] args = arguments.getTypes(); 
    hash.lookup(id, args);
    return false;
  }

    public void gen(int scope, Integer sizeBelow, String method) throws Exception
    {
        //push current ar
	writer.append( "ldx AR\nphx\n");
	int count = arguments.gen(0);
	//set top as current ar
	writer.append("ldx TOP\nstx AR\n");
	//increment top to be at the top of arg decls
	writer.append("lda TOP\nclc\nadc #$" + hex(count) + "\nsta TOP\n");
	//jmp
	writer.append("jsr method_" + id + "\n" +
		      //set old ar as top
		      "lda AR\nsta TOP\n"+
		      //pull current ar
		      "ply\nsty AR\n");
    }
}

