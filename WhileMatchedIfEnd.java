class WhileMatchedIfEnd extends WhileMatchedIf implements BG {

  IfBase ifBase;
  MatchedIf interiorIf;
  MatchedIf otherSideOfElse;

  public WhileMatchedIfEnd (WhileBase w, IfBase i, MatchedIf m, MatchedIf s)
  {
    super(w);
    ifBase = i;
    interiorIf = m;
    otherSideOfElse = s;
  }

  public String toString(int t)
  {
    return( super.toString(t) + T(t) + "{\n" + T(t + 1) + ifBase.toString(t + 1) + T(t + 1) + "{\n" + 
      interiorIf.toString(t + 2) + T(t + 1) + "}\n" + T(t + 1)+ "else\n" + T(t + 1) + "{\n" 
      + otherSideOfElse.toString(t + 2) + T(t + 1) + "}\n" + T(t) + "}\n");
  }

  public boolean analysis(int scope, ReturnType rt) throws Exception
  {
    whileBase.analysis();
    ifBase.analysis();
    interiorIf.analysis(scope + 1, rt);
    hash.leaveScope(scope + 1);
    otherSideOfElse.analysis(scope + 1, rt);
    hash.leaveScope(scope + 1);
    return false;
  }
}