abstract class Stmt extends BaseGrammarTop implements BG {

    abstract public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException;
    abstract public void gen(int scope, Integer sizeBelow, String method) throws Exception;
}

