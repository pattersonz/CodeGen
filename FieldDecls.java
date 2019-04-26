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

    public Integer gen(int scope, Integer sizeBelow) throws Exception
    {
	fieldDeclaration.gen(scope, sizeBelow);
	int addition = fieldDeclaration.dataSize();
	writer.append("lda $0000\nadc #$" + hex(addition) + "\nsta");
	sizeBelow += addition;
	if (fieldDeclarations != null)
	    {
		sizeBelow = fieldDeclarations.gen(scope, sizeBelow);
	    }
	return sizeBelow;
    }
}

