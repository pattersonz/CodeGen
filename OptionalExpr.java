class OptionalExpr extends BaseGrammarTop implements BG {

	Expr expression;
	public OptionalExpr(Expr e)
	{
		expression = e;
	}

	public FullType getType() throws BaseGrammarException
	{
		FullType ft;
		ft = expression.getType();
		return ft;
	}

	public String toString(int t)
	{
		return( " = " + expression.toString(t));
	}

    public void gen() throws Exception
    {
	expression.gen();
    }

    public Expr getExp()
    {
	return expression;
    }

}
