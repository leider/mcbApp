//$Id: NoArgSQLFunction.java,v 1.1 2008/01/14 09:32:44 b18146 Exp $
package org.hibernate.dialect.function;

import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.engine.Mapping;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.Type;


/**
 * A function which takes no arguments
 * @author Michi
 */
public class NoArgSQLFunction implements SQLFunction {
    private Type returnType;
    private boolean hasParenthesesIfNoArguments;
    private String name;

    public NoArgSQLFunction(String name, Type returnType) {
        this(name, returnType, true);
    }

    public NoArgSQLFunction(String name, Type returnType, boolean hasParenthesesIfNoArguments) {
        this.returnType = returnType;
        this.hasParenthesesIfNoArguments = hasParenthesesIfNoArguments;
        this.name = name;
    }

    public Type getReturnType(Type columnType, Mapping mapping) throws QueryException {
        return returnType;
    }

    public boolean hasArguments() {
        return false;
    }

    public boolean hasParenthesesIfNoArguments() {
        return hasParenthesesIfNoArguments;
    }
    
    public String render(List args, SessionFactoryImplementor factory) throws QueryException {
    	if ( args.size()>0 ) {
    		throw new QueryException("function takes no arguments: " + name);
    	}
    	return hasParenthesesIfNoArguments ? name + "()" : name;
    }
}
