package es.tfg.gesform.persistencia.dao;

import java.util.List;

import es.tfg.gesform.persistencia.vo.DepartamentosVO;

/**
 * DAO para acceder a la tabla Departamentos.
 */
public interface DepartamentosDAO {


	/**
	 * Devuelve un Departamento a partir de su identificador (columna "id").
	 * 
	 * @param id
	 *            identificador del Departamento a devolver.
	 * @return el Departamento en caso de que se encuentre, null en caso contrario.
	 */
	DepartamentosVO selectByPrimaryKey(Long id);

	/**
	 * Inserta un Departamento.
	 * 
	 * @param Departamento
	 *            a insertar.
	 */
	void insert(DepartamentosVO obj);

	/**
	 * Devuelve una lista con todos los Departamentos.
	 * 
	 * @return La lista de Departamentos. En el caso de que no se encuentre ninguno
	 *         devuelve una lista vacia.
	 */
	List<DepartamentosVO> selectAll();

	/**
	 * Devuelve una lista de Departamentos cuyos datos coinciden con los criterios
	 * de busqueda.
	 * 
	 * @param Departamento
	 *            criterios de busqueda.
	 * @return la lista de Departamentos coincidentes.
	 */
	List<DepartamentosVO> findByExample(DepartamentosVO obj);

	/**
	 * Actualiza un Departamento.
	 * 
	 * @param Departamento
	 *            Departamento con los datos a actulizar.
	 */
	void update(DepartamentosVO obj);

	/**
	 * Borra un Departamento.
	 * 
	 * @param Departamento
	 *            Departamento a borrar.
	 */
	void delete(DepartamentosVO obj);
	
	List<DepartamentosVO> obtenerTodosActivos();
	
	public DepartamentosVO obtenerNombrePorId(Long idDepartamentos);

	public List<DepartamentosVO> obtenerDepartamentosPorEjemplo(DepartamentosVO example);	
}

