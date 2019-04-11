class OptionalExpr extends BaseGrammarTop implements BG {

	Expr expression;
	public OptionalExpr(Expr e)
	{
		expression = e;
	}

	public ReturnType getType() throws Exception
	{
		FullType ft;
		ft = expression.getType();
		return ft.getType();
	}

	public String toString(int t)
	{
		return( " = " + expression.toString(t));
	}

}