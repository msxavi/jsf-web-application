/**
 * 
 */
package com.application.listeners;

import java.util.Date;
import java.util.Map;

import javax.el.MethodExpression;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mx.common.resources.Chronometer;


/**
 * 
 * @author Emerson
 *
 */
public class ApplicationPhaseListener implements PhaseListener {

	private static final long serialVersionUID = -7021464023661073447L;
	private static final Logger LOG = LoggerFactory
			.getLogger(ApplicationPhaseListener.class);
	private static final String SERVER_NAME = "serverName";
	private static final String CURRENT_DATE = "currentDate";
	private static final String NULL_STRING = "[null]";
	private Chronometer timer = new Chronometer();

	@Override
	public void beforePhase(PhaseEvent event) {
		boolean isFirstPhase = event.getPhaseId().equals(PhaseId.RESTORE_VIEW);
		if (!isFirstPhase) {
			return;
		}
		this.timer.restart();
		HttpServletRequest request = getRequest(event);
		storeAttributesAtRequest(request);
	}

	@Override
	public void afterPhase(PhaseEvent event) {
		boolean isLastPhase = event.getPhaseId()
				.equals(PhaseId.RENDER_RESPONSE);
		if (!isLastPhase) {
			return;
		}
		logRequestDuration(event);
	}

	/**
	 * @param event
	 */
	protected void logRequestDuration(PhaseEvent event) {
		if (!LOG.isDebugEnabled()) {
			return;
		}
		String action = getMethod(event);
		LOG.info("REQUEST ACTION: {}", action);
		long elapsedTime = this.timer.getElapsedTime();
		LOG.info("REQUEST ELAPSED TIME: {} ms", elapsedTime);
	}

	private String getMethod(PhaseEvent event) {
		String methodExpression = NULL_STRING;
		UICommand component = findInvokedCommandComponent(event
				.getFacesContext());
		if (component != null) {
			MethodExpression actionExpression = component.getActionExpression();
			if (actionExpression != null) {
				methodExpression = actionExpression.getExpressionString();
			}
		}
		return methodExpression;
	}

	private void storeAttributesAtRequest(HttpServletRequest request) {
		Date currentDate = new Date();
		request.setAttribute(CURRENT_DATE, currentDate);
		request.setAttribute(SERVER_NAME, request.getServerName());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

	private UICommand findInvokedCommandComponent(FacesContext context) {
		UIViewRoot view = context.getViewRoot();
		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();

		if (context.getPartialViewContext().isAjaxRequest()) {
			UIComponent component = view.findComponent(params
					.get("javax.faces.source"));
			if (component instanceof UICommand) {
				return (UICommand) component;
			}

		} else {
			for (String clientId : params.keySet()) {
				UIComponent component = view.findComponent(clientId);
				if (component instanceof UICommand) {
					return (UICommand) component;
				}
			}
		}

		return null;
	}

	/**
	 * @param event
	 * @return
	 */
	protected HttpServletRequest getRequest(PhaseEvent event) {
		return (HttpServletRequest) event.getFacesContext()
				.getExternalContext().getRequest();
	}

}
