package es.tfg.gesform.persistencia.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.tfg.gesform.persistencia.vo.FormulariosBloquesVO;
import es.tfg.gesform.persistencia.vo.FormulariosVO;

@Repository
public class FormulariosDAOImpl extends HibernateDaoSupport implements FormulariosDAO {

	@Autowired
	public FormulariosDAOImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Transactional
	public List<FormulariosVO> obtenerTodosFormularios(Integer limit) {
		List<FormulariosVO> listaVO  = new ArrayList<FormulariosVO>();

		DetachedCriteria criteria = DetachedCriteria.forClass(FormulariosVO.class);

		criteria.add(Restrictions.isNull("fechaBaja"));
		criteria.addOrder(Order.desc("fechaAlta"));

		if ( limit != null ) {
			listaVO = (List<FormulariosVO>) this.getHibernateTemplate().findByCriteria(criteria, 0, limit);
		}else {
			listaVO = (List<FormulariosVO>) this.getHibernateTemplate().findByCriteria(criteria);
		} 

		return listaVO;
	}

//	@Transactional
//	public ClientesVO selectByPrimaryKey(Integer clienteNumber) {
//		return this.getHibernateTemplate().get(ClientesVO.class, clienteNumber);
//	}

	@Transactional
	public void insertar(FormulariosVO obj)  throws Exception{
		obj.setFechaAlta(new Date());
//		obj.setIdeuuid(java.util.UUID.randomUUID().toString());		
//		obj.setUsuarioUltimaModificacion(datosUsuario());
		this.getHibernateTemplate().save(obj);
		//return (Long) this.getHibernateTemplate().save(obj);
	}
	
	@Transactional
	public void actualizar(FormulariosVO obj) {
//		obj.setFechaUltimaModificacion(new Date());
//		obj.setUsuarioUltimaModificacion(datosUsuario());
		this.getHibernateTemplate().update(obj);
	}
	

	public FormulariosVO selectByPrimaryKey(long id) {
		return this.getHibernateTemplate().get(FormulariosVO.class, id);
	}
	
	
	@Transactional
	public int obtenerOrdenBloque(long idForm)throws Exception {
		
		int orden;
		FormulariosBloquesVO bloque = null;
		
		DetachedCriteria criteria = DetachedCriteria.forClass(FormulariosBloquesVO.class);
		criteria.createAlias("formulario", "f");
		criteria.add(Restrictions.eq("f.id", idForm));
		criteria.add(Restrictions.isNull("fechaBaja"));
		criteria.addOrder(Order.desc("orden"));
		
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);		
		
		// settear la lista de objetos encontrados

	    List lista = this.getHibernateTemplate().findByCriteria(criteria);
	    if (lista.isEmpty())
	    	orden = 1;
	    else {
	    	bloque = (FormulariosBloquesVO) lista.get(0);
	    	orden = bloque.getOrden()+1;
	    }
	        
//	    if (TratamientoDeDatos.esListaConElementos(lista)) {
//	    	bloque = (FormulariosBloquesVO) lista.get(0);
//	        	
//	    }
		
		return orden;
		
	}

	public List<FormulariosVO> obtenerFormulariosFechasVOPorEjemplo(FormulariosVO example, Date fechaDesde, Date fechaHasta) throws Exception {
		List<FormulariosVO> l = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(FormulariosVO.class);

		if (example.getNombre() != null ) {
			criteria.add(Restrictions.like("nombre", example.getNombre(), MatchMode.ANYWHERE).ignoreCase());
		}
		
		
		if (example.getDepartamento() != null && example.getDepartamento().getId() != 0) {
			criteria.createAlias("departamento", "c");
			criteria.add(Restrictions.eq("c.id", example.getDepartamento().getId()));
			criteria.add(Restrictions.isNull("c.fechaBaja"));
		}

		
		if (fechaDesde !=null && fechaHasta !=null) {
			Criterion a = Restrictions.ge("fechaFin", fechaDesde);	
			Criterion b = Restrictions.le("fechaInicio", fechaHasta);
				
			Criterion ayb = Restrictions.and(a,b);
			criteria.add(ayb);
		}
		else if (fechaDesde != null) {
			criteria.add (Restrictions.ge("fechaInicio", fechaDesde));
			
		}
		else if (fechaHasta != null){
			Criterion c = Restrictions.le("fechaFin", fechaHasta);
			Criterion d = Restrictions.isNull("fechaFin");
			Criterion cod = Restrictions.or(c,d);
			criteria.add(cod);
		}
		

		criteria.add(Restrictions.isNull("fechaBaja"));
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc("nombre"));

		l = (List<FormulariosVO>) this.getHibernateTemplate().findByCriteria(criteria);

		return l;
	}

	@Transactional
	public List<FormulariosVO> obtenerFormulariosVOPorEjemplo(FormulariosVO example) throws Exception {
		return this.getHibernateTemplate().findByExample(example);
	}
	



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
