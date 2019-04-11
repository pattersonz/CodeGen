class ReadList extends BaseGrammarTop implements BG {
	Name name;
  ReadList readList;
  public ReadList(Name n)
  {
    name = n;
    readList = null;
  }

  public ReadList(Name n, ReadList r)
  {
    name = n;
    readList = r;
  }

    public void analysis() throws Exception
    {
	name.analysis();
	FullType f = name.getType();
	if (f.getArray())
	    throw new Exception("Can\'t read un-dereferenced array: " +  this.toString(0));
	if (f.getFinal())
	    throw new Exception("Can\'t assign new value to final variable: " + this.toString(0));
	if (readList != null)
	    readList.analysis();
    }
    
  public String toString(int t)
  {
  	return(name.toString(t) + (readList != null ? ", " + readList.toString(t) : ""));
  }
}
