package es.tfg.gesform.persistencia.vo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TIPOS database table.
 * 
 */
@Entity
@Table(name="VALIDACIONES")
@NamedQuery(name="ValidacionesVO.findAll", query="SELECT v FROM ValidacionesVO v")
public class ValidacionesVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String funcion;
	private String nombre;
	private String descripcion;
//	private Date fechaAlta;
//	private Date fechaBaja;
//	private TiposVO tipo;
	

	public ValidacionesVO() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=8)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Column(length=50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(length=100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(length=100)
	public String getFuncion() {
		return this.funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}
	




}