class  ButtonInput extends BaseGrammarTop implements BG {

    char action;
  public ButtonInput(char c)
  {
      action = c;
  }

  public void analysis() throws BaseGrammarException
  {
      return;
  }

  public String toString(int t)
  {
  	return("input");
  }

    public void gen() throws Exception
    {
	String val;
	if (action == 'a')
	    val = "0080";
	else if (action == 'x')
	    val = "0040";
	else if (action == 'l')
	    val = "0020";
	else if (action == 'r')
	    val = "0010";
	else if (action == 'b')
	    val = "8000";
	else if (action == 'y')
	    val = "4000";
	else if (action == 't')
	    val = "2000";
	else if (action == 's')
	    val = "1000";
	else if (action == 'u')
	    val = "0800";
	else if (action == 'd')
	    val = "0400";
	else if (action == 'e')
	    val = "0200";
	else
	    val = "0100";
	writer.append("#$" + val);
    }
}
