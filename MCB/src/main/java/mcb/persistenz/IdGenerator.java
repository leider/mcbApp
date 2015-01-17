package mcb.persistenz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mcb.model.McbModel;

public class IdGenerator {

  public static long nextIdFor(List<? extends McbModel> originals) {
    List<McbModel> models = new ArrayList<McbModel>(originals);
    if (models.isEmpty()) {
      return 1;
    }
    Collections.sort(models, new McbModel.Comp());
    return models.get(0).getId() + 1;
  }

}
