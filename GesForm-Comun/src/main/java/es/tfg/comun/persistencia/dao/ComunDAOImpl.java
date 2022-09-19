package es.tfg.comun.persistencia.dao;

import java.util.List;

import javax.persistence.MappedSuperclass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import es.tfg.comun.constantes.Constantes;




/**
 * ComunDAOImpl.
 */
@MappedSuperclass
/** {@inheritDoc}} */
public abstract class ComunDAOImpl extends HibernateDaoSupport implements ComunDAO {

	/**
	 * Fichero donde se registrará el log de las operaciones en base de datos.
	 */
	static Log      log          = LogFactory.getLog("FORMULARIOS");
	private List    listaObjetos = null;
	private Integer maxResult    = Constantes.NUMERO_RESULTADOS_CONSULTA;


	public ComunDAOImpl() {
	}

/*
	public String datosUsuario() throws Exception {

		Object object = null;

		object = FacesUtils.getSessionParameter("loginBBean");

		try {

			object = FacesUtils.getSessionParameter("loginBBean");
			Method meth;

			meth = object.getClass().getMethod("getUsername");

			Object result = meth.invoke(object);

			String nombre=result.toString();
			return(nombre);

		} catch (DataAccessException e) {
			throw new Exception(e.getMessage());
		} 


	}
*/
}
