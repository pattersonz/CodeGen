class InputMethod extends BaseGrammarTop implements BG {

    char type;
  public InputMethod(char c)
  {
      type = c;
  }

  public void analysis() throws BaseGrammarException
  {
      return;
  }

  public String toString(int t)
  {
      if (type == 'h')
  	return("Held");
      else
	  return("Press"); 
  }

    public void gen() throws Exception
    {
    }
}
