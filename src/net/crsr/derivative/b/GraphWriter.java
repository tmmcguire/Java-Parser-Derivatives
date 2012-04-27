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
      fw.write( "digraph f { " + a.toDot(new HashSet<Parser>()) + " }" );
      fw.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

}
