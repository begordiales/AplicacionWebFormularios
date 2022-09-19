package es.tfg.gesform.persistencia.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.tfg.gesform.persistencia.vo.FormulariosBloquesVO;

@Repository
public class FormulariosBloquesDAOImpl extends HibernateDaoSupport implements FormulariosBloquesDAO {

	@Autowired
	public FormulariosBloquesDAOImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * insertarBloquePlantilla inserta en la tabla FormulariosBloques
	 * Si son datos de una plantilla bloquevo tendrá valor en idplantilla 
	 */
	@Transactional
	public Long insertarBloquePlantilla(FormulariosBloquesVO bloquevo) throws Exception{
		//HABRÁ QUE HACER ALGUNA COMPROBACIÓN DEPENDIENDO DEL TIPO DE BLOQUE. POR EJEMPLO, TITULO SOLO PUEDE HABER UNO POR FORMULARIO
		
		bloquevo.setFechaAlta(new Date());
		this.getHibernateTemplate().save(bloquevo);	
		return bloquevo.getId(); 
					
	}

	public List<FormulariosBloquesVO> obtenerBloquesFormulario(long idForm) {
		DetachedCriteria criteria = DetachedCriteria.forClass(FormulariosBloquesVO.class);
		criteria.createAlias("formulario", "f");
		criteria.add(Restrictions.eq("f.id", idForm));
		criteria.add(Restrictions.isNull("fechaBaja"));
		criteria.addOrder(Order.asc("orden"));
		
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);		
		
		// settear la lista de objetos encontrados
		List lista = this.getHibernateTemplate().findByCriteria(criteria); 
		
		return lista;
	}

	public List<FormulariosBloquesVO> obtenerListaBloquesFormulario(long idForm) {
		DetachedCriteria criteria = DetachedCriteria.forClass(FormulariosBloquesVO.class);
		criteria.createAlias("formulario", "f");
		criteria.createAlias("plantilla", "p",JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("f.id", idForm));
		criteria.add(Restrictions.isNull("fechaBaja"));
		criteria.add(Restrictions.isNull("p.id"));
		criteria.addOrder(Order.asc("orden"));
		
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);		
		
		// settear la lista de objetos encontrados
		List lista = this.getHibernateTemplate().findByCriteria(criteria); 
		
		return lista;
	}
	
	
}
