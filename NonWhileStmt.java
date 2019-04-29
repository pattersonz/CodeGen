abstract class NonWhileStmt extends NonIfStmt implements BG {

	abstract public String toString(int t);

    abstract public void gen(int scope, Integer sizeBelow, String method) throws Exception;
}

