
package gudusoft.gsqlparser.sqlformatter.test;

import gudusoft.gsqlparser.sqlformatter.ui.util.UIUtil;

import org.eclipse.core.expressions.PropertyTester;

public class SQLFormatTester extends PropertyTester
{

	public boolean test( Object receiver, String property, Object[] args,
			Object expectedValue )
	{
		return UIUtil.getActiveEditor( ) != null;
	}
}