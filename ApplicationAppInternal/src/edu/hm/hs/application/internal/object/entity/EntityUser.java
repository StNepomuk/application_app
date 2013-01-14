package edu.hm.hs.application.internal.object.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import edu.hm.hs.application.internal.object.AbstractEntityObject;

/**
 * Entität für den Benutzer.
 * 
 * @author Stefan Wörner
 */
@Entity( name = "User" )
@Table( name = "users" )
public class EntityUser extends AbstractEntityObject
{

	private static final long serialVersionUID = 4457962152002383683L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id", nullable = false, unique = true )
	private Long m_id;

	@Column( name = "username", length = 255, nullable = false )
	private String m_username;

	@Column( name = "password", length = 255, nullable = false )
	private String m_password;

	// Bidirektionale 1:1 Beziehung (Inverse Seite)
	@OneToOne( cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "m_user" )
	private EntityProfile m_profile;

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
	 * Liefert das Attribut username.
	 * 
	 * @return username
	 */
	public String getUsername()
	{
		return m_username;
	}

	/**
	 * Setzt das Attribut username.
	 * 
	 * @param username
	 *            zu setzender Wert für das Attribut username
	 */
	public void setUsername( String username )
	{
		m_username = username;
	}

	/**
	 * Liefert das Attribut password.
	 * 
	 * @return password
	 */
	public String getPassword()
	{
		return m_password;
	}

	/**
	 * Setzt das Attribut password.
	 * 
	 * @param password
	 *            zu setzender Wert für das Attribut password
	 */
	public void setPassword( String password )
	{
		m_password = password;
	}

	/**
	 * Liefert das Attribut profile.
	 * 
	 * @return profile
	 */
	public EntityProfile getProfile()
	{
		return m_profile;
	}

	/**
	 * Setzt das Attribut profile.
	 * 
	 * @param profile
	 *            zu setzender Wert für das Attribut profile
	 */
	public void setProfile( EntityProfile profile )
	{
		m_profile = profile;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#getExclusionList()
	 */
	@Override
	protected String[] getExclusionList()
	{
		return new String[] { "m_id", "m_password", "m_profile" };
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
				EntityUser.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, EntityUser.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#toString()
	 */
	@Override
	public String toString()
	{
		ReflectionToStringBuilder rsb = new ReflectionToStringBuilder( this, ToStringStyle.SHORT_PREFIX_STYLE );
		rsb.setAppendStatics( false );
		rsb.setAppendTransients( true );
		rsb.setUpToClass( EntityUser.class );
		// rsb.setExcludeFieldNames( getExclusionList() );
		rsb.setExcludeFieldNames( "m_profile" );
		return rsb.toString();
	}
}
