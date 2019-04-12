abstract class ArgDecl extends BaseGrammarTop implements BG {
	Type type;
  String id;
  public ArgDecl(Type t, String i)
  {
    type = t;
    id = i;
  }

  abstract public FullType getType() throws BaseGrammarException;

  public void addVar(int scope) throws BaseGrammarException
  {
    FullType t = getType();
    hash.insert(id, t, scope);
  }

  public String toString(int t)
  {
  	return( type.toString(t) + " " + id);
  }
}

