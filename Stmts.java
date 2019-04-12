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

    public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
    {
      boolean temp = false;
     try {
        temp = statement.analysis(scope, rt);
     }
     catch(BaseGrammarException b)
     {
        try{
          if (statements != null) {
            if (temp)
              throw new BaseGrammarException("Error statements are unreachable");
            statements.analysis(scope, rt);
          }
        }
        catch(BaseGrammarException b2)
        {
          b.add(b2);
        }
        hash.leaveScope(scope);
        throw b;
     }
       if (statements != null) {
         if (temp)
           throw new BaseGrammarException("Error statements are unreachable");
         return statements.analysis(scope, rt);
       }
       return temp;
    }
}

