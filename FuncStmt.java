abstract class FuncStmt extends NonWhileStmt implements BG{
	String id;
	public FuncStmt(String i)
	{
		id = i;
	}

	abstract public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException;

    abstract public void gen(int scope, Integer sizeBelow, String method) throws Exception;
}
