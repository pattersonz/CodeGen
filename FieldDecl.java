abstract class FieldDecl extends BaseGrammarTop implements BG {
	FieldStart fieldStart;

  public FieldDecl(FieldStart f)
  {
  	fieldStart = f;
  }

    abstract public void addVar(int scope) throws BaseGrammarException;
    
  public String toString(int t)
  {
    return fieldStart.toString(t);
  }

}

