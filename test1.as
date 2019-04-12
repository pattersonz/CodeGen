class testTypes
{

    final int i = 3;

    void otherExprs()
    {
        int i;
        char c;
        float f;
        bool b;
        i = (true ? 3 : 2);
        b = (b ? i : b);
        i = (b ? i : b);
        b = (i ? i : i);
        c = (b ? c : c);
        f = (b ? f : f);
        f = (b ? i : i);
        f = (b ? f : i);
        f = (b ? i : f);
        f = (c ? i : i);
        f = (float)c;
        f = (int)b;
        i = (float)c;
        i = otherExprs();
    }\\*\

    void binaryExprCheck()
    {
        int i;
        char c;
        bool b;
        float f;
        i = i + i;
        c = c + c;
        b = b + b;
        f = f + f;
        i = i + f;
        f = i + f;
        f = f + i;
        c = i + i;
        b = i + i;
        b = i || i;
        b = i || b;
        b = b || b;
        i = i || b;
        f = i || b;
        c = i || b;
        b = c || c;
        b = f || f;
        print("test" + "concat");
        print("addToI" + i);
        print("subI" - i);
        i = "test" + i;
        b = "test" || "test";
        b = "test" + "char";
    }\\*\

    bool UnaryCheck()
    {
	    int i;
    	float f;
    	char c;
    	bool b;
	    int ia[1];
    	float fa[1];
    	char ca[1];
    	bool ba[1];
    	i++
        b++
        c++
        f++
    	ia++
        ba++
        ca++
        fa++
        ia[0]++
        ba[0]++
        ca[0]++
        fa[0]++
        i = +i;
        i = -i;
        i = +f;
        i = -f;
        b = +i;
        b = -c;
        b = +f;
        b = -b;
        c = +c;
        c = -i;
        c = +b;
        c = -f;
        f = +i;
        f = -i;
        f = +f;
        f = -f;
        i = ~i;
        b = ~i;
        b = ~b;
        c = ~f;
        c = ~b;
        return i;
    }\\*\

	float assignmentCheck(int x[])
	{
	    int i = i;
    	float f;
    	char c;
    	bool b;

		i = 1;
		i = 1.0;
		i = false;
		i = '0';
        f = 1;
        f = 1.0;
        f = false;
        f = '0';
        c = 1;
        c = 1.0;
        c = false;
        c = '0';
        b = 1;
        b = 1.0;
        b = false;
        b = '0';
        {
            int i[1];
            float f[1];
            char c[1];
            bool b[1];
            i = 1;
            i = 1.0;
            i = false;
            i = '0';
            f = 1;
            f = 1.0;
            f = false;
            f = '0';
            c = 1;
            c = 1.0;
            c = false;
            c = '0';
            b = 1;
            b = 1.0;
            b = false;
            b = '0';
            i = x;
            f = x;
            i[0] = 1;
            i[0] = 1.0;
            i[0] = false;
            i[0] = '0';
            f[0] = 1;
            f[0] = 1.0;
            f[0] = false;
            f[0] = '0';
            c[0] = 1;
            c[0] = 1.0;
            c[0] = false;
            c[0] = '0';
            b[0] = 1;
            b[0] = 1.0;
            b[0] = false;
            b[0] = '0';
        };
        return i;
	}\\*\
}