package net.crsr.derivative.c;

import java.util.HashSet;
import java.util.Set;

public class Literal extends Parser
{
  private final char ch;

  public Literal(char ch)
  {
    this.ch = ch;
  }

  @Override
  public Parser derive(char ch)
  {
    return this.ch == ch ? new Epsilon(ch) : Empty.e;
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
      return String.format("%s [label=\"Lit '%s'\"];\n", this.hashCode(), ch);
    }
    else
    {
      return "";
    }
  }
}
