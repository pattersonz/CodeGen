class PrintLnStmt extends NonWhileStmt implements BG {
	PrintLnList printLineList;
  public PrintLnStmt(PrintLnList p)
  {
    printLineList = p;
  }

  public boolean analysis(int scope, ReturnType rt) throws Exception
  {
  	return false;
  }

  public String toString(int t)
  {
  	return(T(t) + "printline(" + printLineList.toString(t) + ");\n");
  }
}

