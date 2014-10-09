
package gudusoft.gsqlparser.sqlformatter.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

public class SqlFormat
{

	public String format( String sql, String formatOptions )
	{
		try
		{
			String formatSql = "rqst_db_vendor=oracle&rqst_isf_client=eclipse_client&rqst_input_sql="
					+ URLEncoder.encode( sql, "utf-8" );

			if ( formatOptions != null && formatOptions.trim( ).length( ) > 0 )
			{
				formatSql += "&rqst_formatOptions=";
				formatSql += URLEncoder.encode( formatOptions, "utf-8" );
			}

			JSONObject jsonObject = new JSONObject( sendPost( "http://www.gudusoft.com/format.php",
					formatSql ) );
			return jsonObject.getString( "rspn_formatted_sql" );
		}
		catch ( Exception e )
		{
		}
		return null;
	}

	public String sendPost( String url, String param )
	{
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try
		{
			URL realUrl = new URL( url );
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection( );
			conn.setRequestProperty( "accept", "*/*" );
			conn.setRequestProperty( "connection", "Keep-Alive" );
			conn.setRequestProperty( "Content-Type",
					"application/x-www-form-urlencoded" );
			conn.setRequestProperty( "user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)" );
			conn.setDoOutput( true );
			conn.setDoInput( true );
			conn.connect( );
			out = new PrintWriter( conn.getOutputStream( ) );
			out.print( param );
			out.flush( );
			in = new BufferedReader( new InputStreamReader( conn.getInputStream( ) ) );
			String line;
			while ( ( line = in.readLine( ) ) != null )
			{
				result += line;
			}
		}
		catch ( Exception e )
		{
			System.out.println( "Send post request failed!\n" + e );
			e.printStackTrace( );
		}
		finally
		{
			try
			{
				if ( out != null )
				{
					out.close( );
				}
				if ( in != null )
				{
					in.close( );
				}
			}
			catch ( IOException ex )
			{
				ex.printStackTrace( );
			}
		}
		return result;
	}
}
