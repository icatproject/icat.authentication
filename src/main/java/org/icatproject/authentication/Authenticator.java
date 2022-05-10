package org.icatproject.authentication;

import java.util.Map;

import jakarta.ejb.Remote;

import org.icatproject.core.IcatException;

/**
 * An interface to be implemented by an ICAT authenticator plugin.
 */
@Remote
public interface Authenticator {

	/**
	 * Return an Authentication object or throw an exception
	 * 
	 * @param credentials
	 *            a map with keys such as username and password. The names and
	 *            meanings of the keys is the responsibility of the implementor
	 *            of this interface.
	 * 
	 * @param remoteAddr
	 *            a string representation of the numeric form of an IP4 or IP6
	 *            address.
	 * 
	 * @return an Authentication object
	 * 
	 * @throws IcatException
	 *             of type
	 *             {@link org.icatproject.core.IcatException.IcatExceptionType#SESSION}
	 *             if unable to authenticate.
	 */
	Authentication authenticate(Map<String, String> credentials, String remoteAddr) throws IcatException;

	/**
	 * Return a Json string with a description of how to use the authenticator.
	 * A json string is used to allow flexibility in the future in what is
	 * returned. The structure currently takes the form as indicated below
	 * (where the line break used in the second example must not occur in the
	 * json).
	 * 
	 * <p>
	 * To indicates that no keys are expected:
	 * </p>
	 * 
	 * <pre>
	 * {"keys":[]}
	 * </pre>
	 * 
	 * <p>
	 * To indicate that two keys are expected: username and password. The
	 * username must start with a lower case letter and may be followed by lower
	 * case letters and digits. Any entry mechanism for the password should try
	 * to hide what is typed in:
	 * </p>
	 * 
	 * <pre>
	 * {"keys":[{"name":"username", "pattern":"[a-z]([a-z]|[0-9])*"},
	 *          {"name":"password", "hide":true}]}
	 * </pre>
	 * 
	 * 
	 * 
	 * @return a Json string.
	 * 
	 * @throws IcatException
	 *             if there are problems.
	 */
	String getDescription() throws IcatException;
}