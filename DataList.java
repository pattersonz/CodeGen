class DataList
{
    
    private Data data;
    private DataList nextList;
    
    DataList()
    {
	data = null;
	nextList = null;
    }

    DataList(Data d, DataList n)
    {
        data = d;
        nextList = n;
    }

    String getId()
    {
	return data.getId();
    }

    int getScope()
    {
	    return data.getScope();
    }
    
    DataList getNext()
    {
	return nextList;
    }
    
    FullType[] getArgs()
    {
	return data.returnArgs();
    }

    FullType returnType()
    {
	return data.returnType();
    }

    boolean getIsFunction()
    {
	return data.getIsFunction();
    }

    Data getData()
    {
	return data;
    }
}
