//$Id: Dom4jComponentTuplizer.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.tuple.component;

import org.dom4j.Element;
import org.hibernate.mapping.Component;
import org.hibernate.mapping.Property;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.tuple.Instantiator;
import org.hibernate.tuple.Dom4jInstantiator;

/**
 * A {@link ComponentTuplizer} specific to the dom4j entity mode.
 *
 * @author Gavin King
 * @author Steve Ebersole
 */
public class Dom4jComponentTuplizer extends AbstractComponentTuplizer  {

	public Class getMappedClass() {
		return Element.class;
	}

	public Dom4jComponentTuplizer(Component component) {
		super(component);
	}

	protected Instantiator buildInstantiator(Component component) {
		return new Dom4jInstantiator( component );
	}

	private PropertyAccessor buildPropertyAccessor(Property property) {
		//TODO: currently we don't know a SessionFactory reference when building the Tuplizer
		//      THIS IS A BUG (embedded-xml=false on component)
		// TODO : fix this after HHH-1907 is complete
		return PropertyAccessorFactory.getDom4jPropertyAccessor( property.getNodeName(), property.getType(), null );
	}

	protected Getter buildGetter(Component component, Property prop) {
		return buildPropertyAccessor(prop).getGetter( null, prop.getName() );
	}

	protected Setter buildSetter(Component component, Property prop) {
		return buildPropertyAccessor(prop).getSetter( null, prop.getName() );
	}

}
