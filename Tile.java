class Tile extends NonWhileStmt implements BG {
    Expr row, column, value;
    public Tile(Expr a, Expr b, Expr c)
  {
      row = a;
      column = b;
      value = c;
  }

    public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
    {
	row.analysis();
	column.analysis();
	value.analysis();
	FullType rot = row.getType(), ct = column.getType(), vt = value.getType();
	FullType it = new FullType(new IntType(), false, false);
        if (! (rot.equals(it) || ct.equals(it) || vt.equals(it)))
	    throw new BaseGrammarException("tiles are only integer values");
    return false;
    }
    
  public String toString(int t)
  {
      return(T(t) + "_tile( " + row.toString(t) + ", " + column.toString(t) + ", " + value.toString(t) + ");\n");
  }
    
    public void gen(int scope, Integer sizeBelow, String method) throws Exception
    {
        row.gen();
	column.gen();
	value.gen();
	writer.append("pla\nplx\nply\npha\n" +
		      "lda TOP\npha\ntya\n.rept 6\nasl a\n.endr\n" +
		      "tay\ntxa\nasl a\nsty TOP\nclc\nadc TOP\n" +
		      "clc\nadc #$0010\ntax\nply\npla\nsta 0,x\nsty TOP\n");
    }
}
