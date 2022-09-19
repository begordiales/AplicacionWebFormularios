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
@Table(name="PLANTILLAS_CAMPOS")
@NamedQuery(name="PlantillasCamposVO.findAll", query="SELECT p FROM PlantillasCamposVO p")
public class PlantillasCamposVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String nombre;
	private String etiqueta;
	private int obligatorio;
	private int tamano;
	private int orden;
	private PlantillasVO plantilla;
	private ValoresTiposVO tipocampo;
	private ValidacionesVO validacion;
	private Date fechaAlta;
	private Date fechaBaja;
	private String valor;
	private Integer tamanofichero;
	private String tiposfichero;
	private Integer numficheros;

	

	public PlantillasCamposVO() {
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
	public String getEtiqueta() {
		return this.etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	@Column(nullable=false, length=1)
	public int getObligatorio() {
		return this.obligatorio;
	}

	public void setObligatorio(int obligatorio) {
		this.obligatorio =obligatorio;
	}
	
		
	@Column(name="TAMANO", precision=8)
	public int getTamano() {
		return this.tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
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
	
	//bi-directional one-to-one association to PlantillasVO
		@OneToOne
		@JoinColumn(name="IDPLANTILLA", nullable=false)
		public PlantillasVO getPlantilla() {
			return this.plantilla;
		}

		public void setPlantilla(PlantillasVO plantilla) {
			this.plantilla = plantilla;
		}
		
	//bi-directional one-to-one association to ValoresTiposVO
		@OneToOne
		@JoinColumn(name="IDTIPOCAMPO", nullable=false)
		public ValoresTiposVO getTipoCampo() {
			return this.tipocampo;
		}

		public void setTipoCampo(ValoresTiposVO tipocampo) {
			this.tipocampo = tipocampo;
		}	
		
	//bi-directional one-to-one association to ValidacionesVO
		@OneToOne
		@JoinColumn(name="IDVALIDACION", nullable=true)
		public ValidacionesVO getValidacion() {
			return this.validacion;
		}

		public void setValidacion(ValidacionesVO validacion) {
			this.validacion = validacion;
		}

		@Column(name="VALOR")
		public String getValor() {
			return valor;
		}


		public void setValor(String valor) {
			this.valor = valor;
		}




		@Column(name="TAMANOFICHERO", precision=7)
		public Integer getTamanofichero() {
			return tamanofichero;
		}


		public void setTamanofichero(Integer tamanofichero) {
			this.tamanofichero = tamanofichero;
		}

		
		@Column(name="TIPOSFICHERO")
		public String getTiposfichero() {
			return tiposfichero;
		}


		public void setTiposfichero(String tiposfichero) {
			this.tiposfichero = tiposfichero;
		}

		@Column(name="NUMFICHEROS", precision=3)
		public Integer getNumficheros() {
			return numficheros;
		}


		public void setNumficheros(Integer numficheros) {
			this.numficheros = numficheros;
		}
		
		
		
	
}