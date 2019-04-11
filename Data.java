abstract class Data
{
	String id;
	int scopeLevel;
    boolean isFunction;
    FullType type;
    
    Data(String newId, int sL, FullType t, boolean f)
    {
	id = newId;
	scopeLevel = sL;
	isFunction = f;
	type = t;
    }
    
	String getId()
	{
		return id;
	}

	int getScope()
	{
		return scopeLevel;
	}
    boolean getIsFunction()
    {
	return isFunction;
    }

    FullType returnType()
    {
    	return type;
    }

    abstract public FullType[] returnArgs();
}
