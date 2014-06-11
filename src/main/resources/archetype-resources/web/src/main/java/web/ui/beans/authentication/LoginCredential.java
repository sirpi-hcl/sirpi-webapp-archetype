#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.ui.beans.authentication;

import java.io.Serializable;

public class LoginCredential  implements Serializable{

	private static final long serialVersionUID = 1L;

	private String loginUserId;
	
	transient private String password;
	
	
	public LoginCredential(){
		   super();
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLoginUserId() {
		return loginUserId;
	}
	
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	
}
