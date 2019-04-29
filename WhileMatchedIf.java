abstract class WhileMatchedIf extends WhileStmt implements BG {

  WhileMatchedIf(WhileBase w)
  {
    super(w);
  }

    abstract public void gen(int scope, Integer sizeBelow, String method) throws Exception;
}

