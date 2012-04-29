package net.crsr.derivative.b;

import java.util.HashMap;
import java.util.Map;

public class PairMap<S,T,U>
{
  private final Map<S,Map<T,U>> map = new HashMap<S, Map<T,U>>();
  
  public boolean contains(S s, T t)
  {
    return map.containsKey(s) && map.get(s).containsKey(t);
  }
  
  public U get(S s, T t)
  {
    return map.containsKey(s) ? map.get(s).get(t) : null;
  }
  
  public void put(S s, T t, U u)
  {
    Map<T,U> inner = map.get(s);
    if (inner == null)
    {
      inner = new HashMap<T,U>();
      map.put(s,inner);
    }
    inner.put(t, u);
  }
}
