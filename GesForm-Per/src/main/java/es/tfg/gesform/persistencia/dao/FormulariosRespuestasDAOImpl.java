package es.tfg.gesform.persistencia.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.tfg.gesform.persistencia.vo.FormulariosBloquesVO;
import es.tfg.gesform.persistencia.vo.FormulariosCamposVO;
import es.tfg.gesform.persistencia.vo.FormulariosRespuestasVO;


@Repository
public class FormulariosRespuestasDAOImpl extends HibernateDaoSupport implements FormulariosRespuestasDAO {

	@Autowired
	public FormulariosRespuestasDAOImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}


	@Transactional
	public void insertar(FormulariosRespuestasVO obj)  throws Exception{

		this.getHibernateTemplate().save(obj);

	}
	
	@Transactional
	public void actualizar(FormulariosRespuestasVO obj) {
//		obj.setFechaUltimaModificacion(new Date());
//		obj.setUsuarioUltimaModificacion(datosUsuario());
		this.getHibernateTemplate().update(obj);
	}

	public FormulariosRespuestasVO selectByPrimaryKey(long id) {
		return this.getHibernateTemplate().get(FormulariosRespuestasVO.class, id);
	}


	public FormulariosRespuestasVO obtenerRespuestaCampo(long idEnvio, long idCampo) throws Exception {
		FormulariosRespuestasVO respuesta = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(FormulariosRespuestasVO.class);
		criteria.createAlias("envio", "e");
		criteria.createAlias("campo", "c");
		criteria.add(Restrictions.eq("e.id", idEnvio));
		criteria.add(Restrictions.eq("c.id", idCampo));
		criteria.add(Restrictions.isNull("fechaBaja"));
			
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);	
			
		List lista = this.getHibernateTemplate().findByCriteria(criteria);
		if (!lista.isEmpty()) {
			respuesta = (FormulariosRespuestasVO) lista.get(0);

		}
			
		return respuesta;

	}
	
	

	
}
