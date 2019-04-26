class ReadStmt extends NonWhileStmt implements BG {
	ReadList readList;
  public ReadStmt(ReadList r)
  {
    readList = r;
  }

  public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
  {
  if (readList == null)
      throw new BaseGrammarException("Read command cannot be null");
  readList.analysis();
  return false;
  }

  public String toString(int t)
  {
  	return(T(t) + "read(" + readList.toString(t) + ");\n");
  }

        public void gen(int scope, Integer sizeBelow) throws Exception
    {
	return;
    }
}

