abstract class Name extends Expr implements BG {
  String id;
  public Name(String i)
  {
    id = i;
  }

  abstract public String toString(int t);

  abstract public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException;

  abstract  public void analysis() throws BaseGrammarException;


  abstract public FullType getType() throws BaseGrammarException;

    public void gen(int extra) throws Exception
    {
	getValue();
    }

  abstract public boolean wasDeref();

    abstract public void asnValue() throws Exception;

    abstract public void getValue() throws Exception;
}
