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

    public void gen(int scope, Integer sizeBelow, String method) throws Exception
    {
	writer.append("ldx $0000\nphx\n");
	if (fieldDeclarations != null)
	    sizeBelow = fieldDeclarations.gen(scope + 1, sizeBelow);
	if (statements != null)
	    statements.gen(scope + 1, sizeBelow, method);
	hash.leaveScope(scope + 1);
	writer.append("plx\nstx $0000\n");
    }

    
}

