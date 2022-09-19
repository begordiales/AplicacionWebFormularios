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


import es.tfg.gesform.persistencia.vo.FormulariosCamposVO;
import es.tfg.gesform.persistencia.vo.FormulariosVO;


@Repository
public class FormulariosBloquesCamposDAOImpl extends HibernateDaoSupport implements FormulariosBloquesCamposDAO {

	@Autowired
	public FormulariosBloquesCamposDAOImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public FormulariosCamposVO selectByPrimaryKey(long id) {
		return this.getHibernateTemplate().get(FormulariosCamposVO.class, id);
	}
	

	@Transactional
	public Long insertarBloqueCampoPlantilla(FormulariosCamposVO campovo) throws Exception{

		campovo.setFechaAlta(new Date());
		this.getHibernateTemplate().save(campovo);	
		return campovo.getId();
					
	}
	
	@Transactional
	public void actualizarCampo(FormulariosCamposVO campovo) throws Exception{

		campovo.setFechaAlta(new Date());
		this.getHibernateTemplate().update(campovo);	

					
	}

	public List<FormulariosCamposVO> obtenerCamposBloque(long idBloque) {
		DetachedCriteria criteria = DetachedCriteria.forClass(FormulariosCamposVO.class);
		criteria.createAlias("bloque", "b");
		criteria.add(Restrictions.eq("b.id", idBloque));
		criteria.add(Restrictions.isNull("fechaBaja"));
		criteria.addOrder(Order.asc("orden"));
		
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);		
		
		// settear la lista de objetos encontrados
		List lista = this.getHibernateTemplate().findByCriteria(criteria); 
		
		return lista;
	}


	
	
}
