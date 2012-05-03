package net.crsr.derivative.c;

import java.util.HashSet;
import java.util.Set;

public class Alternative extends Fix
{
  private Parser l1;
  private Parser l2;
  
  public static Parser alternative(Parser l1, Parser l2)
  {
    if (l1 == Empty.e)
    {
      return l2;
    }
    else if (l2 == Empty.e)
    {
      return l1;
    }
    else
    {
      return new Alternative(l1,l2);
    }
  }

  private Alternative(Parser l1, Parser l2)
  {
    this.l1 = l1;
    this.l2 = l2;
  }

  @Override
  public Parser innerDerive(char ch)
  {
    return Alternative.alternative( l1.derive(ch), l2.derive(ch) );
  }

  @Override
  public Set innerDeriveNull()
  {
    final Set set = new HashSet();
    set.addAll( l1.deriveNull() );
    set.addAll( l2.deriveNull() );
    return set;
  }
  
  @Override
  public Parser compact(Set seen)
  {
    if (! seen.contains(this))
    {
      seen.add(this);
      l1 = l1.compact(seen);
      l2 = l2.compact(seen);
      if (l1 == Empty.e)
      {
        return l2;
      }
      else if (l2 == Empty.e)
      {
        return l1;
      }
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
      sb.append(String.format("%s [label=\"Alternative\"];\n", this.hashCode()));
      sb.append(l1.toDot(seen));
      sb.append(String.format("%s -> %s;\n", this.hashCode(), l1.hashCode()));
      sb.append(l2.toDot(seen));
      sb.append(String.format("%s -> %s;\n", this.hashCode(), l2.hashCode()));
      return sb.toString();
    }
    else
    {
      return "";
    }
  }
}
