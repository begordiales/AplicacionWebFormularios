package es.tfg.gesform.persistencia.dao;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.tfg.gesform.persistencia.vo.ValoresTiposVO;

@Repository
public class ValoresTiposDAOImpl extends HibernateDaoSupport implements ValoresTiposDAO {

	@Autowired
	public ValoresTiposDAOImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Transactional
	public List<ValoresTiposVO> obtenerTodosActivos() {
		List<ValoresTiposVO> listaVO  = new ArrayList<ValoresTiposVO>();

		DetachedCriteria criteria = DetachedCriteria.forClass(ValoresTiposVO.class);

		criteria.add(Restrictions.isNull("fechaBaja"));
		criteria.addOrder(Order.desc("fechaAlta"));
		
		listaVO = (List<ValoresTiposVO>) this.getHibernateTemplate().findByCriteria(criteria);

		return listaVO;
	}
	
	@Transactional
	public List<ValoresTiposVO> obtenerValoresTiposPorCodBasica(String tipo) {
		
	
		DetachedCriteria criteria = DetachedCriteria.forClass(ValoresTiposVO.class);
		criteria.createAlias("tipo", "t");
		criteria.add(Restrictions.eq("t.codigo", tipo));
		criteria.add(Restrictions.isNull("fechaBaja"));
		criteria.addOrder(Order.asc("nombre"));
		
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);		
		
		// settear la lista de objetos encontrados
		List lista = this.getHibernateTemplate().findByCriteria(criteria); 
		
		return lista;
		
	}
	
	@Transactional
	public ValoresTiposVO selectByPrimaryKey(long id) {
		return this.getHibernateTemplate().get(ValoresTiposVO.class, id);
	}

//	@Transactional
//	public void insert(ClientesVO cliente) {
//		this.getHibernateTemplate().save(cliente);
//	}

//	@Transactional
//	public List<ClientesVO> findByExample(ClientesVO cliente) {
//		return this.getHibernateTemplate().findByExample(cliente);
//	}

//	@Transactional
//	public void update(ClientesVO cliente) {
//		this.getHibernateTemplate().update(cliente);
//	}
	
//	@Transactional
//	public void delete(ClientesVO cliente){
//		this.getHibernateTemplate().delete(cliente);
//	}
	
	
}
