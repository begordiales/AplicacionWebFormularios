package es.tfg.comun.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import es.tfg.comun.constantes.Constantes;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class InformesUtils {
	
	protected static final Logger log = Logger.getLogger(InformesUtils.class);

	/**
	 * Para generar el objeto compilado del informe report
	 * 
	 * @param parametros
	 * @param listado
	 * @param nombreReport
	 *            Solo el nombre sin extension
	 * @return
	 * @throws JRException
	 */
	
	
	
	public static JasperPrint generarJasperprintListado(Map parametros, List listado, String nombreReport)
			throws Exception {

		JasperPrint print = new JasperPrint();
		
		String rutaInformes = FacesUtils.getParameterConfg(Constantes.RUTA_INFORMES);
		
		String pathJasper = rutaInformes+File.separator+nombreReport+"."+Constantes.EXTENSION_JASPER;
		String pathPlantilla = rutaInformes+File.separator+nombreReport+"."+Constantes.EXTENSION_JRXML;
		
		
		File fileJasper = new File(pathJasper);
		File fileJrxml = new File(pathPlantilla);
		
		if (fileJrxml.exists()) {
		
			if (!fileJasper.exists()) {
				JasperCompileManager.compileReportToFile(pathPlantilla, pathJasper);
			}
			
			JRDataSource dataSource = null;

			if (listado == null || listado.size() == 0) {
				dataSource = new JREmptyDataSource();
			} else {
				dataSource = new JRBeanCollectionDataSource(listado);
			}
		
			return print = JasperFillManager.fillReport(pathJasper, parametros, dataSource);

		}else {
			throw new Exception("ERROR: No se ha encontrado el fichero plantilla "+pathPlantilla);
		}

	}

	public static void exportarInformeAPDF(JasperPrint print,String nombreFichero) throws Exception {

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

			response.setHeader(Constantes.RESPONSE_HEAD, "attachment; filename=\"" + nombreFichero + "\"");
			response.setContentType(Constantes.CONTENT_TYPE_PDF);

			OutputStream ostream = response.getOutputStream();

			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ostream));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			exporter.setConfiguration(configuration);

			exporter.exportReport();

			context.getApplication().getStateManager().saveView(context);
			context.responseComplete();
		} catch (IOException e1) {
			throw new Exception(e1);
		} catch (JRException e2) {
			throw new Exception(e2);
		}

	}
	
	
	public static void guardarPDF(JasperPrint print){

		try {
			OutputStream output = new FileOutputStream(new File("c:/output/JasperReport.pdf")); 
			JasperExportManager.exportReportToPdfStream(print, output);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 

	}
	
	
	
	public static void exportarInformeAPDF(JasperPrint print) throws Exception {

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

			response.setHeader(Constantes.RESPONSE_HEAD, "attachment; filename=\"" + "Hola" + ".pdf" + "\"");
			response.setContentType(Constantes.CONTENT_TYPE_PDF);

			OutputStream ostream = response.getOutputStream();

			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ostream));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			exporter.setConfiguration(configuration);

			exporter.exportReport();

			context.getApplication().getStateManager().saveView(context);
			context.responseComplete();
		} catch (IOException e1) {
			throw new Exception(e1);
		} catch (JRException e2) {
			throw new Exception(e2);
		}

	}
	
	

	public static void guardarPDFenDisco(JasperPrint print, String ruta,String nombrePDF) throws Exception{
		
		try {
			
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ruta+System.getProperty("file.separator")+nombrePDF));
			
			exporter.exportReport();
			
		} catch (JRException e) {
			e.printStackTrace();
			throw new Exception(e);
		}

	}
}
