//$Id: SchemaUpdateTask.java,v 1.1 2008/01/14 09:32:46 b18146 Exp $
package org.hibernate.tool.hbm2ddl;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.NamingStrategy;
import org.hibernate.util.ArrayHelper;
import org.hibernate.util.ReflectHelper;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.tools.ant.types.FileSet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * An Ant task for <tt>SchemaUpdate</tt>.
 *
 * <pre>
 * &lt;taskdef name="schemaupdate"
 *     classname="org.hibernate.tool.hbm2ddl.SchemaUpdateTask"
 *     classpathref="class.path"/&gt;
 *
 * &lt;schemaupdate
 *     properties="${build.classes.dir}/hibernate.properties"
 *     quiet="no"
 *     &lt;fileset dir="${build.classes.dir}"&gt;
 *         &lt;include name="*.hbm.xml"/&gt;
 *     &lt;/fileset&gt;
 * &lt;/schemaupdate&gt;
 * </pre>
 *
 * @see SchemaUpdate
 * @author Rong C Ou, Gavin King
 */
public class SchemaUpdateTask extends MatchingTask {

	private List fileSets = new LinkedList();
	private File propertiesFile = null;
	private File configurationFile = null;
	private boolean quiet = false;
	private boolean text = true;
	private String namingStrategy = null;

	public void addFileset(FileSet set) {
		fileSets.add(set);
	}

	/**
	 * Set a properties file
	 * @param propertiesFile the properties file name
	 */
	public void setProperties(File propertiesFile) {
		if ( !propertiesFile.exists() ) {
			throw new BuildException("Properties file: " + propertiesFile + " does not exist.");
		}

		log("Using properties file " + propertiesFile, Project.MSG_DEBUG);
		this.propertiesFile = propertiesFile;
	}

	/**
	 * Set a <literal>.cfg.xml</literal> file
	 * @param configurationFile the file name
	 */
	public void setConfig(File configurationFile) {
		this.configurationFile = configurationFile;
	}

	/**
     * Enable "text-only" mode. The schema will not
	 * be updated in the database.
	 * @param text true to enable text-only mode
     */
    public void setText(boolean text) {
        this.text = text;
    }

	/**
	 * Enable "quiet" mode. The schema will not be
	 * written to standard out.
	 * @param quiet true to enable quiet mode
	 */
	public void setQuiet(boolean quiet) {
		this.quiet = quiet;
	}

	/**
	 * Execute the task
	 */
	public void execute() throws BuildException {
		try {
			log("Running Hibernate Core SchemaUpdate."); 
			log("This is an Ant task supporting only mapping files, if you want to use annotations see http://tools.hibernate.org.");
			Configuration cfg = getConfiguration();
			getSchemaUpdate(cfg).execute(!quiet, !text);
		}
		catch (HibernateException e) {
			throw new BuildException("Schema text failed: " + e.getMessage(), e);
		}
		catch (FileNotFoundException e) {
			throw new BuildException("File not found: " + e.getMessage(), e);
		}
		catch (IOException e) {
			throw new BuildException("IOException : " + e.getMessage(), e);
		}
		catch (Exception e) {
			throw new BuildException(e);
		}
	}

	private String[] getFiles() {

		List files = new LinkedList();
		for ( Iterator i = fileSets.iterator(); i.hasNext(); ) {

			FileSet fs = (FileSet) i.next();
			DirectoryScanner ds = fs.getDirectoryScanner( getProject() );

			String[] dsFiles = ds.getIncludedFiles();
			for (int j = 0; j < dsFiles.length; j++) {
				File f = new File(dsFiles[j]);
				if ( !f.isFile() ) {
					f = new File( ds.getBasedir(), dsFiles[j] );
				}

				files.add( f.getAbsolutePath() );
			}
		}

		return ArrayHelper.toStringArray(files);
	}

	private Configuration getConfiguration() throws Exception {
		Configuration cfg = new Configuration();
		if (namingStrategy!=null) {
			cfg.setNamingStrategy(
					(NamingStrategy) ReflectHelper.classForName(namingStrategy).newInstance()
				);
		}
		if (configurationFile!=null) {
			cfg.configure( configurationFile );
		}

		String[] files = getFiles();
		for (int i = 0; i < files.length; i++) {
			String filename = files[i];
			if ( filename.endsWith(".jar") ) {
				cfg.addJar( new File(filename) );
			}
			else {
				cfg.addFile(filename);
			}
		}
		return cfg;
	}

	private SchemaUpdate getSchemaUpdate(Configuration cfg) throws HibernateException, IOException {
		Properties properties = new Properties();
		properties.putAll( cfg.getProperties() );
		if (propertiesFile == null) {
			properties.putAll( getProject().getProperties() );
		}
		else {
			properties.load( new FileInputStream(propertiesFile) );
		}
		cfg.setProperties(properties);
		return new SchemaUpdate(cfg);
	}

	public void setNamingStrategy(String namingStrategy) {
		this.namingStrategy = namingStrategy;
	}

}
