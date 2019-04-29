abstract class IfBackend extends BaseGrammarTop implements BG {

    abstract public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException;
    abstract public void gen(int scope, Integer sizeBelow, int thisIf, String method) throws Exception;
}

