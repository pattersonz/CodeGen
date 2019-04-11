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
}