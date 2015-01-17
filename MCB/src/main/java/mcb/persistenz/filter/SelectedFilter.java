package mcb.persistenz.filter;

public class SelectedFilter {

  private static AdresseFilter selected = AlleFilter.getInstance();

  public static AdresseFilter get() {
    return SelectedFilter.selected;
  }

  public static void set(AdresseFilter filter) {
    SelectedFilter.selected = filter;
  }

}
