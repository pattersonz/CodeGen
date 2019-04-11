class Stmts extends BaseGrammarTop implements BG {
  Stmt statement;
  Stmts statements;
  public Stmts(Stmt s, Stmts x)
  {
    statement = s;
    statements = x;
  }

  public String toString(int t)
  {
  	return (statement.toString(t) + (statements != null ? statements.toString(t) : ""));
  }

    public boolean analysis(int scope, ReturnType rt) throws Exception
    {
  	boolean temp = statement.analysis(scope, rt);
  	if (statements != null)
        {
        if (temp)
          throw new Exception("Error statements are unreachable");
         return statements.analysis(scope, rt);
      }
      return temp;
    }
}

