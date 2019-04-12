class ScopeStmt extends NonWhileStmt implements BG {
	FieldDecls fieldDeclarations;
  Stmts statements;
  Boolean hasSemicolon;
  public ScopeStmt(FieldDecls f, Stmts s, OptionalSemi o)
  {
    fieldDeclarations = f;
    statements = s;
    hasSemicolon = o != null;
  }

  public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
  {
    boolean ret = false;
    if (fieldDeclarations != null)
      fieldDeclarations.addVars(scope + 1);
    if (statements != null)
      ret = statements.analysis(scope + 1, rt);
    hash.leaveScope(scope + 1);
    return ret;
  }

  public String toString(int t)
  {
  	return(T(t) + "{\n" + 
      (fieldDeclarations != null ? fieldDeclarations.toString(t + 1) : "") + 
      (statements != null ? statements.toString(t + 1) : "") + 
      T(t) + "}" + (hasSemicolon ? ";\n" : "\n"));
  }
}

