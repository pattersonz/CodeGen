class VoidReturn extends NonWhileStmt implements BG {
  public VoidReturn()
  {
  }

public boolean analysis(int scope, ReturnType rt) throws Exception
  {
  	if (rt.toString(0) == "void")
  		throw new Exception("no return type specified for non void function");
    return true;
  }

  public String toString(int t)
  {
  	return(T(t) + "return;\n");
  }
}

