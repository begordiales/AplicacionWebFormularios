package es.tfg.gesform.comun.excepciones;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.log4j.Logger;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {
	private ExceptionHandler wrapped;

	private static final Logger log = Logger.getLogger(CustomExceptionHandler.class.getCanonicalName());

	public CustomExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {

		final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
		while (i.hasNext()) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			Throwable t = context.getException();

			final FacesContext fc = FacesContext.getCurrentInstance();
			final Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
			final NavigationHandler nav = fc.getApplication().getNavigationHandler();

			try {
				// log.log(Level.SEVERE, "Critical Exception!", t);
				log.error("Critical Exception!", t);
				requestMap.put("exceptionMessage", t.getMessage());
				nav.handleNavigation(fc, null, "error");
				fc.renderResponse();

			} finally {
				i.remove();
			}
		}
		getWrapped().handle();
	}
}