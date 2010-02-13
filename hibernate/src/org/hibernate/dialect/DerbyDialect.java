//$Id: DerbyDialect.java,v 1.1 2008/01/14 09:32:45 b18146 Exp $
package org.hibernate.dialect;

import org.hibernate.Hibernate;
import org.hibernate.QueryException;
import org.hibernate.HibernateException;
import org.hibernate.engine.Mapping;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.Type;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.id.TableHiLoGenerator;
import org.hibernate.sql.CaseFragment;
import org.hibernate.sql.DerbyCaseFragment;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Simon Johnston
 *
 * Hibernate Dialect for Cloudscape 10 - aka Derby. This implements both an
 * override for the identity column generator as well as for the case statement
 * issue documented at:
 * http://www.jroller.com/comments/kenlars99/Weblog/cloudscape_soon_to_be_derby
 */
public class DerbyDialect extends DB2Dialect {

	public DerbyDialect() {
		super();
		registerFunction( "concat", new VarArgsSQLFunction( Hibernate.STRING, "(","||",")" ) );
		registerFunction( "trim", new DerbyTrimFunctionEmulation() );
	}

	/**
	 * This is different in Cloudscape to DB2.
	 */
	public String getIdentityColumnString() {
		return "not null generated always as identity"; //$NON-NLS-1
	}

	/**
	 * Return the case statement modified for Cloudscape.
	 */
	public CaseFragment createCaseFragment() {
		return new DerbyCaseFragment();
	}

	public boolean dropConstraints() {
	      return true;
	}

	public Class getNativeIdentifierGeneratorClass() {
		return TableHiLoGenerator.class;
	}

	public boolean supportsSequences() {
		return false;
	}

	public boolean supportsLimit() {
		return false;
	}

	public boolean supportsLimitOffset() {
		return false;
	}

	public String getQuerySequencesString() {
	   return null ;
	}

	/**
	 * A specialized function template to emulate the ANSI trim function on Derby DB
	 * since it does not support the full trim specification.  However, we cannot even
	 * fully emulate it because there is not standard 'replace' function either. :(
	 */
	public static class DerbyTrimFunctionEmulation implements SQLFunction {
		private static final SQLFunction LEADING_SPACE_TRIM = new SQLFunctionTemplate( Hibernate.STRING, "ltrim( ?1 )");
		private static final SQLFunction TRAILING_SPACE_TRIM = new SQLFunctionTemplate( Hibernate.STRING, "rtrim( ?1 )");
		private static final SQLFunction BOTH_SPACE_TRIM = new SQLFunctionTemplate( Hibernate.STRING, "ltrim( rtrim( ?1 ) )");
		private static final SQLFunction BOTH_SPACE_TRIM_FROM = new SQLFunctionTemplate( Hibernate.STRING, "ltrim( rtrim( ?2 ) )");

		public Type getReturnType(Type columnType, Mapping mapping) throws QueryException {
			return Hibernate.STRING;
		}

		public boolean hasArguments() {
			return true;
		}

		public boolean hasParenthesesIfNoArguments() {
			return false;
		}

		public String render(List args, SessionFactoryImplementor factory) throws QueryException {
			// according to both the ANSI-SQL and EJB3 specs, trim can either take
			// exactly one parameter or a variable number of parameters between 1 and 4.
			// from the SQL spec:
			//
			// <trim function> ::=
			//      TRIM <left paren> <trim operands> <right paren>
			//
			// <trim operands> ::=
			//      [ [ <trim specification> ] [ <trim character> ] FROM ] <trim source>
			//
			// <trim specification> ::=
			//      LEADING
			//      | TRAILING
			//      | BOTH
			//
			// If only <trim specification> is omitted, BOTH is assumed;
			// if <trim character> is omitted, space is assumed
			if ( args.size() == 1 ) {
				// we have the form: trim(trimSource)
				//      so we trim leading and trailing spaces
				return BOTH_SPACE_TRIM.render( args, factory );
			}
			else if ( "from".equalsIgnoreCase( ( String ) args.get( 0 ) ) ) {
				// we have the form: trim(from trimSource).
				//      This is functionally equivalent to trim(trimSource)
				return BOTH_SPACE_TRIM_FROM.render( args, factory );
			}
			else {
				// otherwise, a trim-specification and/or a trim-character
				// have been specified;  we need to decide which options
				// are present and "do the right thing"
				boolean leading = true;         // should leading trim-characters be trimmed?
				boolean trailing = true;        // should trailing trim-characters be trimmed?
				String trimCharacter;    		// the trim-character
				String trimSource;       		// the trim-source

				// potentialTrimCharacterArgIndex = 1 assumes that a
				// trim-specification has been specified.  we handle the
				// exception to that explicitly
				int potentialTrimCharacterArgIndex = 1;
				String firstArg = ( String ) args.get( 0 );
				if ( "leading".equalsIgnoreCase( firstArg ) ) {
					trailing = false;
				}
				else if ( "trailing".equalsIgnoreCase( firstArg ) ) {
					leading = false;
				}
				else if ( "both".equalsIgnoreCase( firstArg ) ) {
				}
				else {
					potentialTrimCharacterArgIndex = 0;
				}

				String potentialTrimCharacter = ( String ) args.get( potentialTrimCharacterArgIndex );
				if ( "from".equalsIgnoreCase( potentialTrimCharacter ) ) {
					trimCharacter = "' '";
					trimSource = ( String ) args.get( potentialTrimCharacterArgIndex + 1 );
				}
				else if ( potentialTrimCharacterArgIndex + 1 >= args.size() ) {
					trimCharacter = "' '";
					trimSource = potentialTrimCharacter;
				}
				else {
					trimCharacter = potentialTrimCharacter;
					if ( "from".equalsIgnoreCase( ( String ) args.get( potentialTrimCharacterArgIndex + 1 ) ) ) {
						trimSource = ( String ) args.get( potentialTrimCharacterArgIndex + 2 );
					}
					else {
						trimSource = ( String ) args.get( potentialTrimCharacterArgIndex + 1 );
					}
				}

				List argsToUse = new ArrayList();
				argsToUse.add( trimSource );
				argsToUse.add( trimCharacter );

				if ( trimCharacter.equals( "' '" ) ) {
					if ( leading && trailing ) {
						return BOTH_SPACE_TRIM.render( argsToUse, factory );
					}
					else if ( leading ) {
						return LEADING_SPACE_TRIM.render( argsToUse, factory );
					}
					else {
						return TRAILING_SPACE_TRIM.render( argsToUse, factory );
					}
				}
				else {
					throw new HibernateException( "cannot specify trim character when using Derby as Derby does not support the ANSI trim function, not does it support a replace function to properly emmulate it" );
				}
			}
		}
	}


	// Overridden informational metadata ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public boolean supportsLobValueChangePropogation() {
		return false;
	}
}
