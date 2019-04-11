class ReadStmt extends NonWhileStmt implements BG {
	ReadList readList;
  public ReadStmt(ReadList r)
  {
    readList = r;
  }

  public boolean analysis(int scope, ReturnType rt) throws Exception
  {
  if (readList == null)
      throw new Exception("Read command cannot be null");
  readList.analysis();
  return false;
  }

  public String toString(int t)
  {
  	return(T(t) + "read(" + readList.toString(t) + ");\n");
  }
}

