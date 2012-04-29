package net.crsr.derivative.b;

import java.util.HashSet;
import java.util.Set;

public abstract class Fix extends Parser
{
  
  public abstract Parser innerDerive(char ch);
  public abstract Set innerDeriveNull();

  private static PairMap<Parser,Character,Parser> derivatives = new PairMap<Parser, Character, Parser>();

  @Override
  public Parser derive(char ch)
  {
    if (! derivatives.contains(this, ch))
    {
      derivatives.put(this, ch, new Delay(this, ch));
    }
    return derivatives.get(this, ch);
  }

  @Override
  public Set deriveNull()
  {
    if (_set != null)
    {
      return _set;
    }
    Set newSet = new HashSet();
    do
    {
      _set = newSet;
      newSet = innerDeriveNull();
    } while (! _set.equals( newSet ));
    return _set;
  }
  
  private Set _set = null;

  private static class Delay extends Parser
  {
    private Fix parser;
    private char ch;
    private Parser derivative = null;
    
    public Delay(Fix parser, char ch)
    {
      this.parser = parser;
      this.ch = ch;
    }

    @Override
    public Parser derive(char ch)
    {
      this.force();
      return this.derivative.derive(ch);
    }

    @Override
    public Set deriveNull()
    {
      this.force();
      return this.derivative.deriveNull();
    }
    
    @Override
    public String toDot(Set<Parser> seen)
    {
      if (! seen.contains(this))
      {
        seen.add(this);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s [label=\"Delay '%s'\"];\n", this.hashCode(), ch));
        sb.append(parser.toDot(seen));
        sb.append(String.format("%s -> %s [style=dashed];\n", this.hashCode(), parser.hashCode()));
        if (derivative != null)
        {
          sb.append(String.format("%s -> %s;\n", this.hashCode(), derivative.hashCode()));
          sb.append(derivative.toDot(seen));
        }
        return sb.toString();
      }
      else
      {
        return "";
      }
    }
    
    private void force()
    {
      if (derivative == null)
      {
        derivative = this.parser.innerDerive(this.ch);
      }
    }

  }

}
