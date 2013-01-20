package edu.hm.hs.serialization.sample;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import edu.hm.hs.serialization.sample.object.Address;

/**
 * @author Stefan Wörner
 */
public class JAXBSerializer
{

	public static void main( String[] args )
	{
		Address address = new Address();
		address.setStreet( "Lothstr." );
		address.setHouseNumber( "64" );
		address.setZipCode( 80335 );
		address.setCity( "München" );

		try
		{
			JAXBContext context = JAXBContext.newInstance( Address.class );

			Marshaller m = context.createMarshaller();
			m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
			m.marshal( address, new File( "jaxb_serialization.xml" ) );
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}

		try
		{
			JAXBContext context = JAXBContext.newInstance( Address.class );

			Unmarshaller m = context.createUnmarshaller();
			Address addressRead = (Address) m.unmarshal( new File( "jaxb_serialization.xml" ) );
			System.out.println( addressRead );
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
	}
}
