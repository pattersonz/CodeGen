class MethodDecls extends BaseGrammarTop implements BG {
	MethodDecl methodDeclaration;
	MethodDecls methodDeclarations;

  public MethodDecls(MethodDecl m, MethodDecls s)
  {
  	methodDeclaration = m;
  	methodDeclarations = s;
  }

  public MethodDecls(MethodDecl m)
  {
    methodDeclaration = m;
    methodDeclarations = null;
  }

  public void addMethods() throws BaseGrammarException
  {
      try {
          methodDeclaration.addMethod();
      }
      catch(BaseGrammarException b)
      {
          try {
              if (methodDeclarations != null)
                  methodDeclarations.addMethods();
          }
          catch(BaseGrammarException b2)
          {
              b.add(b2);
          }
          throw b;
      }
      if (methodDeclarations != null)
          methodDeclarations.addMethods();
  }

  public String toString(int t)
  {
  	return(methodDeclaration.toString(t) + 
      (methodDeclarations != null ? methodDeclarations.toString(t) : "") );
  }

    public void gen() throws Exception
    {
	return;
    }
}

