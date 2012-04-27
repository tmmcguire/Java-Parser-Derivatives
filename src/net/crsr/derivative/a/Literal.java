package net.crsr.derivative.a;

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
    return this.ch == ch ? new Epsilon(ch) : new Empty();
  }
  
  @Override
  public Set deriveNull()
  {
    return new HashSet();
  }
}
