class FullType
{

	private ReturnType type;
	private boolean isArray;
	private boolean isFinal;

	FullType(ReturnType t, boolean a, boolean f)
	{
		type = t;
		isArray = a;
		isFinal = f;
	}

	ReturnType getType()
	{
		return type;
	}

	boolean getArray()
	{
		return isArray;
	}

	boolean getFinal()
	{
		return isFinal;
	}

	public String toString(int i)
	{
		return (isFinal ? "final " : "") + type.toString(i) + (isArray ? "[]" : "");
	}

	public String typeStr() { return type.toString(0) + (isArray ? "[]" : ""); }

	public boolean equals(FullType f)
	{
		return isArray == f.getArray() && type.toString(0).equals(f.getType().toString(0));
	}
}