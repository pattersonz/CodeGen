class FieldsNMethods extends BaseGrammarTop implements BG
{
	FieldDecl fieldDeclaration;
	FieldsNMethods fieldsAndMethods;
	MethodDecl methodDeclaration;
	MethodDecls methodDeclarations;

	public FieldsNMethods(FieldDecl f, FieldsNMethods s)
	{
		fieldDeclaration = f;
		fieldsAndMethods = s;
		methodDeclaration = null;
		methodDeclarations = null;
	}

	public FieldsNMethods(MethodDecl m, MethodDecls s)
	{
		fieldDeclaration = null;
		fieldsAndMethods = null;
		methodDeclaration = m;
		methodDeclarations = s;
	}

    public void analysis() throws BaseGrammarException
    {
	if (fieldDeclaration != null)
	    {
	    	try {
				fieldDeclaration.addVar(0);
			}
	    	catch(BaseGrammarException b) {
				String rest = "";
				try {
					if (fieldsAndMethods != null)
						fieldsAndMethods.analysis();
				}
				catch(BaseGrammarException b2)
				{
					b.add(b2);
				}

				throw b;
			}
			if (fieldsAndMethods != null)
				fieldsAndMethods.analysis();
	    }
	else
	    {
	    	try {
				methodDeclaration.addMethod();
			}
	    	catch(BaseGrammarException b)
			{
				String rest = "";
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
    }
    
	public String toString(int t)
	{
		if (methodDeclaration != null)
			return (methodDeclaration.toString(t) + (methodDeclarations != null ? methodDeclarations.toString(t) : "" ));
    	return( fieldDeclaration.toString(t) + (fieldsAndMethods != null ? fieldsAndMethods.toString(t) : "") );
	}

    public void gen(Integer top) throws Exception
    {
	if (methodDeclaration != null)
	    {
		
		writer.append("lda #" + top.toString() + "\nsta $0000\ntcd\njsr method_main\ninfinite:\njmp infinite\n");
		methodDeclaration.gen();
		if (methodDeclarations != null)
		    methodDeclarations.gen();
	    }
	else
	    {
		fieldDeclaration.gen(0, top);
		top += fieldDeclaration.dataSize();
		if (fieldsAndMethods != null)
		    fieldsAndMethods.gen(top);
	    }
    }


}
