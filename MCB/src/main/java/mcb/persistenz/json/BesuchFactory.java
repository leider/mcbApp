package mcb.persistenz.json;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import mcb.model.Besuch;
import mcb.model.Treffen;
import flexjson.ObjectBinder;
import flexjson.ObjectFactory;
import flexjson.factories.BeanObjectFactory;

public class BesuchFactory implements ObjectFactory {

  private final List<Treffen> treffens;

  public BesuchFactory(List<Treffen> treffens) {
    super();
    this.treffens = treffens;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public Object instantiate(ObjectBinder context, Object value, Type targetType, Class targetClass) {
    Besuch besuch = (Besuch) new BeanObjectFactory().instantiate(context, value, targetType, targetClass);
    Map<String, Object> valueMap = (Map<String, Object>) value;
    for (Treffen treffen : this.treffens) {
      Map<String, Object> treffenMap = (Map<String, Object>) valueMap.get("treffen");
      if (treffen.getId() == ((Number) treffenMap.get("id")).longValue()) {
        besuch.setTreffen(treffen);
      }
    }
    return besuch;
  }
}
