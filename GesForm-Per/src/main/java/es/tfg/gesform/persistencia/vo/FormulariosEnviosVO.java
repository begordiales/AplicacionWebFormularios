package es.tfg.gesform.persistencia.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TIPOS database table.
 * 
 */
@Entity
@Table(name="FORMULARIOS_ENVIOS")
@NamedQuery(name="FormulariosEnviosVO.findAll", query="SELECT f FROM FormulariosEnviosVO f")
public class FormulariosEnviosVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private FormulariosVO formulario;
	private Timestamp fechaEnvio;
	private Date fechaBaja;

	

	public FormulariosEnviosVO() {
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

	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ENVIO", nullable=false)
	public Timestamp getFechaEnvio() {
		return this.fechaEnvio;
	}

	public void setFechaEnvio(Timestamp fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_BAJA")
	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	//bi-directional one-to-one association to SolicitudVO
		@OneToOne
		@JoinColumn(name="IDFORM", nullable=false)
		public FormulariosVO getFormulario() {
			return this.formulario;
		}

		public void setFormulario(FormulariosVO formulario) {
			this.formulario = formulario;
		}
		
	
}