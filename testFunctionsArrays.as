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

void draw(int row, int col, int symbol)
{
	_tile(row, col, symbol);
}

int getVal(int a, int index)
{
	return a + index;
}

int grabValAt(int a[], int index)
{
	return a[index];
}

int changeVal(int ptr[])
{
	ptr[0]++
	return ptr[0];
}

void main()
{
	int i = 1;
	int a[10];
	int temp;
	int ptr[1];
	a[0] = zero;
	b[0] = minus;
	while (i < 10)
	{
		a[i] = a[i - 1] + 1;
		b[i] = b[i - 1] + 2;
	i++
	}
	i = 0;
	while (i < 10)
	{
		draw(i,0,getVal(a[i],1));
		draw(i,1,getVal(b[i],1));
		i++
	}
	\\function call here
	i = 0;
	while (i < 10)
	{
		draw(i,2,grabValAt(a,i) + 1);
		draw(i,3,grabValAt(b,i) + 1);
		i++
	}
	ptr[0] = zero;
	changeVal(ptr);
	draw(12,0,ptr[0]);
	_display();
}
}