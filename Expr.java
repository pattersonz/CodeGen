abstract class Expr extends BaseGrammarTop implements BG {

    abstract public FullType getType() throws Exception;

    abstract public void analysis() throws Exception;
	
}
