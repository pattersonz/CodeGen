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

  public void addMethods() throws Exception
  {
    methodDeclaration.addMethod();
    if (methodDeclarations != null)
      methodDeclarations.addMethods();
  }

  public String toString(int t)
  {
  	return(methodDeclaration.toString(t) + 
      (methodDeclarations != null ? methodDeclarations.toString(t) : "") );
  }
}

