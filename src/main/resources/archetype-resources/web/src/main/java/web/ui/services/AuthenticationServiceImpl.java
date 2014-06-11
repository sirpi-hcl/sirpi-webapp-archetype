#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.ui.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import ${package}.infra.annotations.Logging;

import ${package}.web.ui.beans.authentication.AppUser;
import ${package}.web.ui.beans.authentication.LoginCredential;

public class AuthenticationServiceImpl implements IAutheticationService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcl.sirpi.web.ui.services.IAutheticationService${symbol_pound}findByCredential(
	 * org.hcl.sirpi.web.ui.beans.authentication.LoginCredential)
	 */
	@Override
	@Logging
	public UserDetails challengeUser(LoginCredential loginCredential) {
		

		String loginUserId = loginCredential.getLoginUserId();
		String password = loginCredential.getPassword();

		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("admin"));

		AppUser gelsUser = new AppUser(loginUserId, "******", true, true,
				true, true, authorities);
		final PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(
				gelsUser, null);
		authentication.setAuthenticated(true);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return gelsUser;
	}
	
	
	

}
