package es.tfg.comun.utils;

import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;

import es.tfg.comun.constantes.Mensajes;



public class PersistenciaException extends RuntimeException {

    public static final Integer ERROR_KEY_DUPLICADA = 1;
    public static final Integer ERROR_CAMPOS_NULL = 2;
    public static final Integer ERROR_ID_NULL = 3;
    public static final Integer ERROR_CAMPO_LARGO = 4;
    public static final Integer ERROR_UPDATE = 5;
    public static final Integer ERROR_MAX_REGISTROS = 6;
    public static final Integer ERROR_BORRAR_REG_RELACIONADO = 7;
    private Integer tipoError;
    private String mensaje;
    private Throwable excepcion;
    private String mensajeError;

    public PersistenciaException(DataAccessException excepcion) {
        super(excepcion);
        this.excepcion = excepcion;
        this.mensaje = excepcion.getMostSpecificCause().getMessage();
        //transformarMensaje(this.mensaje);
    }
    
    public PersistenciaException(HibernateException excepcion) {
        super(excepcion);
        this.excepcion = excepcion;
        this.mensaje = excepcion.getCause().getMessage();
    }
    
  
    public PersistenciaException() {
       setMensajeError(Mensajes.MENSAJE_GENERICO_1);
    }
    
    public PersistenciaException(String mensaje, Integer tipoError) {
        super(mensaje);
        this.tipoError = tipoError;
        this.mensaje = mensaje;

    }

    public PersistenciaException(Integer tipoError) {
        this.tipoError = tipoError;

    }
    
    
    public PersistenciaException(String mensaje) {
        super(mensaje);        
        this.mensaje = mensaje;
        this.setMensajeError(mensaje);
        //transformarMensaje(this.mensaje);
    }

    public PersistenciaException(Exception e) {
		super(e);
	}

	public Integer getTipoError() {
        return tipoError;
    }

    public void setTipoError(Integer tipoError) {
        this.tipoError = tipoError;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Throwable getExcepcion() {
        return excepcion;
    }

    public void setExcepcion(Throwable excepcion) {
        this.excepcion = excepcion;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
}
