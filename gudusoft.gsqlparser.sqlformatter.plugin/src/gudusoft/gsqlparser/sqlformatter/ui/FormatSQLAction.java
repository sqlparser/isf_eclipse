
package gudusoft.gsqlparser.sqlformatter.ui;

import gudusoft.gsqlparser.sqlformatter.SQLFormatPlugin;
import gudusoft.gsqlparser.sqlformatter.core.SqlFormat;
import gudusoft.gsqlparser.sqlformatter.ui.util.UIUtil;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.ui.texteditor.ITextEditor;

public class FormatSQLAction extends ActionDelegate implements
		IObjectActionDelegate
{

	public void run( IAction action )
	{
		final ITextEditor editor = UIUtil.getActiveEditor( );
		final String[] sql = new String[1];
		final boolean[] formatSelectArea = new boolean[]{
			false
		};
		if ( editor.getSelectionProvider( ).getSelection( ) instanceof TextSelection )
		{
			sql[0] = ( (TextSelection) editor.getSelectionProvider( )
					.getSelection( ) ).getText( );
		}
		if ( sql[0] == null || sql[0].length( ) == 0 )
		{
			sql[0] = editor.getDocumentProvider( )
					.getDocument( editor.getEditorInput( ) )
					.get( );
			formatSelectArea[0] = false;
		}
		else
		{
			formatSelectArea[0] = true;
		}

		Thread thread = new Thread( "SQL Format Thread" ) {

			public void run( )
			{
				final String response = new SqlFormat( ).format( sql[0],
						SQLFormatPlugin.getDefault( )
								.getPreferenceStore( )
								.getString( SQLFormatPlugin.FORMAT_OPTIONS ) );
				Display.getDefault( ).syncExec( new Runnable( ) {

					public void run( )
					{
						if ( response != null && response.trim( ).length( ) > 0 )
						{
							if ( !formatSelectArea[0] )
							{
								editor.getDocumentProvider( )
										.getDocument( editor.getEditorInput( ) )
										.set( response );
							}
							else
							{
								int offset = ( (TextSelection) editor.getSelectionProvider( )
										.getSelection( ) ).getOffset( );
								int length = ( (TextSelection) editor.getSelectionProvider( )
										.getSelection( ) ).getLength( );
								IDocument document = editor.getDocumentProvider( )
										.getDocument( editor.getEditorInput( ) );
								try
								{
									document.replace( offset, length, response );
									editor.selectAndReveal( offset,
											response.length( ) );
								}
								catch ( BadLocationException e )
								{

								}
							}
						}
					}
				} );
			}
		};
		thread.setDaemon( true );
		thread.start( );
	}

	public void selectionChanged( IAction action, ISelection sel )
	{

	}

	public void setActivePart( IAction action, IWorkbenchPart targetPart )
	{

	}

}
