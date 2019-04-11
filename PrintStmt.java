class PrintStmt extends NonWhileStmt implements BG {
	PrintList printList;
  public PrintStmt(PrintList p)
  {
    printList = p;
  }

    public boolean analysis(int scope, ReturnType rt) throws Exception
    {
	if (printList != null)
	    printList.analysis();
	else
	    throw new Exception("Can\'t have empty printList");
    return false;
    }
    
  public String toString(int t)
  {
  	return(T(t) + "print(" + printList.toString(t) + ");\n");
  }
}

