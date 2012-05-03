package net.crsr.derivative.c;

import java.util.HashSet;
import java.util.Set;

public class Concat extends Fix
{
  private Parser l1;
  private Parser l2;
  
  public static Parser concat(Parser l1, Parser l2)
  {
    if (l1 == Empty.e || l2 == Empty.e)
    {
      return Empty.e;
    }
    else
    {
      return new Concat(l1,l2);
    }
  }

  private Concat(Parser l1, Parser l2)
  {
    this.l1 = l1;
    this.l2 = l2;
  }

  @Override
  public Parser innerDerive(char ch)
  {
    return Alternative.alternative(
        Concat.concat( l1.derive(ch), l2 ),
        Concat.concat( new Delta(l1), l2.derive(ch) )
        );
  }

  @Override
  public Set innerDeriveNull()
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
  
  @Override
  public Parser compact(Set seen)
  {
    if (! seen.contains(this))
    {
      seen.add(this);
      l1 = l1.compact(seen);
      l2 = l2.compact(seen);
    }
    return this;
  }

  @Override
  public String toDot(Set seen)
  {
    if (! seen.contains(this))
    {
      seen.add(this);
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("%s [label=\"Concat\"];\n", this.hashCode()));
      sb.append(l1.toDot(seen));
      sb.append(String.format("%s -> %s [label=\"First\"];\n", this.hashCode(), l1.hashCode()));
      sb.append(l2.toDot(seen));
      sb.append(String.format("%s -> %s [label=\"Second\"];\n", this.hashCode(), l2.hashCode()));
      return sb.toString();
    }
    else
    {
      return "";
    }
  }
}
