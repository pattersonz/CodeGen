import java.util.*;

class BaseGrammarException extends Exception
{
    Vector<String> errors;
    public BaseGrammarException(String s)
    {
        errors = new Vector<String>();
        errors.add(s);
    }

    public int getErrors()
    {
        return errors.size();
    }


    public void add(BaseGrammarException b)
    {
        for (int i = 0; i < b.getErrors(); i++)
            errors.add(b.get(i));
    }

    public String get(int i)
    {
        return errors.elementAt(i);
    }

    public void prepend(String s)
    {
        for (int i = 0; i < errors.size(); i++)
            errors.set(i, s + ":" + errors.elementAt(i));
    }

    public String toString()
    {
        String out = "";
        for (int i = 0; i < errors.size(); i++)
        {
            out = out + errors.elementAt(i) + "\n";
        }

        return out;
    }
}