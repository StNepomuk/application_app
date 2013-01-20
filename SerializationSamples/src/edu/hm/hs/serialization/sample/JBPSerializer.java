package edu.hm.hs.serialization.sample;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import edu.hm.hs.serialization.sample.object.Address;

/**
 * Serialisierungsbeispiel für JBP.
 * 
 * @author Stefan Wörner
 */
public final class JBPSerializer
{

	private JBPSerializer()
	{

	}

	/**
	 * Main-Methode.
	 * 
	 * @param args
	 *            Aufrufparameter
	 */
	public static void main( String[] args )
	{
		Address address = new Address();
		address.setStreet( "Lothstr." );
		address.setHouseNumber( "64" );
		address.setZipCode( 80335 );
		address.setCity( "München" );

		XMLEncoder enc = null;

		try
		{
			enc = new XMLEncoder( new FileOutputStream( new File( "jbp_serialization.xml" ) ) );
			enc.writeObject( address );
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (enc != null)
			{
				enc.close();
			}
		}

		XMLDecoder dec = null;

		try
		{
			dec = new XMLDecoder( new FileInputStream( new File( "jbp_serialization.xml" ) ) );

			Address addressRead = (Address) dec.readObject();
			System.out.println( addressRead );
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (dec != null)
			{
				dec.close();
			}
		}
	}
}
