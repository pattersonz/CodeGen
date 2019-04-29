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
	writer.append("ldx #$" + hex((int)(floatingPoint)) + "\nphx\n");
    }
}

