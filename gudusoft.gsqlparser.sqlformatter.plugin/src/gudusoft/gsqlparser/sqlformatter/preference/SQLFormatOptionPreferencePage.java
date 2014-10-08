
package gudusoft.gsqlparser.sqlformatter.preference;

import gudusoft.gsqlparser.sqlformatter.SQLFormatPlugin;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class SQLFormatOptionPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage
{

	private TextFieldEditor sqlFormatOptionsEditor;

	public SQLFormatOptionPreferencePage( )
	{
		super( FieldEditorPreferencePage.GRID );
		setPreferenceStore( SQLFormatPlugin.getDefault( ).getPreferenceStore( ) );
	}

	/**
	 * @see PreferencePage#createControl(Composite)
	 */
	public void createControl( Composite parent )
	{
		super.createControl( parent );
	}

	/**
	 * @see FieldEditorPreferencePage#createFieldEditors()
	 */
	protected void createFieldEditors( )
	{
		sqlFormatOptionsEditor = new TextFieldEditor( SQLFormatPlugin.FORMAT_OPTIONS,
				"Set the default SQL format options",
				getFieldEditorParent( ) );
		addField( sqlFormatOptionsEditor );
		getFieldEditorParent( ).setLayoutData( new GridData( GridData.FILL_BOTH ) );
		getFieldEditorParent( ).getParent( ).layout( );
		getFieldEditorParent( ).layout( );
	}

	/**
	 * @see IWorkbenchPreferencePage#init(IWorkbench)
	 */
	public void init( IWorkbench arg0 )
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#initialize()
	 */
	protected void initialize( )
	{
		super.initialize( );
	}

	protected void performDefaults( )
	{
		sqlFormatOptionsEditor.setStringValue( SQLFormatPlugin.getDefault( )
				.getPreferenceStore( )
				.getDefaultString( SQLFormatPlugin.FORMAT_OPTIONS ) );
		super.performDefaults( );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse
	 * .jface.util.PropertyChangeEvent)
	 */
	public void propertyChange( PropertyChangeEvent event )
	{
		super.propertyChange( event );
	}

}
