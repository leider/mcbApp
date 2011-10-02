package mcb.model;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + this.id);
		return result;
	}

	public boolean isNeu() {
		return this.getId() == 0;
	}

	public void setId(long id) {
		this.id = id;
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
