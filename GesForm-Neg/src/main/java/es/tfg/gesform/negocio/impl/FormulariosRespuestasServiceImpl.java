package es.tfg.gesform.negocio.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.tfg.comun.constantes.Constantes;
import es.tfg.comun.utils.FacesUtils;
import es.tfg.comun.utils.Utilidades;
import es.tfg.gesform.negocio.FormulariosRespuestasService;
import es.tfg.gesform.negocio.FormulariosService;
import es.tfg.gesform.negocio.dto.ContenidoFormularioDTO;
import es.tfg.gesform.negocio.dto.FormulariosBloquesDTO;
import es.tfg.gesform.negocio.dto.FormulariosCamposDTO;
import es.tfg.gesform.negocio.dto.FormulariosEnviosDTO;
import es.tfg.gesform.negocio.dto.FormulariosRespuestasDTO;
import es.tfg.gesform.persistencia.dao.FormulariosRespuestasDAO;
import es.tfg.gesform.persistencia.vo.FormulariosCamposVO;
import es.tfg.gesform.persistencia.vo.FormulariosEnviosVO;
import es.tfg.gesform.persistencia.vo.FormulariosRespuestasVO;




@Service
public class FormulariosRespuestasServiceImpl implements FormulariosRespuestasService {

	@Autowired
	private FormulariosRespuestasDAO formulariosRespuestasDAO;
	
	@Autowired
	private FormulariosService formulariosService;
	
	@Autowired
	private FormulariosRespuestasService respuestasService;
	
	/****************************************************/
	
	@Transactional
	public void insertar(long idEnvio, ContenidoFormularioDTO contenido) throws Exception {
		try {
			//rellenamos un objeto de tipo RespuestasDTO
			List<FormulariosRespuestasDTO> lrespuestas = getFormulariosRespuestasDTO(idEnvio,contenido);
			if (lrespuestas!=null) {
				FormulariosRespuestasDTO respuestaDTO = new FormulariosRespuestasDTO();
				for (Iterator iterator = lrespuestas.iterator();iterator.hasNext();) {
					respuestaDTO = (FormulariosRespuestasDTO) iterator.next();
					FormulariosRespuestasVO respuestaVO = this.getFormulariosRespuestasVO(respuestaDTO);
					if (respuestaVO != null) {
//					if(!respuestaVO.getRespuesta().isEmpty() && respuestaVO.getRespuesta()!=null) {
						this.getFormulariosRespuestasDAO().insertar(respuestaVO);
					}
					
				}
			}
			
		}catch (Exception e) {
			throw e;
		}
				
	}
	
	public List<FormulariosRespuestasDTO> getFormulariosRespuestasDTO(long idEnvio, ContenidoFormularioDTO contenidoDTO) throws Exception {
		//UploadedFile f1 = (UploadedFile)object;
		List<FormulariosRespuestasDTO> lista = new ArrayList<FormulariosRespuestasDTO>();
		
		try {			
			//recorremos los bloques de contenidoFormularioDTO
			List<FormulariosBloquesDTO> lbloques= contenidoDTO.getBloques();
			
			if (lbloques !=null) {
				
				for (Iterator iterator = lbloques.iterator(); iterator.hasNext();) {
					FormulariosBloquesDTO bloque = new FormulariosBloquesDTO();
					bloque = (FormulariosBloquesDTO) iterator.next();
					//recorremos los campos del bloque
					List<FormulariosCamposDTO> lcampos = bloque.getCampos();
					FormulariosCamposDTO campo = new FormulariosCamposDTO();
					if (lcampos!=null) {
						
						for (Iterator iterator2 = lcampos.iterator();iterator2.hasNext();) {
							FormulariosRespuestasDTO respuesta = new FormulariosRespuestasDTO();
							campo = (FormulariosCamposDTO) iterator2.next();
							if (!campo.getCodigoTipoCampo().equals(Constantes.VALORES_TIPOS_CAMPO_LITERAL)) {
								respuesta.setIdEnvio(Utilidades.lNoNullEsNull(idEnvio));
								respuesta.setIdCampo(Utilidades.lNoNullEsNull(campo.getIde()));
								respuesta.setCodigoTipoCampo(Utilidades.sNoNull(campo.getCodigoTipoCampo()));
								respuesta.setIdTipocampo(Utilidades.lNoNullEsNull(campo.getIdTipocampo()));
								respuesta.setNombreCampo(Utilidades.sNoNull(campo.getEtiqueta()));
								respuesta.setCodigoValidacion(Utilidades.sNoNull(campo.getCodigoValidacion()));
								respuesta.setFuncionValidacion(Utilidades.sNoNull(campo.getFuncionValidacion()));
								respuesta.setObligatorio(campo.getObligatorio());
								String respuestaAux = "";
								if (campo.getCodigoTipoCampo().equals(Constantes.VALORES_TIPOS_CAMPO_CHECK)) {								
									for(int i=0;i<((String[])campo.getRespuesta()).length ; i++){
										respuestaAux = respuestaAux + ((String[])campo.getRespuesta())[i] + ",";
									}
									respuesta.setRespuesta(respuestaAux);
								}
								else if (campo.getCodigoTipoCampo().equals(Constantes.VALORES_TIPOS_CAMPO_FILE)) {
									if (campo.getRespuesta()!=null) {
										UploadedFile f1 = (UploadedFile)campo.getRespuesta();
										respuestaAux = idEnvio +"/"+f1.getFileName();
										//subimos el fichero
										subirFichero(idEnvio, f1);
									}
									respuesta.setRespuesta(respuestaAux);
								}
								else if (campo.getCodigoTipoCampo().equals(Constantes.VALORES_TIPOS_CAMPO_FECHA)) {
									SimpleDateFormat format =  new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
									Date dutyDay = (Date) format.parse(campo.getRespuesta().toString());
									SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
									respuestaAux = format2.format(dutyDay);
									respuesta.setRespuesta(respuestaAux);
								}
								else {
									if (campo.getRespuesta()!=null) {
										respuesta.setRespuesta(Utilidades.sNoNull(campo.getRespuesta().toString()));
									}
									
//									if (!Utilidades.esNullCeroVacio(campo.getRespuesta())) {
//										//byte[] ptext = campo.getRespuesta().toString().getBytes(ISO_8859_1); 
//										//String value = new String(ptext, UTF_8);
//										//respuesta.setRespuesta(value);
//										respuesta.setRespuesta(Utilidades.sNoNull(campo.getRespuesta()).toString());
//									}
//									else {
//										respuesta.setRespuesta(Utilidades.sNoNull(campo.getRespuesta()).toString());
//									}
								}
								lista.add(respuesta);
							}	
						}
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		
		return lista;
	}
	
	public FormulariosRespuestasVO getFormulariosRespuestasVO(FormulariosRespuestasDTO dto) throws Exception {

		FormulariosRespuestasVO vo = null;

		if (dto != null) {

			vo = new FormulariosRespuestasVO();
			vo.setEnvio(new FormulariosEnviosVO());		
			vo.getEnvio().setId(Utilidades.lNoNullEsNull(dto.getIdEnvio()));
			vo.setCampo(new FormulariosCamposVO());
			vo.getCampo().setId(Utilidades.lNoNullEsNull(dto.getIdCampo()));
			vo.setRespuesta(Utilidades.sNoNull(dto.getRespuesta()));
			
//			if (!Utilidades.esNullCeroVacio(dto.getRespuesta())) {
//				vo.setRespuesta(Utilidades.sNoNull(dto.getRespuesta()).toString());
//			}
//			else {
//				vo.setRespuesta(dto.getRespuesta().toString());
//			}
			
		}

		return vo;
	}
	
	
	public FormulariosRespuestasDTO getFormulariosEnviosDTO(FormulariosRespuestasVO vo) throws Exception {

		FormulariosRespuestasDTO dto = null;

		if (vo != null) {

			dto = new FormulariosRespuestasDTO();
			dto.setIdEnvio(Utilidades.lNoNullEsNull(vo.getCampo().getId()));
			dto.setIdCampo(Utilidades.lNoNullEsNull(vo.getCampo().getId()));
			dto.setRespuesta(Utilidades.esNullVacio(vo.getRespuesta()));
			
		}
			
		return dto;
	}

	public FormulariosRespuestasDAO getFormulariosRespuestasDAO() {
		return formulariosRespuestasDAO;
	}

	public void setFormulariosRespuestasDAO(FormulariosRespuestasDAO formulariosRespuestasDAO) {
		this.formulariosRespuestasDAO = formulariosRespuestasDAO;
	}


	public ContenidoFormularioDTO obtenerRespuestasPorEnvio(FormulariosEnviosDTO envio) throws Exception {
		ContenidoFormularioDTO contenidoDTO = new ContenidoFormularioDTO();
		try {
			contenidoDTO = formulariosService.obtenerContenidoPorIdForm(envio.getIdForm());
			//obtenemos la respuesta de cada campo
			//recorremos los bloques de contenidoFormularioDTO
			List<FormulariosBloquesDTO> lbloques= contenidoDTO.getBloques();
			
			if (lbloques !=null) {
				
				for (Iterator iterator = lbloques.iterator(); iterator.hasNext();) {
					FormulariosBloquesDTO bloque = new FormulariosBloquesDTO();
					bloque = (FormulariosBloquesDTO) iterator.next();
					//recorremos los campos del bloque
					List<FormulariosCamposDTO> lcampos = bloque.getCampos();
					FormulariosCamposDTO campo = new FormulariosCamposDTO();
					if (lcampos!=null) {
						
						for (Iterator iterator2 = lcampos.iterator();iterator2.hasNext();) {
							FormulariosRespuestasVO respuesta = new FormulariosRespuestasVO();
							campo = (FormulariosCamposDTO) iterator2.next();
							if (!campo.getCodigoTipoCampo().equals(Constantes.VALORES_TIPOS_CAMPO_LITERAL)) {
								respuesta = formulariosRespuestasDAO.obtenerRespuestaCampo(envio.getIde(), campo.getIde());
								
								if(campo.getCodigoTipoCampo().equals(Constantes.VALORES_TIPOS_CAMPO_CHECK)) {
									List<String> respuestaAux = new ArrayList<String>();
									if(respuesta.getRespuesta()!=null) {
										respuestaAux = Arrays.asList(respuesta.getRespuesta().split("\\s*,\\s*")); 
									}
									campo.setRespuesta(respuestaAux);
								}
								/*else if (campo.getCodigoTipoCampo().equals(Constantes.VALORES_TIPOS_CAMPO_FILE)) {
									 
									UploadedFile f1 = (UploadedFile)campo.getRespuesta();
									campo.setRespuesta(f1.getFileName());
								}*/
								
								else {
									campo.setRespuesta(Utilidades.sNoNull(respuesta.getRespuesta()));
								}
								
							}

						}
					}	
				}
			}
				
		}catch (Exception e) {
			throw e;
		}
		return contenidoDTO;
	}

	public List<String> validarRespuestas(ContenidoFormularioDTO respuestas) throws Exception {
		List<String> listaErrores = new ArrayList<String>();
		String error = "";

		List<FormulariosRespuestasDTO> listaRespuestas = this.getFormulariosRespuestasDTO(0, respuestas);
		//primero comprobamos los campos obligatorios
		FormulariosRespuestasDTO respuesta = new FormulariosRespuestasDTO();
		for (Iterator iterator = listaRespuestas.iterator();iterator.hasNext();) {		
			respuesta = (FormulariosRespuestasDTO) iterator.next();
			if ((respuesta.getObligatorio()==1) && (Utilidades.esNullCeroVacio(respuesta.getRespuesta()))) {				
					error = "El campo " + respuesta.getNombreCampo() + " es obligatorio";
					listaErrores.add(error);
			}
			if ((respuesta.getFuncionValidacion().equals(Constantes.VALIDACION_NIF)) && (!Utilidades.esNullCeroVacio(respuesta.getRespuesta()))) {
				if (!Utilidades.comprobarNif(respuesta.getRespuesta().toString())) {
					error = "El campo " + respuesta.getNombreCampo() + " no es correcto";
					listaErrores.add(error);
				}
			}

		}
	

		return listaErrores;

	}
	
	 public void subirFichero(long idEnvio, UploadedFile fichero) {
		  try {
		    //Creamos el directorio
			String rutaAbs = FacesUtils.getParameterConfg(Constantes.RUTA_FICHEROS);
			String ruta = "C:/DOCGESFORM/"+idEnvio;
		    String savedFileName = ruta + "/" + fichero.getFileName();
		    File fileToSave = new File(savedFileName);
		    fileToSave.getParentFile().mkdirs();
		    fileToSave.delete();
		    //generamos path para copiar el fichero
		    Path folder = Paths.get(savedFileName);
		    Path fileToSavePath = Files.createFile(folder);
		    //Copiamos el fichero en el servidor
		    InputStream input = fichero.getInputstream();
		    Files.copy(input, fileToSavePath,StandardCopyOption.REPLACE_EXISTING);
		  } catch (Exception e) {
			  e.printStackTrace();
		  } finally {

		  }
		}

	public String abrirFichero(String nombreFichero, String rutaFichero) {

			try {
				// Creamos un contexto para el fichero //
				FacesContext context = FacesContext.getCurrentInstance();
				HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

				response.reset();

				// Eliminamos espacios en blanco, saltos de carro, etc..
				nombreFichero = nombreFichero.trim();// + Constantes.EXTENSION_PDF;

				// Obtenemos el File del fichero origen y su inputStream
				File fi = new File(rutaFichero);
				InputStream is = new FileInputStream(fi);

				response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreFichero + "\"");
				// response.setContentType("application/force-download");

				// Obtenemos un OutputStream para el fichero destino //
				OutputStream archivoDestino = response.getOutputStream();
				byte[] buffer = new byte[512 * 1024];

				// Lees el archivo hasta que se acabe...
				int nbLectura;
				while ((nbLectura = is.read(buffer)) != -1) {
					archivoDestino.write(buffer, 0, nbLectura);
				}

				// Cerramos el archivo destino //
				archivoDestino.close();
				is.close();

				// Abrimos el archivo PDF //
				context.getApplication().getStateManager().saveView(context);
				context.responseComplete();
				return "";
			} catch (Exception e) {
				// log.error("Problema abriendo el pdf");
				e.printStackTrace();
				String causa = e.toString();

				if (causa != null && causa.contains("FileNotFoundException")) {
					FacesUtils.setSessionParameter("codigoError", "P011.1");
					return "errorFile";
				} else {
					return "error";
				}
			}
		}
	}
