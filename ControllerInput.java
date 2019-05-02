class ControllerInput extends Expr implements BG {

    ControllerNum cn;
    InputMethod im;
    ButtonInput bi;
    public ControllerInput(ControllerNum c, InputMethod i, ButtonInput b)
  {
      cn = c;
      im = i;
      bi = b;
  }

  public void analysis() throws BaseGrammarException
  {
      return;
  }

  public FullType getType() throws BaseGrammarException
  {
     return new FullType(new BoolType(), false, false);
  }

  public String toString(int t)
  {
      return("_button(" + cn.toString(t) + ", " + im.toString(t) + ", " + bi.toString(t) + ")");
  }

    public void gen(int extra) throws Exception
    {
	//load the possibilities are Joy1Held Joy2Held Joy1Press Joy2Press
	writer.append("wai\nlda Joy" + cn.toString(0) + im.toString(0) + "\nand ");
	//we then mask for the button we care about
	bi.gen();
	//if this button isn't pressed then zero flag is set. get processor into acc and then
	//shift right once so zero is in ones spot. mask all other bits and if the zero is
	//present, the answer is false. So flip bit then push value
	writer.append("\nphp\nsep #$20\npla\nlsr a\nand #$01\neor #$01\ntax\nrep #$20\nphx\n");
    }
}
