abstract class FuncExpr extends Expr implements BG{
	String id;
	public FuncExpr(String i)
	{
		id = i;
	}

    abstract public void gen() throws Exception;
}
