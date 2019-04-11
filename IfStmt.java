class IfStmt extends Stmt implements BG {
  IfBase ifBase;
  IfBackend ifBackend;
  public IfStmt(IfBase i, IfBackend b)
  {
    ifBase = i;
    ifBackend = b;
  }

    public boolean analysis(int scope, ReturnType rt) throws Exception
    {
	   ifBase.analysis();
	   boolean ret = ifBackend.analysis(scope, rt);
     hash.leaveScope(scope);
     return ret;
    }

  public String toString(int t)
  {
  	return ( T(t) + ifBase.toString(t) + T(t) + "{\n" + ifBackend.toString(t + 1) + T(t) + "}\n");
  }
}

