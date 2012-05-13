package net.crsr.derivative.c;

import java.util.HashSet;
import java.util.Set;

public class Epsilon extends Parser
{
  private Set trees = new HashSet();
  
  private static Parser emptyString = new Epsilon();
  
  public static Parser epsilon() { return emptyString; }
  
  public static Parser epsilon(char ch) { return new Epsilon(ch); }
  
  public static Parser epsilon(Set trees)
  {
    if (trees.isEmpty())
    {
      return Empty.e;
    }
    else if (trees.size() == 1 && trees.contains(""))
    {
      return emptyString;
    }
    else
    {
      return new Epsilon(trees);
    }
  }

  private Epsilon()
  {
    trees.add("");
  }

  private Epsilon(char ch)
  {
    trees.add(ch);
  }
  
  private Epsilon(Set trees)
  {
    this.trees.addAll(trees);
  }

  @Override
  public Parser derive(char ch)
  {
    return Empty.e;
  }

  @Override
  public Set deriveNull()
  {
    return trees;
  }

  @Override
  public String toDot(Set seen)
  {
    if (! seen.contains(this))
    {
      seen.add(this);
      return String.format("%s [label=\"Epsilon <%s>\"];\n", this.hashCode(), trees);
    }
    else
    {
      return "";
    }
  }
}
