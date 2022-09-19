package es.tfg.gesform.persistencia.dao;

import java.util.Date;
import java.util.List;

import es.tfg.gesform.persistencia.vo.FormulariosVO;

/**
 * DAO para acceder a la tabla Formularios.
 */
public interface FormulariosDAO {



	public FormulariosVO selectByPrimaryKey(long id);

	public void insertar(FormulariosVO obj)  throws Exception;
	
	public void actualizar(FormulariosVO obj);

	public List<FormulariosVO> obtenerTodosFormularios(Integer limit);
	
	public int obtenerOrdenBloque(long idForm)throws Exception; //obtiene el máximo orden de bloque en un formulario
	
	public List<FormulariosVO> obtenerFormulariosVOPorEjemplo (FormulariosVO example) throws Exception;
	public List<FormulariosVO> obtenerFormulariosFechasVOPorEjemplo (FormulariosVO example, Date fechaDesde, Date fechaHasta) throws Exception;
	
//	Long insertarBloquePlantilla(Long idForm, PlantillasVO plantillavo) throws Exception;

//	List<ClientesVO> findByExample(ClientesVO cliente);
//
//	void update(ClientesVO cliente);
//
//	void delete(ClientesVO cliente);

}

