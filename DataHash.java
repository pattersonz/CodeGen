import java.util.Random;

class DataHash
{
    final int size = 10000;
    DataList[] table;
    int seed;

    DataHash()
    {
	table = new DataList[size];
	Random rand = new Random(System.currentTimeMillis());
	seed = rand.nextInt();
    }
    
    void insert(String id, FullType t, int scope) throws BaseGrammarException
    {
	int spot = hashFunction(id);
	int endLoc = spot;
	boolean foundSpot = false;
	while (!foundSpot)
	    {
		if (table[spot] == null)
		    {
			Data newVar = new VarData(id, t, scope);
			table[spot] = new DataList(newVar, null);
			foundSpot = true;
		    }
		    else
			{
			    if (table[spot].getId().equals(id))
				{
				    if (table[spot].getScope() == scope)
					{
					    throw new BaseGrammarException(id + " already defined");
						
					}
				    Data newVar = new VarData(id, t, scope);
				    table[spot] = new DataList(newVar, table[spot]);
				    foundSpot = true;
				}
			    else
				{
				    spot = (spot + 1) % size;
				    if (spot == endLoc)
					throw new BaseGrammarException("Hash is full");
				}
			}
	    } 
    }
    
    void insert(String id, ReturnType r, FullType[] args, int scope) throws BaseGrammarException
	{
		int spot = hashFunction(id);
		int endLoc = spot;
		boolean foundSpot = false;
		FullType t = new FullType(r, false, false);
		while (!foundSpot)
		{
		    if (table[spot] == null)
		    {
			Data newFunc = new FunctionData(id, t, args, scope);
			 table[spot] = new DataList(newFunc, (DataList)null);
			 foundSpot = true;
		    }
		    else
		    {
			if ( table[spot].getId().equals(id) )
			    {
				if (table[spot].getScope() == scope)
				    throw new BaseGrammarException(id + " already defined");
				Data newFunc = new FunctionData(id, t, args, scope);
				table[spot] = new DataList(newFunc, table[spot]);
				foundSpot = true;
			    }
			else
			    {
				spot = (spot + 1) % size;
				if (spot == endLoc)
				    throw new BaseGrammarException("Hash is full");
			    }
		    }
		} 
	}

    FullType lookup(String id) throws BaseGrammarException
    {
	int spot = hashFunction(id);
	int endLoc = spot;
	boolean found = false;
	    while (!found)
		{
		    if (table[spot]  == null)
			throw new BaseGrammarException( id + " not declared");
		    else if (table[spot].getId().equals(id))
			{
			    if (table[spot].getIsFunction())
					throw new BaseGrammarException("Invalid use of function: " + id);
			    else
					found = true;
			}
		    else
			{
			    spot = (spot + 1) % size;
			    if (spot == endLoc)
				throw new BaseGrammarException( id + " not declared");
			}
		}
		return table[spot].returnType();
    }

    FullType lookup(String id, FullType[] args) throws BaseGrammarException
    {
    	FullType in = new FullType(new IntType(), false, false), bo = new FullType(new BoolType(), false, false), fl = new FullType(new FloatType(), false, false);
	int spot = hashFunction(id);
	int endLoc = spot;
	boolean found = false;
	    while (!found)
		{
		    if (table[spot]  == null)
			throw new BaseGrammarException( id + " not declared");
		    else if (table[spot].getId().equals(id))
			{
			    if (!table[spot].getIsFunction())
				throw new BaseGrammarException( id + " cannot be used in this way");
			    else
			        {
				    FullType[] listArgs = table[spot].getArgs();
				    if (listArgs == null && args == null)
				    	return table[spot].returnType();
				    else if (listArgs == null || args == null)
				    	throw new BaseGrammarException("incorrect number of arguments in " + id + "()");
				    if (listArgs.length != args.length)
						throw new BaseGrammarException("incorrect number of arguments in " + id + "( )");
				    else
					{
					    for (int i = 0; i < listArgs.length; i++)
						{
							if (! (listArgs[i].equals(args[i]) ||
									(  args[i].equals(in) && listArgs[i].equals(bo)) ||
									(  args[i].equals(in) && listArgs[i].equals(fl))
									))
							throw new BaseGrammarException("function argument type mismatch expected: " +
								listArgs[i].toString(0) + " got: " + args[i].toString(0));
						}
					    found = true;
					}
				}
			}
		    else
			{
			    spot = (spot + 1) % size;
			    if (spot == endLoc)
				throw new BaseGrammarException(id + " not declared");
			}
		}
		return table[spot].returnType();
    }

    void leaveScope(int scope)
    {
	for (int i = 0; i < size; i++)
	    {
		if(table[i] != null && table[i].getScope() >= scope)
		    {
			DataList childList = table[i].getNext();
			if (childList == null)
			    table[i] = null;
			else
			    table[i] = childList;
		    }
	    }
    }

    int hashFunction(String key)
    {
	int location = seed;
	for (int i = 0; i <= key.length() / 4; i++)
	    {
		int[] rep = new int[4];
		int charRep;
		for (int j = 0; j < 4; j++)
		    {
			if (j + i*4 < key.length())
			    rep[j] = (int)key.charAt(i*4 + j);
			else
			    rep[j] = (int)'+';
		    }
		charRep = (rep[2] << 24) + (rep[3] << 16)  + (rep[0] << 8) + rep[1];
		int charRepL, charRepR;
		charRepR = charRep & (int)((2 << 16) - 1);
		charRepL = charRep & (int)(((2 << 16) - 1) << 16 );
		charRep = charRep ^ (charRepL >> 3) ^ (charRepR << 3 );
		location = location ^ charRep;
	    }
	    if (location < 0)
	    	location *= -1;
	return location % size;
    }
    //------------------------------------
    //code generation section  starts here
    //------------------------------------
    void insert(String id, FullType t, int scope, Integer sizeBelow)
    {
	int spot = hashFunction(id);
	int endLoc = spot;
	boolean foundSpot = false;
	while (!foundSpot)
	    {
		if (table[spot] == null)
		    {
			Data newVar = new VarData(id, t, scope, sizeBelow);
			table[spot] = new DataList(newVar, null);
			foundSpot = true;
		    }
		    else
			{
			    if (table[spot].getId().equals(id))
				{
				    Data newVar = new VarData(id, t, scope, sizeBelow);
				    table[spot] = new DataList(newVar, table[spot]);
				    foundSpot = true;
				}
			    else
				{
				    spot = (spot + 1) % size;
				}
			}
	    } 
    }

    public VarData getVar() throws Exception
	{
	    int spot = hashFunction(id);
	    int endLoc = spot;
	    boolean found = false;
	    while (!found)
		{
		    if (table[spot].getId().equals(id))
			{
			    found = true;
			}
		    else
			{
			    spot = (spot + 1) % size;
			}
		}
	    if (!( table[spot] instanceof VarData))
		throw new Exception("Hash broke");
	    return (VarData)table[spot];
    }

}
