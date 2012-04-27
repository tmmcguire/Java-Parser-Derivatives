package net.crsr.derivative.a;

import java.util.HashSet;
import java.util.Set;

public class Epsilon extends Parser
{
  private Set trees = new HashSet();

  public Epsilon()
  {
    trees.add("");
  }
  
  public Epsilon(char ch)
  {
    trees.add(ch);
  }

  @Override
  public Parser derive(char ch)
  {
    return new Empty();
  }
  
  @Override
  public Set deriveNull()
  {
    return trees;
  }
}
