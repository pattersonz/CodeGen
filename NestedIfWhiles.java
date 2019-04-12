class NestedIfWhiles extends WhileMatchedIf implements BG {

	NestedMatchedWhileIf nestedMatchedWhileIf;

  public NestedIfWhiles(WhileBase w, NestedMatchedWhileIf n)
  {
    super(w);
    nestedMatchedWhileIf = n;
  }

  public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
  {
    whileBase.analysis();
    try {
      nestedMatchedWhileIf.analysis(scope + 1, rt);
      hash.leaveScope(scope + 1);
      return false;
    }
    catch(BaseGrammarException ex)
    {
      hash.leaveScope(scope + 1);
      ex.prepend(whileBase.toString(0).replace("\n",""));
      throw ex;
    }
  }

  public String toString(int t)
  {
  	 return( super.toString(t) + T(t) + "{\n" + nestedMatchedWhileIf.toString(t + 1) + T(t) + "}\n" );
  }
}