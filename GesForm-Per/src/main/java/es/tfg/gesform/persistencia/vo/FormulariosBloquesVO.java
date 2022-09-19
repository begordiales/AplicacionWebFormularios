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
@Table(name="FORMULARIOS_BLOQUES")
@NamedQuery(name="FormulariosBloquesVO.findAll", query="SELECT f FROM FormulariosBloquesVO f")
public class FormulariosBloquesVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String nombre;
	private String descripcion;
	private int orden;
	private int columnas;
	private FormulariosVO formulario;
	private PlantillasVO plantilla;
	private List<FormulariosCamposVO> campos;
	private Date fechaAlta;
	private Date fechaBaja;

	

	public FormulariosBloquesVO() {
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
	
	@Column(name="ORDEN", precision=8)
	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}
	
	@Column(name="NUMCOLUMNAS", precision=8)
	public int getColumnas() {
		return this.columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
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
	
	//bi-directional many-to-one association to TipoVO
			@ManyToOne
			@JoinColumn(name="IDFORM", nullable=false)
			public FormulariosVO getFormulario() {
				return this.formulario;
			}

			public void setFormulario(FormulariosVO formulario) {
				this.formulario = formulario;
			}
	//bi-directional one-to-one association to SolicitudVO
			@OneToOne
			@JoinColumn(name="IDPLANTILLA", nullable=true)
			public PlantillasVO getPlantilla() {
				return this.plantilla;
			}

			public void setPlantilla(PlantillasVO plantilla) {
				this.plantilla = plantilla;
			}


			
			//bi-directional many-to-one association to FormulariosCamposVO
			@OneToMany(mappedBy = "bloque")
			public List<FormulariosCamposVO> getCampos() {
				return this.campos;
			}

			public void setCampos(List<FormulariosCamposVO> campos) {
				this.campos = campos;
			}


			
			
			
			
	
			



}