package net.crsr.derivative.c;


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
//        System.out.println(t);
        return t;
      }
    };
    a = new Reduce(new Concat(new Literal('a'), new Literal('b')), reduction);
    GraphWriter.writeGraph("/tmp/ab.dot", a);
    final Parser ab = a.derive('a').derive('b');
    GraphWriter.writeGraph("/tmp/ab-prime.dot", ab);
    System.out.println( ab.deriveNull() );
    System.out.println();
    
    Recurrence S = new Recurrence();
    a = new Alternative(new Epsilon(), new Reduce(new Concat(S, new Literal('1')), reduction));
    S.setParser(a);
    GraphWriter.writeGraph("/tmp/S.dot", a);
    final Parser S111 = a.derive('1').derive('1').derive('1');
    GraphWriter.writeGraph("/tmp/111.dot", S111);
    System.out.println( S111.deriveNull() );

    S = new Recurrence();
    a = new Alternative(new Epsilon(), new Reduce(new Concat(S, new Literal('1')), reduction));
    S.setParser(a);
    GraphWriter.writeGraph("/tmp/S1.dot", a);
    for (char ch : "1111111111".toCharArray())
    {
      a = a.derive(ch);
      GraphWriter.writeGraph("/tmp/111.dot", a);
    }
    System.out.println( a.deriveNull() );

    Recurrence term = new Recurrence();
    Parser one = new Literal('1');
    Parser add = new Literal('+');
    Parser mul = new Literal('*');
    Parser op  = new Alternative(add, mul);
    Parser ex  = new Concat(term,new Concat(op,term));
    Parser l   = new Alternative(one,ex);
    term.setParser(l);
    GraphWriter.writeGraph("/tmp/l.dot", l);
    int i = 0;
    for (char ch : "1+1*1".toCharArray())
    {
      l = l.derive(ch);
      GraphWriter.writeGraph("/tmp/l" + (++i) + ".dot", l);
    }
    System.out.println( l.deriveNull() );
}

}
