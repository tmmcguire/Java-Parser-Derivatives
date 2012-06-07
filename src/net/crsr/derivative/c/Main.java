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
    a = new Reduce(Concat.concat(new Literal('a'), new Literal('b')), reduction);
    GraphWriter.writeGraph("/tmp/ab.dot", a);
    final Parser ab = a.derive('a').derive('b');
    GraphWriter.writeGraph("/tmp/ab-prime.dot", ab);
    System.out.println( ab.deriveNull() );
    System.out.println();
    
    Recurrence S = new Recurrence();
    a = Alternative.alternative(Epsilon.epsilon(), new Reduce(Concat.concat(S, new Literal('1')), reduction));
    S.setParser(a);
    GraphWriter.writeGraph("/tmp/S.dot", a);
    final Parser S111 = a.compact().derive('1').compact().derive('1').compact().derive('1').compact();
    GraphWriter.writeGraph("/tmp/111.dot", S111);
    System.out.println( S111.deriveNull() );

    S = new Recurrence();
    a = Alternative.alternative(Epsilon.epsilon(), new Reduce(Concat.concat(S, new Literal('1')), reduction));
    S.setParser(a);
    a = a.compact();
    GraphWriter.writeGraph("/tmp/S1.dot", a);
    int i = 0;
    for (char ch : "1111111111".toCharArray())
    {
      a = a.derive(ch).compact();
      GraphWriter.writeGraph("/tmp/S" + i++ + ".dot", a);
    }
    System.out.println( a.deriveNull() );
    System.out.println();

    Recurrence term = new Recurrence();
    Parser one = new Literal('1');
    Parser add = new Literal('+');
    Parser mul = new Literal('*');
    Parser op  = Alternative.alternative(add, mul);
    Parser ex  = Concat.concat(term,Concat.concat(op,term));
    Parser l   = Alternative.alternative(one,ex);
    term.setParser(l);
    l = l.compact();
    GraphWriter.writeGraph("/tmp/l.dot", l);
    i = 0;
    for (char ch : "1+1*1".toCharArray())
    {
      l = l.derive(ch).compact();
      GraphWriter.writeGraph("/tmp/l" + (++i) + ".dot", l);
    }
    System.out.println( l.deriveNull() );
    System.out.println();
  }

}
