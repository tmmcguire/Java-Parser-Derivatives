package net.crsr.derivative.b;

import java.util.HashSet;
import java.util.Set;

public class Empty extends Parser
{
  @Override
  public Parser derive(char ch)
  {
    return new Empty();
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
}
