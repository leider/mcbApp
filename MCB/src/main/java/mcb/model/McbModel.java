package mcb.model;

import java.util.Comparator;
import java.util.logging.Logger;

import com.jgoodies.binding.beans.Model;

public abstract class McbModel extends Model {

  public static class Comp implements Comparator<McbModel> {
    @Override
    public int compare(McbModel a1, McbModel a2) {
      return (int) (a2.getId() - a1.getId());
    }
  }

  static final Logger LOGGER = Logger.getLogger(McbModel.class.getName());

  private static final long serialVersionUID = 1L;

  protected long id = 0;

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    McbModel other = (McbModel) obj;
    return this.id == other.id;
  }

  public long getId() {
    return this.id;
  }

  @Override
  public int hashCode() {
    return (int) (31 + this.id);
  }

  public boolean isNeu() {
    return this.getId() == 0;
  }

  public void setId(long id) {
    this.id = id;
  }

}
