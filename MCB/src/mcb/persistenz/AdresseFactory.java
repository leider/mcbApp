package mcb.persistenz;

import java.lang.reflect.Type;

import mcb.model.Adresse;
import mcb.model.Besuch;
import flexjson.ObjectBinder;
import flexjson.ObjectFactory;
import flexjson.factories.BeanObjectFactory;

public class AdresseFactory implements ObjectFactory {

	@SuppressWarnings("rawtypes")
	@Override
	public Object instantiate(ObjectBinder context, Object value, Type targetType, Class targetClass) {
		Adresse adresse = (Adresse) new BeanObjectFactory().instantiate(context, value, targetType, targetClass);
		for (Besuch besuch : adresse.getBesuchteTreffen()) {
			besuch.setAdresse(adresse);
		}
		return adresse;
	}

}
