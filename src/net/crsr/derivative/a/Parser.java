package net.crsr.derivative.a;

import java.util.Set;

public abstract class Parser
{
  abstract public Parser derive(char ch);
  abstract public Set    deriveNull(); 
}
