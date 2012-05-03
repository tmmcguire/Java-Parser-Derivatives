package net.crsr.derivative.c;

import java.util.HashSet;
import java.util.Set;

public abstract class Parser
{
  abstract public Parser  				 derive(char ch);
  abstract public Set     				 deriveNull();
  
  public Parser compact()
  {
    final HashSet<Parser> seen = new HashSet<Parser>();
    final Parser compacted = this.compact(seen);
//    System.out.println("compacted: " + seen.size());
    return compacted;
  }
  
  public Parser compact(Set<Parser> seen)
  {
    if (! seen.contains(this))
    {
      seen.add(this);
    }
    return this;
  }
  
  abstract public String toDot(Set<Parser> seen);
}
