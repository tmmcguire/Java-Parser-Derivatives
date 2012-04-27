package net.crsr.derivative.a;

import java.util.HashSet;
import java.util.Set;

public class Reduce extends Parser
{
  private Parser parser;
  private Reduction reduction;

  public Reduce(Parser parser, Reduction reduction)
  {
    this.parser    = parser;
    this.reduction = reduction;
  }

  @Override
  public Parser derive(char ch)
  {
    return new Reduce(parser.derive(ch),reduction);
  }

  @Override
  public Set deriveNull()
  {
    Set newSet = new HashSet();
    for (Object o : parser.deriveNull())
    {
      newSet.add( reduction.reduce(o) );
    }
    return newSet;
  }
}
