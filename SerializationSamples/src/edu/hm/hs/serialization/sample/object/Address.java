package edu.hm.hs.serialization.sample.object;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Resource für die Adresse.
 * 
 * @author Stefan Wörner
 */
@XmlRootElement
public class Address implements Serializable
{

	private static final long serialVersionUID = 9048690440773846206L;

	transient private Long m_id;

	private String m_street;

	private String m_houseNumber;

	private Integer m_zipCode;

	private String m_city;

	// Getter und Setter ...

	/**
	 * Liefert das Attribut id.
	 * 
	 * @return id
	 */
	public Long getId()
	{
		return m_id;
	}

	/**
	 * Setzt das Attribut id.
	 * 
	 * @param id
	 *            zu setzender Wert für das Attribut id
	 */
	public void setId( Long id )
	{
		m_id = id;
	}

	/**
	 * Liefert das Attribut street.
	 * 
	 * @return street
	 */
	public String getStreet()
	{
		return m_street;
	}

	/**
	 * Setzt das Attribut street.
	 * 
	 * @param street
	 *            zu setzender Wert für das Attribut street
	 */
	public void setStreet( String street )
	{
		m_street = street;
	}

	/**
	 * Liefert das Attribut houseNumber.
	 * 
	 * @return houseNumber
	 */
	public String getHouseNumber()
	{
		return m_houseNumber;
	}

	/**
	 * Setzt das Attribut houseNumber.
	 * 
	 * @param houseNumber
	 *            zu setzender Wert für das Attribut houseNumber
	 */
	public void setHouseNumber( String houseNumber )
	{
		m_houseNumber = houseNumber;
	}

	/**
	 * Liefert das Attribut zipCode.
	 * 
	 * @return zipCode
	 */
	public Integer getZipCode()
	{
		return m_zipCode;
	}

	/**
	 * Setzt das Attribut zipCode.
	 * 
	 * @param zipCode
	 *            zu setzender Wert für das Attribut zipCode
	 */
	public void setZipCode( Integer zipCode )
	{
		m_zipCode = zipCode;
	}

	/**
	 * Liefert das Attribut city.
	 * 
	 * @return city
	 */
	public String getCity()
	{
		return m_city;
	}

	/**
	 * Setzt das Attribut city.
	 * 
	 * @param city
	 *            zu setzender Wert für das Attribut city
	 */
	public void setCity( String city )
	{
		m_city = city;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Address [m_street=" + m_street + ", m_houseNumber=" + m_houseNumber + ", m_zipCode=" + m_zipCode + ", m_city="
				+ m_city + "]";
	}
}
