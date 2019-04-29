class testCodeGen{
int b[10];
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
void func1(int a)
{
	_tile(10,0,a);
}

void main()
{
	int a[10];
	int i = 1;
	a[0] = zero;
	b[0] = minus;
	while(i < 10)
	{
		a[i] = a[i - 1] + 1;
		b[i] = b[i - 1] + 2;
		i++
	}
	i = 0;
	while(i < 10)
	{
		_tile(i,0,i+zero);
		_tile(i,1,a[i]);
		_tile(i,2,b[i]);
		i++
	}
	func1(bar);
	_tile(11, 0, select);
	_display();
	
}
}