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
@Table(name="VALORES_TIPOS")
@NamedQuery(name="ValoresTiposVO.findAll", query="SELECT v FROM ValoresTiposVO v")
public class ValoresTiposVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String codigo;
	private String nombre;
	private String descripcion;
	private Date fechaAlta;
	private Date fechaBaja;
	private TiposVO tipo;
	

	public ValoresTiposVO() {
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


	@Column(name="CODIGO",  unique=true, nullable=false, length=5)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
		@JoinColumn(name="IDTIPO")
		public TiposVO getTipo() {
			return this.tipo;
		}

		public void setTipo(TiposVO tipo) {
			this.tipo = tipo;
		}









}