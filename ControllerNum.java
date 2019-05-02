class ControllerNum extends BaseGrammarTop implements BG {

    Integer cn;
  public ControllerNum(Integer c)
  {
      cn = c;
  }

  public void analysis() throws BaseGrammarException
  {
      return;
  }

  public String toString(int t)
  {
      return(cn.toString());
  }

    public void gen() throws Exception
    {
	return;
    }
}
