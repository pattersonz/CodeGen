class ArgDecls extends BaseGrammarTop implements BG {
	ArgDecl argumentDeclaration;
	ArgDecls argumentDeclarations;
  public ArgDecls(ArgDecl a, ArgDecls s)
  {
  	argumentDeclaration = a;
  	argumentDeclarations = s;
  }

    public ArgDecls(ArgDecl a)
  {
    argumentDeclaration = a;
    argumentDeclarations = null;
  }

  public FullType[] getTypes() throws BaseGrammarException
  {
    FullType[] args;
    if (argumentDeclarations == null)
    {
      args = new FullType[1];
      args[0] = argumentDeclaration.getType();
    }
    else
    {
      FullType[] tempArgs = argumentDeclarations.getTypes();
      args = new FullType[tempArgs.length + 1];
      for (int i = 0; i < tempArgs.length; i++)
        args[i + 1] = tempArgs[i];
      args[0] = argumentDeclaration.getType();
    }
    return args;
  }

  public void addVars(int scope) throws BaseGrammarException
  {
    argumentDeclaration.addVar(scope);
    if (argumentDeclarations != null)
      argumentDeclarations.addVars(scope);
  }

  public String toString(int t)
  {
  	return(argumentDeclaration.toString(t) + (argumentDeclarations != null ? ", " + 
      argumentDeclarations.toString(t) : "") );
  }
}

