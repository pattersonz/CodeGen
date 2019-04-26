class PrintStmt extends NonWhileStmt implements BG {
	PrintList printList;
  public PrintStmt(PrintList p)
  {
    printList = p;
  }

    public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
    {
	if (printList != null)
	    printList.analysis();
	else
	    throw new BaseGrammarException("Can\'t have empty printList");
    return false;
    }
    
  public String toString(int t)
  {
  	return(T(t) + "print(" + printList.toString(t) + ");\n");
  }
        public void gen(int scope, Integer sizeBelow) throws Exception
    {
        return;
    }
}

