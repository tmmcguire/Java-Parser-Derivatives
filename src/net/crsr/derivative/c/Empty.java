package net.crsr.derivative.c;

import java.util.HashSet;
import java.util.Set;

public class Empty extends Parser
{
  public static final Empty empty = new Empty();
  
  @Override
  public Parser derive(char ch)
  {
    return empty;
  }

  @Override
  public Set deriveNull()
  {
    return new HashSet();
  }

  @Override
  public String toDot(Set seen)
  {
    if (! seen.contains(this))
    {
      seen.add(this);
      return String.format("%s [label=\"Empty\"];\n", this.hashCode());
    }
    else
    {
      return "";
    }
  }
  
  private Empty() { }
}
