class testCodeGen{
int spot[9];
int filled;
final int blank = 1;
final int plus = 2;
final int minus = 3;
final int equals = 4;
final int redCross = 5;
final int divide = 6;
final int redO = 7;
final int bar = 8;
final int zero = 9;
final int one = 10;
final int two = 11;
final int three = 12;
final int four = 13;
final int five = 14;
final int six = 15;
final int seven = 16;
final int eight = 17;
final int nine = 18;
final int plusYellow = 19;
final int minusYellow = 20;
final int select = 21;

void draw(int row, int col, int symbol)
{
	_tile(row, col, symbol);
}

void main()
{

	float temp = (float)-22;
	if (  temp * 5 < -105 &&  temp * 5 > -113)
		draw(0,0,redO);
	else
		draw(0,0,redCross);
		
	if (  100.0 / 0.5 < 202.0 &&  100 / 0.25 > 398.0)
		draw(0,1,redO);
	else
		draw(0,1,redCross);
	draw(1,0,minus);
	
	_display();
}
}