
package gudusoft.gsqlparser.sqlformatter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class SQLFormatPlugin extends AbstractUIPlugin implements
		IPropertyChangeListener
{

	public static final String PLUGIN_ID = "gudusoft.gsqlparser.sqlformatter"; //$NON-NLS-1$
	public static final String FORMAT_OPTIONS = "formatOptions";

	private static BundleContext context;
	private static SQLFormatPlugin plugin;

	public static SQLFormatPlugin getDefault( )
	{
		return plugin;
	}

	public SQLFormatPlugin( )
	{
		plugin = this;
		initializeDefaultPreferences( this.getPreferenceStore( ) );
	}

	static BundleContext getContext( )
	{
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start( BundleContext bundleContext ) throws Exception
	{
		SQLFormatPlugin.context = bundleContext;
		getPreferenceStore( ).addPropertyChangeListener( this );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop( BundleContext bundleContext ) throws Exception
	{
		SQLFormatPlugin.context = null;
		getPreferenceStore( ).removePropertyChangeListener( this );
	}

	public void propertyChange( PropertyChangeEvent paramPropertyChangeEvent )
	{
	}

	protected void initializeDefaultPreferences( IPreferenceStore store )
	{
		try
		{
			store.setDefault( FORMAT_OPTIONS, readFormatOptions( ) );
		}
		catch ( IOException e )
		{
		}
	}

	public String readFormatOptions( ) throws FileNotFoundException,
			IOException
	{
		BufferedReader reader = null;
		try
		{
			StringBuffer result = new StringBuffer( 1024 );
			InputStream fis = this.getClass( )
					.getResourceAsStream( "/gudusoft/gsqlparser/sqlformatter/preference/fo.json" );
			reader = new BufferedReader( new InputStreamReader( fis, "utf-8" ) );
			char[] block = new char[512];
			while ( true )
			{
				int readLength = reader.read( block );
				if ( readLength == -1 )
					break;// end of file
				result.append( block, 0, readLength );
			}
			return result.toString( );
		}
		catch ( IOException e )
		{
			throw e;
		}
		finally
		{
			try
			{
				if ( reader != null )
					reader.close( );
			}
			catch ( IOException ex )
			{
			}
		}
	}
}
