class testCodeGen{
int spot[9];
int filled;
final int blank = 1;
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
final int plusYellow = 19;
final int minusYellow = 20;
final int select = 21;

void draw(int row, int col, int symbol)
{
	_tile(row, col, symbol);
}

bool isValid(int x)
{
	bool ans = (spot[0] == x && spot[1] == x && spot[2] == x);
	
	   ans = ans || (spot[3] == x && spot[4] == x && spot[5] == x);
	   
	   ans = ans || (spot[6] == x && spot[7] == x && spot[8] == x); 

	   ans = ans || (spot[0] == x && spot[3] == x && spot[6] == x);

	   ans = ans || (spot[1] == x && spot[4] == x && spot[7] == x);

	   ans = ans || (spot[2] == x && spot[5] == x && spot[8] == x);

	   ans = ans || (spot[0] == x && spot[4] == x && spot[8] == x);

	   ans = ans || (spot[2] == x && spot[4] == x && spot[6] == x);

	   return ans;
}

int getWinner()
{
	filled++
	if (isValid(1))
	   return redO;
	else if (isValid(2))
	     return greenO;
	else if (filled == 9)
	     return plus;
	return 0;
}
bool possibleSpot(int x)
{
	return spot[x] == 0;
}

void drawAt(int x, int y)
{
	int row = 0;
	int col = 2;
	if (x > 5)
	   row = 4;
	else if (x > 2)
	     row = 2;
	if (x == 8 || x == 5 || x == 2)
	   col = 6;
	else if (x == 7 || x == 4 || x == 1)
	     col = 4;
	draw(row, col, y);
}

void main()
{
	
	while(true)
	{
	\\main game loop, draw board
	       int i = 0;
	       int winner;
	       while(i < 32)
	       {
		int j = 0;
		while(j < 32)
		{
		draw(i,j,blank);
		j++
		}
		i++
	       }
	       
	draw(0,3,bar);
	draw(0,5,bar);
	draw(2,3,bar);
	draw(2,5,bar);
	draw(4,3,bar);
	draw(4,5,bar);
	draw(1,2, minusYellow);
	draw(1,4, minusYellow);
	draw(1,6, minusYellow);
	draw(3,2, minusYellow);
	draw(3,4, minusYellow);
	draw(3,6, minusYellow);
	draw(1,3,plusYellow);
	draw(1,5,plusYellow);
	draw(3,3,plusYellow);
	draw(3,5,plusYellow);
	_display();

	i = 0;
	while(i < 9)
	{
		spot[i] = 0;
		i++
	}

	winner = 0;
	filled = 0;
	\\main loop
	while(winner == 0)
	{
		int selecting = 0;
		int prevSel;
		bool confirmed = false;
		int player = 1;
		while(~possibleSpot(selecting))
			{
				if (selecting == 8)
				   selecting = 0;
				else
				   selecting++
				
			}
		drawAt(selecting, select);
		_display();
		while(~confirmed)
		{
			if(_button(_1,_press,_LEFT))
			{
				prevSel = selecting;
				if (selecting == 0)
				   selecting = 8;
				else
					selecting--
				while (~possibleSpot(selecting))
				if (selecting == 0)
				   selecting = 8;
				else
					selecting--
				drawAt(prevSel, blank);
				drawAt(selecting, select);
				_display();
			}
			else if(_button(_1,_press,_RIGHT))
			{
				prevSel = selecting;
				if (selecting == 8)
				   selecting = 0;
				else
					selecting++
				while (~possibleSpot(selecting))
				if (selecting == 0)
				   selecting = 8;
				else
					selecting--
				drawAt(prevSel, blank);
				drawAt(selecting, select);
				_display();
			}
			else if (_button(_1,_press,_A))
			     confirmed = true;
		}
		spot[selecting] = player;
		if (player == 1)
		   player = 2;
		else
		   player = 1;
		
		winner = getWinner();
	}

	\\display winner
	i = 0;
	while(i < 32)
	       {
		int j = 0;
		while(j < 32)
		{
		draw(i,j,winner);
		j++
		}
		i++
	       }

	       \\restart game
	while(~_button(_1,_press,_A))
	{
	}
	
	}
}
}