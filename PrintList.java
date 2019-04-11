class PrintList extends BaseGrammarTop implements BG {
	Expr expression;
  PrintList printList;
  public PrintList(Expr e)
  {
    expression = e;
    printList = null;
  }

  public PrintList(Expr e, PrintList p)
  {
    expression = e;
    printList = p;
  }

    public void analysis() throws Exception
    {
	expression.analysis();
	if (printList != null)
	    printList.analysis();
    }
    
  public String toString(int t)
  {
  	return(expression.toString(t) + (printList != null ? ", " + printList.toString(t) : ""));
  }
}
