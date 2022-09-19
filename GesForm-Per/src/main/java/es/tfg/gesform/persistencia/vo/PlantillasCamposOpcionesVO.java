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
@Table(name="PLANTILLAS_CAMPOS_OPCIONES")
@NamedQuery(name="PlantillasCamposOpcionesVO.findAll", query="SELECT p FROM PlantillasCamposOpcionesVO p")
public class PlantillasCamposOpcionesVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private int valor;
	private String descripcion;
	private int orden;
	private PlantillasCamposVO campo;
	private Date fechaAlta;
	private Date fechaBaja;

	

	public PlantillasCamposOpcionesVO() {
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

	
	@Column(name="VALOR", precision=8)
	public int getValor() {
		return this.valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
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
	
	//bi-directional one-to-one association to PlantillasCamposVO
		@OneToOne
		@JoinColumn(name="IDPLANTILLACAMPO", nullable=false)
		public PlantillasCamposVO getCampo() {
			return this.campo;
		}

		public void setCampo(PlantillasCamposVO campo) {
			this.campo = campo;
		}
		
	
}