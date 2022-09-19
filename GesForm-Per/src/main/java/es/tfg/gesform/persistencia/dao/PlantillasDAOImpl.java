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

import es.tfg.gesform.persistencia.vo.PlantillasCamposOpcionesVO;
import es.tfg.gesform.persistencia.vo.PlantillasCamposVO;
import es.tfg.gesform.persistencia.vo.PlantillasVO;

@Repository
public class PlantillasDAOImpl extends HibernateDaoSupport implements PlantillasDAO {

	@Autowired
	public PlantillasDAOImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public PlantillasVO obtenerPlantillaPorCodigo(String codigo) {

		PlantillasVO plantilla = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(PlantillasVO.class);

		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.add(Restrictions.isNull("fechaBaja"));

		List l = this.getHibernateTemplate().findByCriteria(criteria);

		if (l != null && l.size() > 0) {
			plantilla = (PlantillasVO) l.get(0);
		}

		return plantilla;

	}

	public List<PlantillasCamposVO> obtenerCamposPlantillaPorCodigoPlantilla(String codigo) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(PlantillasCamposVO.class);
		criteria.createAlias("plantilla", "p");
		criteria.add(Restrictions.eq("p.codigo", codigo));
		criteria.add(Restrictions.isNull("fechaBaja"));
		criteria.addOrder(Order.asc("orden"));
		
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);		
		
		// settear la lista de objetos encontrados
		List lista = this.getHibernateTemplate().findByCriteria(criteria); 
		
		return lista;
	}
	
	public List<PlantillasCamposOpcionesVO> obtenerOpcionesCampoPorIdCampo(long idCampo){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(PlantillasCamposOpcionesVO.class);
		criteria.createAlias("campo", "c");
		criteria.add(Restrictions.eq("c.id", idCampo));
		criteria.add(Restrictions.isNull("fechaBaja"));
		criteria.addOrder(Order.asc("orden"));
		
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);		
		
		// settear la lista de objetos encontrados
		List lista = this.getHibernateTemplate().findByCriteria(criteria); 
		
		return lista;
		
	}

//	@Transactional
//	public List<FormulariosVO> obtenerTodosFormularios(Integer limit) {
//		List<FormulariosVO> listaVO  = new ArrayList<FormulariosVO>();
//
//		DetachedCriteria criteria = DetachedCriteria.forClass(FormulariosVO.class);
//
//		criteria.add(Restrictions.isNull("fechaBaja"));
//		criteria.addOrder(Order.desc("fechaAlta"));
//
//		if ( limit != null ) {
//			listaVO = (List<FormulariosVO>) this.getHibernateTemplate().findByCriteria(criteria, 0, limit);
//		}else {
//			listaVO = (List<FormulariosVO>) this.getHibernateTemplate().findByCriteria(criteria);
//		} 
//
//		return listaVO;
//	}


//	@Transactional
//	public void insertar(FormulariosVO obj)  throws Exception{
//		obj.setFechaAlta(new Date());
//		this.getHibernateTemplate().save(obj);
//	}
	
//	@Transactional
//	public void actualizar(FormulariosVO obj) {
//		this.getHibernateTemplate().update(obj);
//	}
//
//	public FormulariosVO selectByPrimaryKey(long id) {
//		return this.getHibernateTemplate().get(FormulariosVO.class, id);
//	}
	



	
	
}
