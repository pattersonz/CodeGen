abstract class MatchedIf extends BaseGrammarTop implements BG {

    abstract public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException;
    abstract public void gen(int scope, Integer sizeBelow) throws Exception;
}

