package edu.hm.hs.serialization.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import edu.hm.hs.serialization.sample.object.Address;

/**
 * Serialisierungsbeispiel für JOS.
 * 
 * @author Stefan Wörner
 */
public final class BinarySerializer
{

	private BinarySerializer()
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

		OutputStream fos = null;

		try
		{
			fos = new FileOutputStream( new File( "jos_serialization.bin" ) );
			ObjectOutput o = new ObjectOutputStream( fos );
			o.writeObject( address );
		}
		catch (IOException e)
		{
			System.err.println( e );
		}
		finally
		{
			try
			{
				fos.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		InputStream fis = null;

		try
		{
			fis = new FileInputStream( new File( "jos_serialization.bin" ) );

			ObjectInput o = new ObjectInputStream( fis );
			Address addressRead = (Address) o.readObject();
			System.out.println( addressRead );
		}
		catch (IOException e)
		{
			System.err.println( e );
		}
		catch (ClassNotFoundException e)
		{
			System.err.println( e );
		}
		finally
		{
			try
			{
				fis.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
