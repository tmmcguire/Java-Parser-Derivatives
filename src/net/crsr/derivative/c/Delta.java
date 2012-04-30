package net.crsr.derivative.c;

import java.util.Set;

public class Delta extends Parser
{
  private Parser l;

  public Delta(Parser l) { this.l = l; }

  @Override
  public Parser derive(char ch)
  {
    return Empty.e;
  }

  @Override
  public Set deriveNull()
  {
    return l.deriveNull();
  }

  @Override
  public String toDot(Set seen)
  {
    if (! seen.contains(this))
    {
      seen.add(this);
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("%s [label=\"Delta\"];\n", this.hashCode()));
      sb.append(l.toDot(seen));
      sb.append(String.format("%s -> %s;\n", this.hashCode(), l.hashCode()));
      return sb.toString();
    }
    else
    {
      return "";
    }
  }
}
