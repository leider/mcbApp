package mcb.model;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Laender {
  private static Map<String, String> map;

  static {
    Laender.map = new TreeMap<String, String>();
    Laender.map.put("D", "");
    Laender.map.put("F", "Frankreich");
    Laender.map.put("NL", "Niederlande");
    Laender.map.put("A", "÷sterreich");
    Laender.map.put("B", "Belgien");
    Laender.map.put("CH", "Schweiz");
    Laender.map.put("CZ", "Tschechien");
    Laender.map.put("DK", "D‰nemark");
    Laender.map.put("E", "Spanien");
    Laender.map.put("FIN", "Finnland");
    Laender.map.put("FL", "Liechtenstein");
    Laender.map.put("GB", "Groﬂbritannien");
    Laender.map.put("GR", "Griechenland");
    Laender.map.put("H", "Ungarn");
    Laender.map.put("HR", "Kroatien");
    Laender.map.put("I", "Italien");
    Laender.map.put("IRL", "Irland");
    Laender.map.put("L", "Luxemburg");
    Laender.map.put("N", "Norwegen");
    Laender.map.put("P", "Portugal");
    Laender.map.put("PL", "Polen");
    Laender.map.put("S", "Schweden");
  }

  public static SortedSet<String> getAlleKurzel() {
    return new TreeSet<String>(Laender.map.keySet());
  }

  public static String landFuerKuerzel(String landText) {
    return Laender.map.get(landText).toUpperCase();

  }
}
