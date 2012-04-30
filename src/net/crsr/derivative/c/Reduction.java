package net.crsr.derivative.c;

public interface Reduction<T,U>
{
  public U reduce(T t);
}
