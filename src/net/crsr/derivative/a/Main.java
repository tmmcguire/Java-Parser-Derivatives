package net.crsr.derivative.a;

import static net.crsr.derivative.a.Parser.*;

public class Main
{

  public static void main(String[] args)
  {
    Parser a = new Empty();
    System.out.println( a.derive('a').deriveNull() );
    System.out.println();
    
    a = new Epsilon();
    System.out.println( a.deriveNull() );
    System.out.println( a.derive('a').deriveNull() );
    System.out.println();
    
    a = new Literal('a');
    System.out.println( a.derive('a').deriveNull() );
    System.out.println();

    a = new Concat(new Literal('a'), new Literal('b'));
    System.out.println( a.derive('a').derive('b').deriveNull() );
    System.out.println();
    
    Reduction reduction = new Reduction()
    {
      @Override
      public Object reduce(Object t)
      {
        if (t instanceof Pair)
        {
          t = String.format("%s%s", ((Pair) t).first(), ((Pair) t).second());
        }
        System.out.println(t);
        return t;
      }
    };
    a = new Reduce(new Concat(new Literal('a'), new Literal('b')), reduction);
    System.out.println( a.derive('a').derive('b').deriveNull() );
    System.out.println();
  }

}
