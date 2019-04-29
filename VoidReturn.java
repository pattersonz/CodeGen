class VoidReturn extends NonWhileStmt implements BG {
  public VoidReturn()
  {
  }

public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
  {
  	if (rt.toString(0) == "void")
  		throw new BaseGrammarException("no return type specified for non void function");
    return true;
  }

  public String toString(int t)
  {
  	return(T(t) + "return;\n");
  }

    public void gen(int scope, Integer sizeBelow, String method) throws Exception
    {
	writer.append("jmp method_" + method + "_end\n");
    }
}

