package mcb.persistenz;

import java.lang.reflect.Type;
import java.util.Map;

import mcb.model.Besuch;
import mcb.model.Treffen;
import flexjson.ObjectBinder;
import flexjson.ObjectFactory;
import flexjson.factories.BeanObjectFactory;

public class BesuchFactory implements ObjectFactory {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object instantiate(ObjectBinder context, Object value, Type targetType, Class targetClass) {
		Besuch besuch = (Besuch) new BeanObjectFactory().instantiate(context, value, targetType, targetClass);
		besuch.setId(null);
		Map<String, Object> valueMap = (Map<String, Object>) value;
		for (Treffen treffen : ApplicationData.getAlleTreffen()) {
			Map<String, Object> treffenMap = (Map<String, Object>) valueMap.get("treffen");
			if (treffen.getId().longValue() == ((Number) treffenMap.get("id")).longValue()) {
				besuch.setTreffen(treffen);
			}
		}
		return besuch;
	}
}
