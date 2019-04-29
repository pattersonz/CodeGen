class testCodeGen{
final int plus = 2;
final int minus = 3;
final int equals = 4;
final int greenO = 5;
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
final int plusBlue = 19;
final int minusBlue = 20;
final int select = 21;
void main()
{
	int i = 0;
	int j = 0;
	int ones;
	int tens;
	while (i <= 31)
	{
		int onesSpot = 0;
		tens = 0;
		ones = i;
		while (ones > 9)
		{
			tens++
			ones = ones - 10;
		}
		if (tens >= 1)
		{
			onesSpot = 1;
			_tile(i,0,tens + zero);
		}
		_tile(i,onesSpot,ones + zero);
		j = onesSpot + 1;
		while (j <= 31)
		{
			if (i == j)
			   _tile(i,j,plus);
			else if (i <> j)
			     _tile(i,j, (i > 15 ? minusBlue:minus));
			else
			\\error
				_tile(i,j,plusBlue);
			j++
		}
		i++
		
	}
	i = ( 12? 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 + 11 + 12 + 12 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 + 11 + 12 + 12 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 + 11 + 12 + 12 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 + 11 + 12 + 12 + 1 + 2 + 3 + 4 + 5  : 1);
	_display();
	
}
}