package es.tfg.gesform.persistencia.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.tfg.gesform.persistencia.vo.FormulariosEnviosVO;
import es.tfg.comun.persistencia.dao.ComunDAOImpl;


@Repository
public class FormulariosEnviosDAOImpl extends ComunDAOImpl implements FormulariosEnviosDAO {

	@Autowired
	public FormulariosEnviosDAOImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

//	@Transactional
	public List<FormulariosEnviosVO> obtenerTodosEnvios(Integer limit) {
		List<FormulariosEnviosVO> listaVO  = new ArrayList<FormulariosEnviosVO>();

		DetachedCriteria criteria = DetachedCriteria.forClass(FormulariosEnviosVO.class);

		criteria.add(Restrictions.isNull("fechaBaja"));
		criteria.addOrder(Order.desc("fechaEnvio"));

		if ( limit != 0 ) {
			listaVO = (List<FormulariosEnviosVO>) this.getHibernateTemplate().findByCriteria(criteria, 0, limit);
		}else {
			listaVO = (List<FormulariosEnviosVO>) this.getHibernateTemplate().findByCriteria(criteria);
		} 

		return listaVO;
	}

//	@Transactional
//	public ClientesVO selectByPrimaryKey(Integer clienteNumber) {
//		return this.getHibernateTemplate().get(ClientesVO.class, clienteNumber);
//	}

	@Transactional
	public void insertar(FormulariosEnviosVO obj)  throws Exception{

		obj.setFechaEnvio(new Timestamp(System.currentTimeMillis()));
		this.getHibernateTemplate().save(obj);

	}
	
	@Transactional
	public void actualizar(FormulariosEnviosVO obj) {
//		obj.setFechaUltimaModificacion(new Date());
//		obj.setUsuarioUltimaModificacion(datosUsuario());
		this.getHibernateTemplate().update(obj);
	}

	public FormulariosEnviosVO selectByPrimaryKey(long id) {
		return this.getHibernateTemplate().get(FormulariosEnviosVO.class, id);
	}

	public List<FormulariosEnviosVO> obtenerEnviosForm(long idForm) {
		List<FormulariosEnviosVO> listaVO  = new ArrayList<FormulariosEnviosVO>();

		DetachedCriteria criteria = DetachedCriteria.forClass(FormulariosEnviosVO.class);
		criteria.createAlias("formulario", "f");
		criteria.add(Restrictions.eq("f.id", idForm));

		criteria.add(Restrictions.isNull("fechaBaja"));
		criteria.addOrder(Order.desc("fechaEnvio"));
		listaVO = (List<FormulariosEnviosVO>) this.getHibernateTemplate().findByCriteria(criteria);

		return listaVO;
	}
	
	public List<FormulariosEnviosVO> obtenerFormulariosEnvios2() {
		List<FormulariosEnviosVO> listaVO  = new ArrayList<FormulariosEnviosVO>();

		DetachedCriteria criteria = DetachedCriteria.forClass(FormulariosEnviosVO.class);
		ProjectionList projectionList = Projections.projectionList();
		
		criteria.createAlias("formulario", "f");
		
	    projectionList.add(Projections.property("f.id"),"idform");
//	    projectionList.add(Projections.property("f.nombre"),"nombreform");
	    criteria.setProjection(Projections.distinct(projectionList));
		criteria.add(Restrictions.isNull("fechaBaja"));


		listaVO = (List<FormulariosEnviosVO>) this.getHibernateTemplate().findByCriteria(criteria);

		return listaVO;
	}
	
	@Transactional
	public List<Object> obtenerFormulariosEnvios() {
		String sql_nat = "select distinct f.id, f.nombre from formularios_envios fe"
				+ " inner join formularios f on fe.idform = f.id";

		Query consulta = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createNativeQuery(sql_nat);
		List<Object> list = consulta.getResultList();
		return list;
	}
	
	
	public List<FormulariosEnviosVO> obtenerEnviosFechasVOPorEjemplo(FormulariosEnviosVO example, Date fechaDesde, Date fechaHasta) throws Exception {
		List<FormulariosEnviosVO> l = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(FormulariosEnviosVO.class);

		if (example.getFormulario().getId() != -1 && example.getFormulario().getId() != 0) {
			criteria.createAlias("formulario", "f");
			criteria.add(Restrictions.eq("f.id", example.getFormulario().getId()));
			criteria.add(Restrictions.isNull("f.fechaBaja"));
		}
		
		
//		if (example.getDepartamento() != null && example.getDepartamento().getId() != 0) {
//			criteria.createAlias("departamento", "c");
//			criteria.add(Restrictions.eq("c.id", example.getDepartamento().getId()));
//			criteria.add(Restrictions.isNull("c.fechaBaja"));
//		}

		
		if (fechaDesde !=null && fechaHasta !=null) {
			Criterion a = Restrictions.ge("fechaEnvio", fechaDesde);	
			Criterion b = Restrictions.le("fechaEnvio", fechaHasta);
				
			Criterion ayb = Restrictions.and(a,b);
			criteria.add(ayb);
		}
		else if (fechaDesde != null) {
			criteria.add (Restrictions.ge("fechaEnvio", fechaDesde));
			
		}
		else if (fechaHasta != null){
			criteria.add(Restrictions.le("fechaEnvio", fechaHasta));
		}
		

		criteria.add(Restrictions.isNull("fechaBaja"));
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.desc("fechaEnvio"));

		l = (List<FormulariosEnviosVO>) this.getHibernateTemplate().findByCriteria(criteria);

		return l;
	}
	
	
}
