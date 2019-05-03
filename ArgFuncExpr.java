class ArgFuncExpr extends FuncExpr implements BG {
	Args arguments;
  public ArgFuncExpr(String i, Args a)
  {
    super(i);
    arguments = a;
  }

  public String toString(int t)
  {
  	return(id + "(" + arguments.toString(t) + ")");
  }

  public void analysis() throws BaseGrammarException
  {
    arguments.analysis();
  }

  public FullType getType() throws BaseGrammarException
  {
    FullType[] args = arguments.getTypes(); 
    return hash.lookup(id, args);
  }

    public void gen(int extra) throws Exception
    {
        //push current ar
	writer.append( "ldx AR\nphx\n");
	int count = arguments.gen(extra);
	//set top + extra as current ar
	writer.append("lda TOP\nclc\nadc #$" + hex(extra) + "\nsta AR\n");
	//increment top to be at the top of arg decls
	writer.append("lda TOP\nclc\nadc #$" + hex(count) + "\nsta TOP\n");
	//jmp
	writer.append("jsr method_" + id + "\n" +
		      //set old ar as top minus the extra (to restore)
		      "lda AR\n\nsec\nsbc #$" + hex(extra) + "\nsta TOP\n"+
		      //pull current ar
		      "ply\nsty AR\n" +
		      //push x for val return
		      "phx\n");
    }
}

