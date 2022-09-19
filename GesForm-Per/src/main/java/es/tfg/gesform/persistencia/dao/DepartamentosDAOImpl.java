package es.tfg.gesform.persistencia.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.tfg.comun.constantes.Constantes;
import es.tfg.comun.utils.Utilidades;
import es.tfg.gesform.persistencia.vo.DepartamentosVO;

@Repository
public class DepartamentosDAOImpl extends HibernateDaoSupport implements DepartamentosDAO {

	@Autowired
	public DepartamentosDAOImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	 

	@Transactional
	public List<DepartamentosVO> selectAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(DepartamentosVO.class);
		criteria.add(Restrictions.isNull("fechaBaja"));
		
		return (List<DepartamentosVO>) this.getHibernateTemplate().findByCriteria(criteria);
//		return this.getHibernateTemplate().loadAll(ConsejeriasVO.class);
		
	}

	@Transactional
	public DepartamentosVO selectByPrimaryKey(Long id) {
		return this.getHibernateTemplate().get(DepartamentosVO.class, id);
	}

	@Transactional
	public void insert(DepartamentosVO obj) {
		obj.setFechaAlta(new Date());
		//obj.setUsuarioUltimaModificacion(datosUsuario());
		this.getHibernateTemplate().save(obj);
	}

	@Transactional
	public List<DepartamentosVO> findByExample(DepartamentosVO obj) {
		return this.getHibernateTemplate().findByExample(obj);
	}

	@Transactional
	public void update(DepartamentosVO obj) {
	//	obj.setFechaUltimaModificacion(new Date());
	//	obj.setUsuarioUltimaModificacion(datosUsuario());
		this.getHibernateTemplate().update(obj);
	}

	@Transactional
	public void delete(DepartamentosVO obj) {
		
		this.getHibernateTemplate().delete(obj);
	}


	@Transactional
	public List<DepartamentosVO> obtenerTodosActivos() {
		List<DepartamentosVO> listaVO  = new ArrayList<DepartamentosVO>();

		DetachedCriteria criteria = DetachedCriteria.forClass(DepartamentosVO.class);

		criteria.add(Restrictions.isNull("fechaBaja"));
		criteria.addOrder(Order.desc("fechaAlta"));
		
		listaVO = (List<DepartamentosVO>) this.getHibernateTemplate().findByCriteria(criteria);

		return listaVO;
	}
	
	public DepartamentosVO obtenerNombrePorId(Long idDepartamento) {
		
		DepartamentosVO departamento = null;
		
		DetachedCriteria criteria = DetachedCriteria.forClass(DepartamentosVO.class);
		// filtrar por codigo de identificaion
		criteria.add(Restrictions.eq("id", idDepartamento));
		List listaDepartamentos = this.getHibernateTemplate().findByCriteria(criteria);
		
		if (Utilidades.esListaConElementos(listaDepartamentos) && listaDepartamentos.size() == Constantes.NUMERO_UNO_int) {
		    departamento = (DepartamentosVO) listaDepartamentos.get(0);
		}
		
		return departamento;
	}
	
      public List<DepartamentosVO> obtenerDepartamentosPorEjemplo(DepartamentosVO example) {
		
		List<DepartamentosVO> l = null;
		
		DetachedCriteria criteria = DetachedCriteria.forClass(DepartamentosVO.class);

		if (example.getNombre() != null && example.getNombre().length() != 0) {
			criteria.add(Restrictions.ilike("nombre", example.getNombre(), MatchMode.ANYWHERE));
			criteria.addOrder(Order.asc("nombre"));
		}
		
		
		criteria.add(Restrictions.isNull("fechaBaja"));
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		
		
		l = (List<DepartamentosVO>) this.getHibernateTemplate().findByCriteria(criteria);
		
		return l;
	}
	
 
	
	
}
