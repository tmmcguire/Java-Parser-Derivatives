package net.crsr.derivative.a;

import java.util.Set;

public class Delta extends Parser
{
  private Parser l;
  
  public Delta(Parser l) { this.l = l; }
  
  @Override
  public Parser derive(char ch)
  {
    return new Empty();
  }
  
  @Override
  public Set deriveNull()
  {
    return l.deriveNull();
  }
}
