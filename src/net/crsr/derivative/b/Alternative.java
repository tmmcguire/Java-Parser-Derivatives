package net.crsr.derivative.b;

import java.util.HashSet;
import java.util.Set;

public class Alternative extends Parser
{
  private Parser l1;
  private Parser l2;

  public Alternative(Parser l1, Parser l2)
  {
    this.l1 = l1;
    this.l2 = l2;
  }

  @Override
  public Parser derive(char ch)
  {
    return new Alternative( l1.derive(ch), l2.derive(ch) );
  }

  @Override
  public Set deriveNull()
  {
    final Set set = new HashSet();
    set.addAll( l1.deriveNull() );
    set.addAll( l2.deriveNull() );
    return set;
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
