package edu.hm.hs.application.api.object.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import edu.hm.hs.application.api.object.AbstractRessourceObject;
import edu.hm.hs.application.api.object.enumeration.Gender;

/**
 * Resource für das Profil.
 * 
 * @author Stefan Wörner
 */
@JsonPropertyOrder( value = { "id", "firstname", "lastname", "eMailAddress", "dateOfBirth", "gender", "address", "skills",
		"contactInfos" }, alphabetic = true )
@JsonSerialize( include = Inclusion.NON_NULL )
@Produces( { MediaType.APPLICATION_JSON } )
@Consumes( { MediaType.APPLICATION_JSON } )
public class Profile extends AbstractRessourceObject
{

	private static final long serialVersionUID = -5190919068441710119L;

	private Long m_id;

	private String m_firstname;

	private String m_lastname;

	private String m_eMailAddress;

	private Date m_dateOfBirth;

	private Gender m_gender;

	private Address m_address;

	private List<Skill> m_skills;

	private List<ContactInfo> m_contactInfos;

	/**
	 * Standardkonstruktor.
	 */
	public Profile()
	{
		m_skills = new ArrayList<Skill>();
		m_contactInfos = new ArrayList<ContactInfo>();
	}

	/**
	 * Liefert das Attribut id.
	 * 
	 * @return id
	 */
	@JsonProperty( "id" )
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
	@JsonProperty( "id" )
	public void setId( Long id )
	{
		m_id = id;
	}

	/**
	 * Liefert das Attribut firstname.
	 * 
	 * @return firstname
	 */
	@JsonProperty( "firstname" )
	public String getFirstname()
	{
		return m_firstname;
	}

	/**
	 * Setzt das Attribut firstname.
	 * 
	 * @param firstname
	 *            zu setzender Wert für das Attribut firstname
	 */
	@JsonProperty( "firstname" )
	public void setFirstname( String firstname )
	{
		m_firstname = firstname;
	}

	/**
	 * Liefert das Attribut lastname.
	 * 
	 * @return lastname
	 */
	@JsonProperty( "lastname" )
	public String getLastname()
	{
		return m_lastname;
	}

	/**
	 * Setzt das Attribut lastname.
	 * 
	 * @param lastname
	 *            zu setzender Wert für das Attribut lastname
	 */
	@JsonProperty( "lastname" )
	public void setLastname( String lastname )
	{
		m_lastname = lastname;
	}

	/**
	 * Liefert das Attribut eMailAddress.
	 * 
	 * @return eMailAddress
	 */
	@JsonProperty( "eMailAddress" )
	public String geteMailAddress()
	{
		return m_eMailAddress;
	}

	/**
	 * Setzt das Attribut eMailAddress.
	 * 
	 * @param eMailAddress
	 *            zu setzender Wert für das Attribut eMailAddress
	 */
	@JsonProperty( "eMailAddress" )
	public void seteMailAddress( String eMailAddress )
	{
		m_eMailAddress = eMailAddress;
	}

	/**
	 * Liefert das Attribut dateOfBirth.
	 * 
	 * @return dateOfBirth
	 */
	@JsonProperty( "dateOfBirth" )
	public Date getDateOfBirth()
	{
		return m_dateOfBirth;
	}

	/**
	 * Setzt das Attribut dateOfBirth.
	 * 
	 * @param dateOfBirth
	 *            zu setzender Wert für das Attribut dateOfBirth
	 */
	@JsonProperty( "dateOfBirth" )
	public void setDateOfBirth( Date dateOfBirth )
	{
		m_dateOfBirth = dateOfBirth;
	}

	/**
	 * Liefert das Attribut gender.
	 * 
	 * @return gender
	 */
	@JsonProperty( "gender" )
	public Gender getGender()
	{
		return m_gender;
	}

	/**
	 * Setzt das Attribut gender.
	 * 
	 * @param gender
	 *            zu setzender Wert für das Attribut gender
	 */
	@JsonProperty( "gender" )
	public void setGender( Gender gender )
	{
		m_gender = gender;
	}

	/**
	 * Liefert das Attribut address.
	 * 
	 * @return address
	 */
	@JsonProperty( "address" )
	public Address getAddress()
	{
		return m_address;
	}

	/**
	 * Setzt das Attribut address.
	 * 
	 * @param address
	 *            zu setzender Wert für das Attribut address
	 */
	@JsonProperty( "address" )
	public void setAddress( Address address )
	{
		m_address = address;
	}

	/**
	 * Liefert das Attribut skills.
	 * 
	 * @return skills
	 */
	@JsonProperty( "skills" )
	public List<Skill> getSkills()
	{
		return m_skills;
	}

	/**
	 * Setzt das Attribut skills.
	 * 
	 * @param skills
	 *            zu setzender Wert für das Attribut skills
	 */
	@JsonProperty( "skills" )
	public void setSkills( List<Skill> skills )
	{
		m_skills = skills;
	}

	/**
	 * Fügt einen Skill hinzu.
	 * 
	 * @param skill
	 *            Hinzuzufügender Skill
	 */
	public void addSkill( Skill skill )
	{
		m_skills.add( skill );
	}

	/**
	 * Löscht den Skill.
	 * 
	 * @param skill
	 *            Zu löschender Skill
	 */
	public void removeSkill( Skill skill )
	{
		m_skills.remove( skill );
	}

	/**
	 * Liefert das Attribut contactInfos.
	 * 
	 * @return contactInfos
	 */
	@JsonProperty( "contactInfos" )
	public List<ContactInfo> getContactInfos()
	{
		return m_contactInfos;
	}

	/**
	 * Setzt das Attribut contactInfos.
	 * 
	 * @param contactInfos
	 *            zu setzender Wert für das Attribut contactInfos
	 */
	@JsonProperty( "contactInfos" )
	public void setContactInfos( List<ContactInfo> contactInfos )
	{
		m_contactInfos = contactInfos;
	}

	/**
	 * Fügt eine Kontakt-Info hinzu.
	 * 
	 * @param contactInfo
	 *            Hinzuzufügende Kontakt-Info
	 */
	public void addContactInfo( ContactInfo contactInfo )
	{
		m_contactInfos.add( contactInfo );
	}

	/**
	 * Löscht die Kontakt-Info.
	 * 
	 * @param contactInfo
	 *            Zu löschende Kontakt-Info
	 */
	public void removeContactInfo( ContactInfo contactInfo )
	{
		m_contactInfos.remove( contactInfo );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#getExclusionList()
	 */
	@Override
	protected String[] getExclusionList()
	{
		return new String[] {};
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode( INITIAL_NON_ZERO_ODD_NUMBER, MULTIPLIER_NON_ZERO_ODD_NUMBER, this, true,
				Profile.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, Profile.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		ReflectionToStringBuilder rsb = new ReflectionToStringBuilder( this, ToStringStyle.SHORT_PREFIX_STYLE );
		rsb.setAppendStatics( false );
		rsb.setAppendTransients( true );
		rsb.setUpToClass( Profile.class );
		rsb.setExcludeFieldNames( getExclusionList() );
		return rsb.toString();
	}
}
