//$Id: CompositeElementPropertyMapping.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.persister.collection;

import org.hibernate.MappingException;
import org.hibernate.engine.Mapping;
import org.hibernate.persister.entity.AbstractPropertyMapping;
import org.hibernate.type.AbstractComponentType;
import org.hibernate.type.Type;

/**
 * @author Gavin King
 */
public class CompositeElementPropertyMapping extends AbstractPropertyMapping {

	private final AbstractComponentType compositeType;
	
	public CompositeElementPropertyMapping(
			String[] elementColumns, 
			String[] elementFormulaTemplates, 
			AbstractComponentType compositeType, 
			Mapping factory)
	throws MappingException {

		this.compositeType = compositeType;

		initComponentPropertyPaths(null, compositeType, elementColumns, elementFormulaTemplates, factory);

	}

	public Type getType() {
		return compositeType;
	}

	protected String getEntityName() {
		return compositeType.getName();
	}

}