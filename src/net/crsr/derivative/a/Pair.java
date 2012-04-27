package net.crsr.derivative.a;

public class Pair<S,T>
{
  public static <S,T> Pair<S,T> pair(S s, T t) { return new Pair<S,T>(s,t); }
  
  private final S s;
  private final T t;
  
  @Override
  public int hashCode()
  {
    return s.hashCode() ^ t.hashCode();
  }

  @Override
  public boolean equals(Object obj)
  {
    if (! (obj instanceof Pair))
    {
      return false;
    }
    final Pair<?,?> other = (Pair<?,?>) obj;
    if (s != null && t != null)
    {
      return s.equals(other.s) && t.equals(other.t);
    }
    else if (s == null && t != null)
    {
      return s == other.s && t.equals(other.t);
    }
    else if (s != null && t == null)
    {
      return s.equals(other.s) && t == other.t;
    }
    else
    {
      return s == other.s && t == other.t;
    }
  }

  public Pair(S s, T t) { this.s = s; this.t = t; }
  
  public S first() { return s; }
  public T second() { return t; }

  public String toString()
  {
    return String.format("<%s,%s>", s, t);
  }
}
