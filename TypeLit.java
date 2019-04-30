abstract class TypeLit extends Expr implements BG {

	abstract public FullType getType() throws BaseGrammarException;
	public void analysis() throws BaseGrammarException { return; }

    public void gen(int extra) throws Exception
    {
	gen();
    }
    abstract public void gen() throws Exception;
}

