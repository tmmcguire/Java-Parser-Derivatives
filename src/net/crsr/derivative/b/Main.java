package net.crsr.derivative.b;


public class Main
{

  public static void main(String[] args)
  {
    Parser a;
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
    GraphWriter.writeGraph("/tmp/ab.dot", a);
    final Parser ab = a.derive('a').derive('b');
    GraphWriter.writeGraph("/tmp/ab-prime.dot", ab);
    System.out.println( ab.deriveNull() );
    System.out.println();
  }

}
