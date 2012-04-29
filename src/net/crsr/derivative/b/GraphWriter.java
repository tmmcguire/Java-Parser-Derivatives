package net.crsr.derivative.b;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class GraphWriter
{

  public static void writeGraph(String file, Parser a)
  {
    try
    {
      FileWriter fw = new FileWriter(file);
      final HashSet<Parser> seen = new HashSet<Parser>();
      fw.write( "digraph f { " + a.toDot(seen) + " }" );
      fw.close();
      System.out.println(seen.size());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

}
