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
@Table(name="FORMULARIOS_RESPUESTAS")
@NamedQuery(name="FormulariosRespuestasVO.findAll", query="SELECT f FROM FormulariosRespuestasVO f")
public class FormulariosRespuestasVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private FormulariosEnviosVO envio;
	private FormulariosCamposVO campo;
	private String respuesta;
	private Date fechaBaja;

	

	public FormulariosRespuestasVO() {
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

	@Column(name="RESPUESTA")
	public String getRespuesta() {
		return respuesta;
	}


	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_BAJA")
	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	//bi-directional one-to-one association to FormulariosEnviosVO
		@OneToOne
		@JoinColumn(name="IDENVIO", nullable=false)
		public FormulariosEnviosVO getEnvio() {
			return this.envio;
		}

		public void setEnvio(FormulariosEnviosVO envio) {
			this.envio = envio;
		}
		
		//bi-directional one-to-one association to FormulariosCamposVO
		@OneToOne
		@JoinColumn(name="IDCAMPO", nullable=false)
		public FormulariosCamposVO getCampo() {
			return this.campo;
		}

		public void setCampo(FormulariosCamposVO campo) {
			this.campo = campo;
		}
		
	
}