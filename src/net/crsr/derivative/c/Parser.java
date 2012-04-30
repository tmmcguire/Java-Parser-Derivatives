package net.crsr.derivative.c;

import java.util.Set;

public abstract class Parser
{
  abstract public Parser  				 derive(char ch);
  abstract public Set     				 deriveNull();
  
  abstract public String toDot(Set<Parser> seen);
}
