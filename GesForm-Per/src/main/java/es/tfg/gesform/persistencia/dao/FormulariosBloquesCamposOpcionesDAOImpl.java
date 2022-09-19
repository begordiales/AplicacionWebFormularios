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


import es.tfg.gesform.persistencia.vo.FormulariosCamposOpcionesVO;


@Repository
public class FormulariosBloquesCamposOpcionesDAOImpl extends HibernateDaoSupport implements FormulariosBloquesCamposOpcionesDAO {

	@Autowired
	public FormulariosBloquesCamposOpcionesDAOImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Transactional
	public void insertarBloqueCampoOpcionesPlantilla(FormulariosCamposOpcionesVO opcionesvo) throws Exception{

		opcionesvo.setFechaAlta(new Date());
		this.getHibernateTemplate().save(opcionesvo);	
//		return campovo.getId();
					
	}

	public List<FormulariosCamposOpcionesVO> obtenerOpcionesCampos(long idCampo) {
		DetachedCriteria criteria = DetachedCriteria.forClass(FormulariosCamposOpcionesVO.class);
		criteria.createAlias("campo", "c");
		criteria.add(Restrictions.eq("c.id", idCampo));
		criteria.add(Restrictions.isNull("fechaBaja"));
		criteria.addOrder(Order.asc("orden"));
		
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);		
		
		// settear la lista de objetos encontrados
		List lista = this.getHibernateTemplate().findByCriteria(criteria); 
		
		return lista;
	}





	
	
}
