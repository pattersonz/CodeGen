import java.lang.Math;

class FloatLit extends TypeLit implements BG {
	double floatingPoint; //the natural parse conversion makes this be a double
  public FloatLit(double f)
  {
    floatingPoint = f;
  }

  public String toString(int t)
  {
  	return Double.toString(floatingPoint);
  }

  	public FullType getType() throws BaseGrammarException
	{
		return new FullType(new FloatType(), false, true);
	}
    public void gen() throws Exception
    {
	String exp, mantissa = "";
	Integer ex;
	for ( ex = 0; ex < 32; ex++)
	    {
		if (floatingPoint <= Math.pow(2, ex - 16))
		    break;
	    }
	System.out.println(ex);
	if (ex != 0)
	    ex--;
	exp = bin(ex,5);
	if (floatingPoint < Math.pow(2, -16))
	    mantissa = "0000000000";
	else
	    {
		double curVal = Math.pow(2, ex - 16);
		floatingPoint -= curVal;
		for (int i = 0; i < 10; i++)
		    {
			curVal = curVal / 2;
			if (floatingPoint >= curVal)
			    {
				mantissa += "1";
				floatingPoint -= curVal;
			    }
			else
			    mantissa += "0";
		    }
		
	    }
	System.out.println("#%0" + exp + mantissa);
	writer.append("ldx #%0" + exp + mantissa + "\nphx\n");
    }
}

