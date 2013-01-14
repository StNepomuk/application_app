package edu.hm.hs.application.api.object;

/**
 * Klasse enthält alle Fehler-Konstanten der ApplicationApp API.
 * 
 * @author Stefan Wörner
 */
public final class ErrorConstants
{

	/**
	 * Privater Konstructor.
	 */
	private ErrorConstants()
	{

	}

	/**
	 * Fehlermeldung für nicht gesetzten Vornamen.
	 */
	public static final String FIRSTNAME_EMPTY_ERROR_MSG = "Bitte geben Sie Ihren Vornamen an.";

	/**
	 * Fehlermeldung für nicht gesetzten Nachnamen.
	 */
	public static final String LASTNAME_EMPTY_ERROR_MSG = "Bitte geben Sie Ihren Nachnamen an.";

	/**
	 * Fehlermeldung für nicht gesetzte eMail-Adresse.
	 */
	public static final String EMAIL_ADDRESS_EMPTY_ERROR_MSG = "Bitte geben Sie Ihre eMail-Adresse an.";

	/**
	 * Fehlermeldung für ungültige eMail-Adresse.
	 */
	public static final String ILLEGAL_EMAIL_ADDRESS_ERROR_MSG = "Bitte geben Sie eine gültige eMail-Adresse an.";

	/**
	 * Fehlermeldung für nicht gesetzte Straße.
	 */
	public static final String STREET_EMPTY_ERROR_MSG = "Bitte geben Sie Ihre Straße an.";

	/**
	 * Fehlermeldung für nicht gesetzte Hausnummer.
	 */
	public static final String HOUSENUMBER_EMPTY_ERROR_MSG = "Bitte geben Sie Ihre Hausnummer an.";

	/**
	 * Fehlermeldung für nicht gesetzte PLZ.
	 */
	public static final String ZIPCODE_EMPTY_ERROR_MSG = "Bitte geben Sie Ihre Postleitzahl an.";

	/**
	 * Fehlermeldung für nungültige PLZ.
	 */
	public static final String ZIPCODE_LENGHT_ERROR_MSG = "Bitte geben Sie eine gültige Postleitzahl an.";

	/**
	 * Fehlermeldung für nicht gesetzte Stadt.
	 */
	public static final String CITY_EMPTY_ERROR_MSG = "Bitte geben Sie Ihre Stadt an.";
}
