class FieldDecls extends BaseGrammarTop implements BG {
	FieldDecl fieldDeclaration;
	FieldDecls fieldDeclarations;

  public FieldDecls(FieldDecl f, FieldDecls s)
  {
  	fieldDeclaration = f;
  	fieldDeclarations = s;
  }

  public FieldDecls(FieldDecl f)
  {
    fieldDeclaration = f;
    fieldDeclarations = null;
  }

  public void addVars(int scope) throws BaseGrammarException
  {
    fieldDeclaration.addVar(scope);
    if (fieldDeclarations != null)
      fieldDeclarations.addVars(scope);
  }

  public String toString(int t)
  {
  	return(fieldDeclaration.toString(t) + (fieldDeclarations != null ? fieldDeclarations.toString(t) : "") );
  }
}

