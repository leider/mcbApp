package mcb.model;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.logging.Logger;

import com.jgoodies.binding.beans.Model;

public abstract class McbModel extends Model {

	static final Logger LOGGER = Logger.getLogger(McbModel.class.getName());

	private static final long serialVersionUID = 1L;

	protected Long id;

	public Long getId() {
		return this.id;
	}

	public String toLogString() {
		StringWriter writer = new StringWriter();
		Class<?> clazz = this.getClass();
		writer.write(clazz.getName());
		writer.write(" - ");
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			if (!Modifier.isStatic(field.getModifiers())) {
				try {
					boolean wasAccessible = field.isAccessible();
					field.setAccessible(true);
					Object value = field.get(this);
					if (value != null) {
						writer.write(field.getName());
						writer.write(": ");
						writer.write(value.toString());
						writer.write(", ");
					}
					field.setAccessible(wasAccessible);
				} catch (Exception e) {
					McbModel.LOGGER.warning(e.getMessage());
				}
			}
		}
		return writer.toString();
	}
}
