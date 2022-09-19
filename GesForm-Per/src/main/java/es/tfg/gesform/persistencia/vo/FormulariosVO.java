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
@Table(name="FORMULARIOS")
@NamedQuery(name="FormulariosVO.findAll", query="SELECT f FROM FormulariosVO f")
public class FormulariosVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String nombre;
	private String descripcion;
	private String url;
	private Date fechaInicio;
	private Date fechaFin;
	private DepartamentosVO departamento;
	private ValoresTiposVO tipoform;
	private Date fechaAlta;
	private Date fechaBaja;

	

	public FormulariosVO() {
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
	
	@Column(length=250)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(length=500)
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_INICIO", nullable=false)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_FIN", nullable=false)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
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
	
	//bi-directional many-to-one association to DepartamentosVO
			@ManyToOne
			@JoinColumn(name="IDDEPARTAMENTO" , nullable=true)
			public DepartamentosVO getDepartamento() {
				return this.departamento;
			}

			public void setDepartamento(DepartamentosVO departamento) {
				this.departamento = departamento;
			}
	//bi-directional one-to-one association to ValoresTipoVO
			@ManyToOne
			@JoinColumn(name="IDTIPOFORM", nullable=false)
			public ValoresTiposVO getTipoform() {
				return tipoform;
			}


			public void setTipoform(ValoresTiposVO tipoform) {
				this.tipoform = tipoform;
			}	
			



}