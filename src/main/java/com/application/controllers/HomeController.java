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
@ManagedBean(name = "homeBean")
public class HomeController extends BaseController<HomeController> {

	private static final String ABOUT = "about";
	private static final String HOME = "home";
	/**
	 * 
	 */
	private static final long serialVersionUID = -5379638333079611948L;

	@Override
	protected String getTransactionCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String openPage() throws ApplicationException {
		return HOME;
	}

	public String about() {
		return ABOUT;
	}

	@Override
	protected void resetEntityBean() {

	}

}
