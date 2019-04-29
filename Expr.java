abstract class Expr extends BaseGrammarTop implements BG {

    abstract public FullType getType() throws BaseGrammarException;

    abstract public void analysis() throws BaseGrammarException;

    abstract public void gen() throws Exception;
	
}
