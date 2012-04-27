package net.crsr.derivative.a;

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
}
