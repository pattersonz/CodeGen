class FieldStart extends BaseGrammarTop implements BG
{
	Type type;
	String id;

	public FieldStart(Type t, String i)
	{
		type = t;
		id = i;
	}

	public String toString(int t)
	{
    	return( type.toString(t) + " " + id);
	}
}