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

  public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
  {
    whileBase.analysis();
    try {
      ifBase.analysis();
      try {
        interiorIf.analysis(scope + 1, rt);
        hash.leaveScope(scope + 1);
        try {
          otherSideOfElse.analysis(scope + 1, rt);
          hash.leaveScope(scope + 1);
          return false;
        }
        catch(BaseGrammarException e2)
        {
          e2.prepend("else");
          throw e2;
        }
      }
      catch(BaseGrammarException e1)
      {
        e1.prepend(ifBase.toString(0).replace("\n",""));
        throw e1;
      }
    }
    catch(BaseGrammarException ex)
    {
      hash.leaveScope(scope);
      ex.prepend(whileBase.toString(0).replace("\n",""));
      throw ex;
    }
  }
}