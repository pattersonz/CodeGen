#include <bits/stdc++.h>
using namespace std;

string byte(int b[])
{
  int val = 0;
  for(int i = 0; i < 8; i++)
    {
      val *= 2;
      val += b[i];
    }
  string first, second;
  first = "0"; second = "0";
  cout << val << " " << val / 16 << "" << val%16 <<endl;
  int temp = val / 16;
  if (temp < 10)
    first[0] = temp + '0';
  else
    first[0] = temp - 10 + 'A';
  temp = val%16;
  if (temp < 10)
    second[0] = temp + '0';
  else
    second[0] = temp - 10 + 'A';
  return first + second;
}

int main(int argc, char* argv[])
{
  ifstream ifs(argv[1], ifstream::in);
  int numTiles;
  ifs >> numTiles;
  string s;
  getline(ifs, s); //skip over new line
  vector<int>tiles;
  tiles.resize(numTiles*8*8, 0);
  for (int i = 0; i < numTiles; i++)
    {
      getline(ifs,s);
      for (int r = 0; r < 8; r++)
	{
	for (int c = 0; c < 8; c++)
	  {
	    char x;
	    ifs >> x;
	    if (x == '1')
	      tiles[i*64+r*8+c] = 1;
	    else if (x == '2')
	      tiles[i*64+r*8+c]=2;
	    else if (x == '3')
	      tiles[i*64+r*8+c]=3;
	  }
	}
    }
  ifs.close();
  ofstream ofs("tiles.inc", ofstream::out);
  ofs << "DataSet:" << endl;
  for (int i = 0; i < numTiles; i++)
    {
      ofs << "\t.db ";
      for (int r = 0; r < 8; r++)
	{
	  int bin[8];
	  for (int c = 0; c < 8; c++)
	    {
	      bin[c] = tiles[i*64+r*8+c] % 2;
	    }
	  ofs << "$" << byte(bin) << ", ";
	  for (int c = 0; c < 8; c++)
	    {
	      bin[c] = tiles[i*64+r*8+c] / 2;
	    }
	  ofs << "$" << byte(bin) << (r == 7?"":", ");
	}
      ofs << endl;
    }
    
  return 0;
}
