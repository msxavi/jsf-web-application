/**
 * 
 */
package com.application.controllers;

import javax.faces.bean.ManagedBean;

import com.mx.common.exceptions.ApplicationException;
import com.mx.jsf.controllers.base.BaseController;

/**
 * @author emerson.xavier
 *
 */
@ManagedBean(name = "loginBean")
public class LoginController extends BaseController<LoginController> {

	private static final String LOGOUT_SUCCESS = "logout-success";
	private static final String LOGIN_SUCCESS = "login-success";
	private static final long serialVersionUID = 3202647699478878149L;

	private String username;
	private String password;

	/**
	 * 
	 * @return String
	 */
	public String doLogin() {
		addInfoMessage("application_login_message");
		return LOGIN_SUCCESS;
	}

	/**
	 * 
	 * @return String
	 */
	public String doLogout() {
		addInfoMessage("application_logout_message");
		return LOGOUT_SUCCESS;
	}

	@Override
	public String openPage() throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void resetEntityBean() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getTransactionCode() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
