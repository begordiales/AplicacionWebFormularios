package es.tfg.comun.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Utilidades {

	public static String NUMEROS = "0123456789";

	public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

	public static String SYMBOLS= "";

	/**
	 * @author
	 * @param Cadena
	 *            - Object
	 * @return String Devuelve un objeto convertido a string. si el objeto es nulo,
	 *         devuelve una cadena en blanco.
	 */
	public static String sNoNull(Object stCadena) {
		if (stCadena == null || stCadena.equals("null") || stCadena.equals("undefined"))
			return ("");
		else
			return (stCadena.toString());
	}


	public static String quitarmiles(String cadena) {
		Integer pun = cadena.lastIndexOf((".").charAt(0));
		Integer com = cadena.lastIndexOf((",").charAt(0));

		if (pun > com)
			cadena = cadena.replace(",", "");
		else
			cadena = cadena.replace(".", "");

		return cadena;
	}

	/**
	 * @author
	 * @param Cadena
	 *            - String
	 * @return String Añade tantos ceros a la izquierda como sean necesarios hasta
	 *         alcanzar el tamaño (Tamano)
	 */
	public static String cerosAIzquierda(String stCadena, Integer iTamano) {
		while (stCadena.length() < iTamano)
			stCadena = "0" + stCadena;
		return stCadena;
	}



	/**
	 * @author
	 * @param Cadena
	 *            - Object
	 * @return Double Devuelve un objeto convertido a double. si el objeto es nulo,
	 *         devuelve 0.00.
	 */
	public static Double ddNoNull(Object stCadena) {
		if (stCadena == null || stCadena.equals("null") || stCadena.equals("undefined") || stCadena.equals("[]")
				|| stCadena.equals(""))
			return (0.00);
		else
			return (Double.valueOf(stCadena.toString()).doubleValue());
	}

	/**
	 * Devuelve un objeto convertido a BigDecimal. - Si el objeto es nulo, devuelve
	 * 0.00. - Si la cadena no se puede castear a BigDecimal devuelve null
	 * @param
	 * @return BigDecimal
	 */
	public static BigDecimal bdNoNull(Object objNumero) {
		BigDecimal bdResultado = null;

		try {
			if (objNumero == null || objNumero.equals("null") || objNumero.equals("undefined") || objNumero.equals("[]")
					|| objNumero.equals(""))
				bdResultado = new BigDecimal("0.00");
			else
				bdResultado = new BigDecimal("" + objNumero);
		} catch (Exception e) {
		}
		return bdResultado;
	}

	/**
	 * 
	 * @param Cadena
	 * @return Long si es numero sino null
	 */
	public static Long lNoNullEsNull(Object Cadena) {
		if (Cadena == null || Cadena.equals("null") || Cadena.equals("undefined"))
			return null;
		else {
			try {
				Long resul = Long.parseLong(Cadena.toString());
				return resul;
			} catch (Exception e) {
				return null;
			}
		}
	}

	/**
	 * @author
	 * @param Cadena
	 * @return Integer si es numero sino Integer(0)
	 */
	public static Integer iNoNullEsCero(Object stCadena) {
		if (stCadena == null || stCadena.equals("null") || stCadena.equals("undefined"))
			return new Integer(0);
		else {
			try {
				Integer resul = Integer.parseInt(stCadena.toString());
				return resul;
			} catch (Exception e) {
				return new Integer(0);
			}
		}
	}




	/**
	 * Indica si el valor es null, cero o cadena vacia
	 * 
	 * @author
	 * @param objeto
	 *            - Object
	 * @return true o false
	 */
	public static boolean esNullCeroVacio(Object objeto) {
		if (objeto == null)
			return true;
		if (objeto.toString().equals("0"))
			return true;
		if (objeto.toString().equals("0.0"))
			return true;
		if (objeto.toString().equals("0,00"))
			return true;
		if (objeto.toString().equals("0.00"))
			return true;
		if (objeto.toString().equals(""))
			return true;
		if (objeto.toString().equals("null"))
			return true;
		if (objeto.toString().equals("0,000"))
			return true;
		if (objeto.toString().equals("0.000"))
			return true;

		return false;
	}



	/**
	 * Indica si el valor es null, cero o cadena vacia
	 * 
	 * @author
	 * @param objeto
	 *            - Object
	 * @return true o false
	 */
	public static boolean esNullVacio(Object objeto) {
		if (objeto == null)
			return true;
		if (objeto.toString().trim().equals(""))
			return true;
		if (objeto.toString().trim().equals("null"))
			return true;
		return false;
	}

	/**
	 * Comprueba si una cadena es numÃ©rica.
	 * 
	 * @author
	 * @param cadena
	 *            - Object
	 * @return boolean true - es numerica false - no es numerica
	 */
	public static boolean isNumber(Object cadena) {
		try {
			if (cadena != null) {
				Integer.parseInt(cadena.toString());
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	
	/**
	 * Hace split de una cadena separada por el caracter separador inserta el
	 * resultado en un array
	 * 
	 * @param cadena
	 * @param separador
	 *            el caracter que hace de separador
	 * @return array de Strings
	 */


	public static Double stringToDouble(String cadena) {
		return Double.valueOf(cadena).doubleValue();
	}

	/**
	 * Indica si la lista es null o no tiene elementos
	 * 
	 * @param lista
	 *            - List
	 * @return true o false
	 */
	public static boolean esListaNullVacia(List lista) {
		return ((lista == null) || (lista.size() < 1));
	}

	/**
	 * Indica si la lista tiene elementos.
	 * 
	 * @param lista
	 *            - List
	 * @return true o false
	 * @author
	 */
	public static boolean esListaConElementos(List<?> lista) {
		boolean tieneElem = false;

		if (!esListaNullVacia(lista) && lista.size() > 0) {
			tieneElem = true;
		}
		return tieneElem;
	}


	/**
	 * Pasa una cadena que contiene una fecha en formato mm/dd/yyyy a un Date
	 * @param cadena
	 * @return Date
	 * @throws ParseException
	 */
    public static Date objectToFechaEsp(Object cadena) throws ParseException{
    	if (cadena==null || cadena.equals("")) return null; 
    	String dateFormat=cadena.toString();
	 		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
	 		Date date=sdf.parse(dateFormat);
	 		return date;
    }
    
    


	public static String formateoNIF(String nif) {
		if (esNullCeroVacio(nif))
			return nif;
		else
			return cerosAIzquierda(nif.toUpperCase(), 9);
	}

	
	/**
	 * Obtiene el path excluyendo el nombre del fichero a partir de la ruta total
	 * del mismo
	 * 
	 * @param stRutaFichero
	 * @return
	 */
	public static String obtenerPathFichero(String stRutaFichero) {
		String stResultado = null;
		String stFileSeparator = null;

		if (System.getProperty("file.separator").equals("\\"))
			stFileSeparator = "\\\\";
		else
			stFileSeparator = "/";

		stResultado = stRutaFichero.substring(0, stRutaFichero.lastIndexOf(stFileSeparator));
		stResultado += stFileSeparator;
		return stResultado;
	}

	/**
	 * Obtiene el nombre de un fichero a partir de la ruta total del mismo
	 * 
	 * @param stRutaFichero
	 * @return
	 */
	public static String obtenerNombreFichero(String stRutaFichero) {
		String stResultado = null;
		String stFileSeparator = null;

		if (System.getProperty("file.separator").equals("\\")) {
			stFileSeparator = "\\";
			stResultado = stRutaFichero.substring(stRutaFichero.lastIndexOf(stFileSeparator) + 1,
					stRutaFichero.length());
		} else {
			stFileSeparator = "/";
			stResultado = stRutaFichero.substring(stRutaFichero.lastIndexOf(stFileSeparator) + 1,
					stRutaFichero.length());
		}
		return stResultado;
	}

	public static String pasarAMayusculas(String stCadena) {
		String result = "";
		if (!esNullVacio(stCadena))
			result = stCadena.toUpperCase();
		return result;
	}

	public static String pasarAMinusculas(String stCadena) {
		String result = "";
		if (!esNullCeroVacio(stCadena))
			result = stCadena.toLowerCase();
		return result;
	}

	

	public static boolean validarEmail(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = emailPattern.matcher(email);
		return matcher.matches();
	}



	public static boolean validarIBAN(String iban) {

		boolean esValido = false;
		int i = 2;
		int caracterASCII = 0; 
		int resto = 0;
		int dc = 0;
		String cadenaDc = "";
		BigInteger cuentaNumero = new BigInteger("0"); 
		BigInteger modo = new BigInteger("97");

		if(iban.length() == 24 && iban.substring(0,1).toUpperCase().equals("E") 
				&& iban.substring(1,2).toUpperCase().equals("S")) {

			do {
				caracterASCII = iban.codePointAt(i);
				esValido = (caracterASCII > 47 && caracterASCII < 58);
				i++;
			}
			while(i < iban.length() && esValido); 


			if(esValido) {
				cuentaNumero = new BigInteger(iban.substring(4,24) + "142800");
				resto = cuentaNumero.mod(modo).intValue();
				dc = 98 - resto;
				cadenaDc = String.valueOf(dc);
			}	

			if(dc < 10) {
				cadenaDc = "0" + cadenaDc;
			} 

			// Comparamos los caracteres 2 y 3 de la cuenta (dígito de control IBAN) con cadenaDc
			if(iban.substring(2,4).equals(cadenaDc)) {
				esValido = true;
			} else {
				esValido = false;
			}
		}

		return esValido;
	}




	/**
	 * Valida que la cadena que entra por parametro tenga el formato correcto para
	 * un porcentaje
	 * 
	 * @param valorCasilla
	 * @return
	 */
	public static Boolean validarPorcentaje(String valorCasilla) {
		if (Utilidades.esNullVacio(valorCasilla))
			return false;
		Double porcentaje = Utilidades.ddNoNull(Utilidades.stringToDouble(valorCasilla));
		if (porcentaje <= 100 && porcentaje > 0)
			return true;
		return false;
	}

	

	public static BigDecimal BigDecimalComa(String cadena) {
		try {
			if (cadena == null || cadena.equals(""))
				return new BigDecimal("0");
			else {
				cadena = quitarmiles(cadena);
				return new BigDecimal(cadena);
			}
		} catch (Exception e) {
			cadena = ((cadena.replace(",", "#")).replace(".", ",")).replace("#", ".");
			return new BigDecimal(cadena);
		}
	}

	/**
	 * Comprueba si la cadena corresponde con un entero
	 * 
	 * @param cadena
	 * @return
	 */
	public static boolean esEntero(String cadena) {
		try {
			if (cadena != null) {
				cadena = quitarmiles(cadena);
				Integer.parseInt(cadena.toString());
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}


	
	public static Boolean validarURL(String url){
		Boolean valida = Boolean.FALSE;

		/*validación de url*/
		try {
			new URL(url).toURI();
			valida = Boolean.TRUE;
		}
		catch (URISyntaxException exception) {
			valida = Boolean.FALSE;
		}
		catch (MalformedURLException exception) {
			valida = Boolean.FALSE;
		}

		return valida;
	}



		public static boolean guardarImagen(String carpeta, String nombreFichero, byte[] contenido) throws IOException {

		if (carpeta != null && nombreFichero != null && contenido != null) {

			File fdir = new File(carpeta);

			if (!fdir.exists())
				fdir.mkdirs();

			File targetFile = new File(carpeta+nombreFichero);

			OutputStream outStream = new FileOutputStream(targetFile);
			outStream.write(contenido);

			outStream.flush();

			outStream.close();
		}else {
			return false;
		}
		return true;
	}

	public static boolean copiarFichero(File forigen, File fdestino) throws IOException {

		if (forigen.exists()) {

			Path p = Paths.get(fdestino.getAbsolutePath());
			Path folder = p.getParent();

			if (!Files.exists(folder))
				Files.createDirectories(folder);

			Files.copy(Paths.get(forigen.getAbsolutePath()), Paths.get(fdestino.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
			return true;
		}

		return false; 
	}



	/**
	 * Indica si el valor es null, cero o cadena vacia

	 * @param objeto
	 *            - Object
	 * @return true o false
	 */
	public static boolean esNull(Object objeto) {
		if (objeto == null)
			return true;
		return false;
	}



	/**
	 * 
	 * @param Cadena
	 * @return Integer si es numero sino null
	 */
	public static Integer iNoNullEsNull (Object Cadena) {
		if (Cadena == null || Cadena.equals("null") || Cadena.equals("undefined"))
		{
			return null;
		}
		else
		{
			try {
				Integer resul = Integer.parseInt(Cadena.toString());
				return resul;
			} catch (Exception e) {
				return null;
			}	      
		}
	}


	public static String escaparCaracteresBD (String cadena){
		if (cadena.contains("'")){
			cadena=cadena.replaceAll("'", "''");
		}  
		cadena="'"+cadena+"'";

		if (cadena.contains("\\")){ 
			cadena=cadena.replaceAll("\\\\", "\\\\134");
			cadena="E"+cadena;
		}

		return cadena;
	}



	public static Boolean comprobarNif(String nif) {
		boolean correcto = false;

		if (!esNullVacio(nif)) {
			if (nif.toUpperCase().startsWith("X") || nif.toUpperCase().startsWith("Y")
					|| nif.toUpperCase().startsWith("Z") || nif.toUpperCase().startsWith("M")
					|| nif.toUpperCase().startsWith("K") || nif.toUpperCase().startsWith("L"))
				nif = nif.substring(1);

			Pattern nifPattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
			Matcher m = nifPattern.matcher(nif);
			if (m.matches()) {
				String letra = m.group(2);
				// Extraer letra del NIF
				String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
				int dni = Integer.parseInt(m.group(1));
				dni = dni % 23;
				String reference = letras.substring(dni, dni + 1);

				if (reference.equalsIgnoreCase(letra)) {
					correcto = true;
				} else {
					correcto = false;
				}
			} else {
				correcto = false;
			}
		}
		return correcto;
	}
	

	
}
