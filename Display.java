class Display extends NonWhileStmt implements BG {
    public Display()
  {
  }

    public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
    {
    return false;
    }
    
  public String toString(int t)
  {
      return(T(t) + "_display();\n ");
  }
    
    public void gen(int scope, Integer sizeBelow, String method) throws Exception
    {
	writer.append("jsr SetTiles\nwai\n");
    }
}
