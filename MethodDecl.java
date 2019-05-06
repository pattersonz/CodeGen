class MethodDecl extends BaseGrammarTop implements BG{

	MethodStart methodStart;
	ArgDecls argumentDeclarations;
	FieldDecls fieldDeclarations;
	Stmts statements;
	Boolean hasSemicolon;

	public MethodDecl(MethodStart m, ArgDecls a, FieldDecls f, Stmts s, OptionalSemi o)
	{
		methodStart = m;
		argumentDeclarations = a;
		fieldDeclarations = f;
		statements = s;
		hasSemicolon = o != null;
	}

	public MethodDecl(MethodStart m, FieldDecls f, Stmts s, OptionalSemi o)
	{
		methodStart = m;
		argumentDeclarations = null;
		fieldDeclarations = f;
		statements = s;
		hasSemicolon = o != null;
	}

	public void addMethod() throws BaseGrammarException
	{
		FullType[] argTypes = null;
		try {
			if (argumentDeclarations != null) {
				argTypes = argumentDeclarations.getTypes();
			}
			hash.insert(methodStart.id, methodStart.returnType, argTypes, 0);
			if (argumentDeclarations != null)
				argumentDeclarations.addVars(1);


			if (fieldDeclarations != null)
				fieldDeclarations.addVars(2);


			boolean alwaysRets = false;
			if (statements != null)
				alwaysRets = statements.analysis(2, methodStart.getType());

			if ( !(methodStart.getType().toString(0) == "void") && !alwaysRets)
				throw new BaseGrammarException("A path exists in method where a value is not returned in function");
			hash.leaveScope(1);
		}
		catch(BaseGrammarException ex) {
			hash.leaveScope(1);
			ex.prepend(methodStart.toString(0));
			throw ex;
		}

	}

	public String toString(int t)
	{
		return( T(t) + methodStart.toString(t) + "(" + 
			( argumentDeclarations != null ? argumentDeclarations.toString(t) : "") 
			+ ")\n" + T(t) +"{\n" + 
			(fieldDeclarations != null ? fieldDeclarations.toString(t + 1) : "") 
			+ (statements != null ? statements.toString(t + 1) : "")
			+ T(t) + "}" + (hasSemicolon ? ";\n" : "\n") );
	}
    
    public void gen() throws Exception
    {
	FullType[] argTypes = null;
	writer.append("method_" + methodStart.id + ":\n");

	if (argumentDeclarations != null) {
	    argTypes = argumentDeclarations.getTypes();
	}

	hash.insert(methodStart.id, methodStart.returnType, argTypes, 0);
	Integer sizeBelow = 0;
	
	if (argumentDeclarations != null)
	    {
		sizeBelow = argumentDeclarations.gen(sizeBelow);
	    }
	
	if (fieldDeclarations != null)
	    sizeBelow = fieldDeclarations.gen(2, sizeBelow);
	
	if (statements != null)
	    statements.gen(2, sizeBelow, methodStart.id);
	hash.leaveScope(1);
	writer.append("method_" + methodStart.id + "_end:\n");
	writer.append("rts\n");
    }
}
