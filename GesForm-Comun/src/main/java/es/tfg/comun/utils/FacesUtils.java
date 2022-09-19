package es.tfg.comun.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;





/**
 * <p>
 * FacesUtils
 * </p>
 * Clase estática que implementa métodos para ayudar a utilizar FacesContext.

 */
public class FacesUtils {

	protected static final Logger log = Logger.getLogger(FacesUtils.class);

	public static ExternalContext getExternalContext() {
		FacesContext faces = getFacesContext();
		ExternalContext context = faces.getExternalContext();
		return context;
	}

	/*
	 * Faces Object Helpers
	 */
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * Obitene el valor de un parametro de configuracion, para ello lee la ruta del fichero de configuracion en el properties ruta_parametros.
	 * @param name parametro que se quiere leer.
	 * @return valor del parametro.
	 * @throws Exception 
	 */
	public static String getParameterConfg(String name) throws Exception {

		InputStreamReader reader;
		FileInputStream fis;
		String valorParametro = new String();

		String rutaParametros;
		try {
			rutaParametros = ResourceBundle.getBundle("ruta_parametros").getString("ruta");
		} catch (Exception e) {
			throw new Exception("No se encuentra el valor ruta en ruta_parametros");
		}

		File filePa = new File(rutaParametros);
		if (filePa.isFile()) {

			try {

				fis = new FileInputStream(filePa);
				reader = new InputStreamReader(fis, Charset.forName("UTF-8"));
				PropertyResourceBundle bundle;
				bundle = new PropertyResourceBundle(reader);
				valorParametro = bundle.getString(name);
				fis.close();

			} catch (IOException e) {			
				throw new Exception("No existe el parametro de configuación "+name);
			}
		}else{
			throw new Exception("No se encuentra el fichero de configuración " + rutaParametros);
		}

		return valorParametro;
	}

	/**
	 * Obtiene la Petición del HttpServletRequest
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		return request;
	}

	/**
	 * Método que devuelve un valor de la session.
	 * @param name
	 *            nombre del identificador que se desea coger de la sesion.
	 * @return Object.
	 */
	public static Object getSessionParameter(String name) {
		try {
			return FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().get(name);
		} catch (Exception e) {
			return null;
		}

	}

	public static FacesMessage obtenerMensajeProperties(
			FacesMessage.Severity tipoMensaje, String codigoMensaje,
			String valorCampo) {
		FacesMessage m = new FacesMessage(tipoMensaje, ResourceBundle
				.getBundle("msgModelo050").getString(codigoMensaje)
				.replace(" ", " ")
				+ valorCampo, null);
		return m;
	}

	/**
	 * Método que insertar un valor en la sesion.
	 * @param name
	 *            identificador en la sesion.
	 * @param object
	 *            valor del identificador.
	 */
	public static void setSessionParameter(String name, Object object) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
		.put(name, object);
	}

	public HttpSession getSession() {
		ExternalContext context = getExternalContext();
		HttpSession session = (HttpSession) context.getSession(false);
		return session;
	}


	 
}
