class VoidFuncExpr extends FuncExpr implements BG {
  public VoidFuncExpr(String i)
  {
    super(i);
  }

  public String toString(int t)
  {
  	return(id + "()");
  }

  public void analysis() throws Exception
  {
  	return;
  }

  public FullType getType() throws Exception
  {
  	return hash.lookup(id, null);
  }
}

