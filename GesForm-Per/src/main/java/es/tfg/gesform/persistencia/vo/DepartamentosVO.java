package es.tfg.gesform.persistencia.vo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TIPOS database table.
 * 
 */
@Entity
@Table(name="DEPARTAMENTOS")
@NamedQuery(name="DepartamentosVO.findAll", query="SELECT d FROM DepartamentosVO d")
public class DepartamentosVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String nombre;
	private Date fechaAlta;
	private Date fechaBaja;

	

	public DepartamentosVO() {
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ALTA", nullable=false)
	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_BAJA")
	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	




}