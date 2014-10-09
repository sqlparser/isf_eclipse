
package gudusoft.gsqlparser.sqlformatter.ui.util;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;

public class UIUtil
{

	public static ITextEditor getActiveEditor( )
	{
		IWorkbenchPart editor = getActiveEditor( true );
		if ( editor instanceof ITextEditor )
		{
			return (ITextEditor) editor;
		}
		return null;
	}

	public static IWorkbenchPart getActiveEditor( boolean activePageOnly )
	{
		IWorkbenchWindow window = PlatformUI.getWorkbench( )
				.getActiveWorkbenchWindow( );

		if ( window != null )
		{
			if ( activePageOnly )
			{
				IWorkbenchPage pg = window.getActivePage( );

				if ( pg != null )
				{
					return pg.getActivePart( );
				}
			}
			else
			{
				IWorkbenchPage[] pgs = window.getPages( );

				for ( int i = 0; i < pgs.length; i++ )
				{
					IWorkbenchPage pg = pgs[i];

					if ( pg != null )
					{
						IWorkbenchPart part = pg.getActivePart( );

						if ( part != null )
						{
							return part;
						}
					}
				}
			}
		}

		return null;
	}

}
