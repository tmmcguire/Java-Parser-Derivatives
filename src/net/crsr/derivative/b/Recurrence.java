package net.crsr.derivative.b;

import java.util.Set;

public class Recurrence extends Parser
{
    private Parser l;
    
    public void setParser(Parser l) { this.l = l; }
    
    @Override
    public Parser derive(char ch)
    {
      return l.derive(ch);
    }

    @Override
    public Set deriveNull()
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
