abstract class Stmt extends BaseGrammarTop implements BG {

    abstract public boolean analysis(int scope, ReturnType rt) throws Exception;
}

