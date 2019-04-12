class IfStmt extends Stmt implements BG {
  IfBase ifBase;
  IfBackend ifBackend;
  public IfStmt(IfBase i, IfBackend b)
  {
    ifBase = i;
    ifBackend = b;
  }

    public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
    {
	   ifBase.analysis();
	   boolean ret = false;
	   try {
         ret = ifBackend.analysis(scope, rt);
       }
	   catch(BaseGrammarException ex)
       {
          ex.prepend(ifBase.toString(0).replace("\n",""));
          hash.leaveScope(scope + 1);
          throw ex;
       }
     return ret;
    }

  public String toString(int t)
  {
  	return ( T(t) + ifBase.toString(t) + T(t) + "{\n" + ifBackend.toString(t + 1) + T(t) + "}\n");
  }
}

