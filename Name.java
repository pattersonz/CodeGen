class Name extends Expr implements BG {
  String id;
  public Name(String i)
  {
    id = i;
  }

  public String toString(int t)
  {
  	return(id);
  }

    public boolean analysis(int scope, ReturnType rt) throws Exception
    {
	     return false;
    }

    public void analysis() throws Exception
    {
      return;
    }

    public FullType getType() throws Exception
    {
	   FullType ft = hash.lookup(id);
	   return ft;
       
    }

    public boolean wasDeref()
    {
	   return false;
    }
}
