package net.crsr.derivative.c;

import java.util.HashSet;
import java.util.Set;

public class Reduce extends Fix
{
  private Parser parser;
  private Reduction reduction;

  public Reduce(Parser parser, Reduction reduction)
  {
    this.parser    = parser;
    this.reduction = reduction;
  }

  @Override
  public Parser innerDerive(char ch)
  {
    return new Reduce(parser.derive(ch),reduction);
  }

  @Override
  public Set innerDeriveNull()
  {
    Set newSet = new HashSet();
    for (Object o : parser.deriveNull())
    {
      newSet.add( reduction.reduce(o) );
    }
    return newSet;
  }
  
  @Override
  public Parser compact(Set seen)
  {
    if (! seen.contains(this))
    {
      seen.add(this);
      parser = parser.compact(seen);
    }
    if (parser instanceof Reduce)
    {
      final Reduce subReduction = (Reduce) parser;
      final Reduction inner = subReduction.reduction;
      final Reduction outer = this.reduction;
      final Reduction combination = new Reduction<Object,Object>() { @Override public Object reduce(Object t) { return outer.reduce( inner.reduce(t) ); } };
      return new Reduce(subReduction.parser,combination);
    }
    else if (parser == Empty.empty)
    {
      return Empty.empty;
    }
    else
    {
      return this;
    }
  }

  @Override
  public String toDot(Set seen)
  {
    if (! seen.contains(this))
    {
      seen.add(this);
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("%s [label=\"Reduce\"];\n", this.hashCode()));
      sb.append(parser.toDot(seen));
      sb.append(String.format("%s -> %s;\n", this.hashCode(), parser.hashCode()));
      return sb.toString();
    }
    else
    {
      return "";
    }
  }
}
