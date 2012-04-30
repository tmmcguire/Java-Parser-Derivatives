package net.crsr.derivative.c;

import java.util.Set;

public class Recurrence extends Fix
{
    private Parser l;
    
    public void setParser(Parser l) { this.l = l; }
    
    @Override
    public Parser innerDerive(char ch)
    {
      return l.derive(ch);
    }

    @Override
    public Set innerDeriveNull()
    {
      return l.deriveNull();
    }

    @Override
    public String toDot(Set<Parser> seen)
    {
      if (! seen.contains(this))
      {
        seen.add(this);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s [label=\"Recurrence\"];\n", this.hashCode()));
        sb.append(l.toDot(seen));
        sb.append(String.format("%s -> %s;\n", this.hashCode(), l.hashCode()));
        return sb.toString();
      }
      else
      {
        return "";
      }
    }
    
  }
