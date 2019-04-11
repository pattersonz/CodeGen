class NestedWhileIf extends NestedMatchedWhileIf implements BG{

	NestedMatchedWhileIf nestedMatchedWhile;
	public NestedWhileIf(WhileBase w, NestedMatchedWhileIf n)
	{
		super(w);
		nestedMatchedWhile = n;
	}

	public boolean analysis(int scope, ReturnType rt) throws Exception
	{
		whileBase.analysis();
		nestedMatchedWhile.analysis(scope + 1, rt);
		hash.leaveScope(scope + 1);
		return false;
	}

 	public String toString(int t)
	{
		return( super.toString(t) + T(t) + "{\n" + nestedMatchedWhile.toString(t + 1) + T(t) + "}\n");
	}
}