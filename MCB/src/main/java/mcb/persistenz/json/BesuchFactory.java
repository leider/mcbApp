package mcb.persistenz.json;

import java.lang.reflect.Type;
import java.util.Map;

import mcb.model.Besuch;
import mcb.model.Treffen;
import mcb.persistenz.filter.Treffens;
import flexjson.ObjectBinder;
import flexjson.ObjectFactory;
import flexjson.factories.BeanObjectFactory;

public class BesuchFactory implements ObjectFactory {

  private final Treffens treffens;

  public BesuchFactory(Treffens treffens) {
    super();
    this.treffens = treffens;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public Object instantiate(ObjectBinder context, Object value, Type targetType, Class targetClass) {
    Besuch besuch = (Besuch) new BeanObjectFactory().instantiate(context, value, targetType, targetClass);
    Map<String, Object> valueMap = (Map<String, Object>) value;
    for (Treffen treffen : this.treffens.getAlleTreffen()) {
      Map<String, Object> treffenMap = (Map<String, Object>) valueMap.get("treffen");
      if (treffen.getId() == ((Number) treffenMap.get("id")).longValue()) {
        besuch.setTreffen(treffen);
      }
    }
    return besuch;
  }
}
