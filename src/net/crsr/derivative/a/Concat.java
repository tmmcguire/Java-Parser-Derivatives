package net.crsr.derivative.a;

import java.util.HashSet;
import java.util.Set;

public class Concat extends Parser
{
  private Parser l1;
  private Parser l2;
  
  public Concat(Parser l1, Parser l2)
  {
    this.l1 = l1;
    this.l2 = l2;
  }

  @Override
  public Parser derive(char ch)
  {
    return new Alternative(
        new Concat( l1.derive(ch), l2 ),
        new Concat( new Delta(l1), l2.derive(ch) )
        );
  }

  @Override
  public Set deriveNull()
  {
    Set set1   = l1.deriveNull();
    Set set2   = l2.deriveNull();
    Set result = new HashSet();
    for (Object o1 : set1)
    {
      for (Object o2 : set2)
      {
        result.add( new Pair(o1,o2) );
      }
    }
    return result;
  }
}
