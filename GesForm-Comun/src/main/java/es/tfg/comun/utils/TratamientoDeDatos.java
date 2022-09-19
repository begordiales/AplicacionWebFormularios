package es.tfg.comun.utils;

import java.util.List;
import java.util.regex.Pattern;
import es.tfg.comun.constantes.Constantes;




/**
 * Clase estática de utilidades para el tratamiendo común de datos.
 */
public class TratamientoDeDatos {

	/**
	 * @param Cadena - Object
	 * @return String 
	 * Devuelve un objeto convertido a string. si el objeto es nulo, devuelve una cadena en blanco.
	 */
	public static String sNoNull(Object Cadena) {
	    if (Cadena == null || Cadena.equals("null") || Cadena.equals("undefined"))
	    {
	      return("");
	    }
	    else
	    {
	      return (Cadena.toString());
	    }
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
	
	/**
	 * 
	 * @param Cadena
	 * @return Integer si es numero sino Integer(0)
	 */
	public static Integer iNoNullEsCero (Object Cadena) {
	    if (Cadena == null || Cadena.equals("null") || Cadena.equals("undefined"))
	    {
	      return new Integer(0);
	    }
	    else
	    {
	    	try {
	    		Integer resul = Integer.parseInt(Cadena.toString());
	    		return resul;
	    	} catch (Exception e) {
				return new Integer(0);
			}	      
	    }
	}
	
	/**
	 * Indica si el valor es null, cero o cadena vacía
	 * @param objeto - Object
	 * @return true o false
	 */
	public static boolean esNullCeroVacio (Object objeto) {
	
		if (objeto==null) return true;
		if (objeto.toString().equals("0")) return true;
		if (objeto.toString().equals("0.0")) return true;
		if (objeto.toString().equals("")) return true;
		if (objeto.toString().equals("null")) return true;
		
		return false;
	}
	
		
	/**
	 * Comprueba si una cadena es numérica.
	 * @param cadena - Object
	 * @return boolean
	 * 		true  - es numérica
	 * 		false - no es numérica 
	 */
	public static boolean isNumber (Object cadena) {
		
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
	 * Comprueba si una cadena es Long.
	 * @param cadena - Object
	 * @return boolean 
	 * 		true  - es Long
	 * 		false - no es cadena.toString() 
	 */
	public static boolean isLong (Object cadena) {
		
		try {
			if (cadena != null) {		
				Long.parseLong(cadena.toString());
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		
		return false;
	}
	
	/**
	 * Comprueba si una cadena es Double.
	 * @param cadena - Object
	 * @return boolean 
	 * 		true  - es Long
	 * 		false - no es cadena.toString() 
	 */
	public static boolean isDouble (Object cadena) {
		
		try {
			if (cadena != null) {		
				Double.parseDouble(cadena.toString());
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		
		return false;
	}
	
	

	
	/**
	 * 
	 * @param nombre
	 * @return Devuelve un número con este formato '123.455,00' a '123455.00' (patrón que
	 * permite la BB.DD)
	 */
	public static String getNumeroEnteroDecimal(String nombre){
		String cadena=nombre.replace(".","");
		 cadena=cadena.replace(",", ".");
	return cadena;	
	}
	

   /**
     * Indica si el valor es null o cadena vacÃ­a
     * @param objeto
     *            - Object
     * @return true o false
     */

    public static boolean esNullVacio(Object objeto) {
        if (objeto == null)
            return true;
        if (objeto.toString().equals(""))
            return true;
        if (objeto.toString().equals("null"))
            return true;
        return false;

    }

   
    
    /**
     * 
     * @param cadena
     * @return Boolean En caso de nulo o excepcion retorna FALSE
     */
    public static Boolean booleanNoNull(Object cadena) {
        Boolean resul = false;
        if (cadena == null || cadena.equals("null")
                || cadena.equals("undefined")) {
            resul = false;
        } else {
            try {
                resul = Boolean.parseBoolean(cadena.toString());
            } catch (Exception e) {
                resul = false;
            }
        }
        return resul;
    }

    
    /**
     * Indica si la lista es null o no tiene elementos
     * @param lista
     *            - List
     * @return true o false
     */
    public static boolean esListaNullVacia(List<?> lista) {
        return ((lista == null) || (lista.size() < 1));
    }
	
    
   
    
    private static final Pattern cifPattern = Pattern.compile("[[A-H][J-N][P-S]UVW][0-9]{7}[0-9A-J]");

    private static final String CONTROL_SOLO_NUMEROS = "ABEH";

    private static final String CONTROL_SOLO_LETRAS = "KPQS";

    private static final String CONTROL_NUMERO_A_LETRA = "JABCDEFGHI";
    
    
    public static boolean validateCif(String cif) {
        Boolean valido = true;
        try {
            int valor = -1;
            int longitud = cif.length();
            if (!TratamientoDeDatos.esNullCeroVacio(longitud) && longitud > 0) {
                if (cif.startsWith("X")) {
                    valor = TratamientoDeDatos.iNoNullEsNull(cif.substring(1,
                            cif.length() - 1));
                } else if (cif.startsWith("Y")) {
                    valor = 10000000 + TratamientoDeDatos.iNoNullEsNull(cif
                            .substring(1, cif.length() - 1));
                } else if (cif.startsWith("Z")) {
                    valor = 20000000 + TratamientoDeDatos.iNoNullEsNull(cif
                            .substring(1, cif.length() - 1));
                } else if (TratamientoDeDatos.isNumber(cif.charAt(0))
                        && longitud > 1) {
                    valor = TratamientoDeDatos.iNoNullEsNull(cif.substring(0,
                            cif.length() - 1));
                }
                if (valor != -1) {

                    if (cif.endsWith(""
                            + Constantes.NIF_STRING_ASOCIATION
                                    .charAt(valor % 23)) == true) {
                        valido = true;
                    } else
                        valido = false;
                } else {

                    if (!cifPattern.matcher(cif).matches()) {
                        // No cumple el patr�n
                        return false;
                    }

                    int parA = 0;
                    for (int i = 2; i < 8; i += 2) {
                        final int digito = Character.digit(cif.charAt(i), 10);
                        if (digito < 0) {
                            return false;
                        }
                        parA += digito;
                    }

                    int nonB = 0;
                    for (int i = 1; i < 9; i += 2) {
                        final int digito = Character.digit(cif.charAt(i), 10);
                        if (digito < 0) {
                            return false;
                        }
                        int nn = 2 * digito;
                        if (nn > 9) {
                            nn = 1 + (nn - 10);
                        }
                        nonB += nn;
                    }

                    final int parcialC = parA + nonB;
                    final int digitoE = parcialC % 10;
                    final int digitoD = (digitoE > 0) ? (10 - digitoE) : 0;
                    final char letraIni = cif.charAt(0);
                    final char caracterFin = cif.charAt(8);

                    final boolean esControlValido =
                    // �el caracter de control es v�lido como letra?
                    (CONTROL_SOLO_NUMEROS.indexOf(letraIni) < 0 && CONTROL_NUMERO_A_LETRA
                            .charAt(digitoD) == caracterFin) ||
                    // �el caracter de control es v�lido como
                    // d�gito?
                            (CONTROL_SOLO_LETRAS.indexOf(letraIni) < 0 && digitoD == Character
                                    .digit(caracterFin, 10));
                    return esControlValido;
                }
            }

        } catch (Exception e) {
            return false;
        }
        return valido;
    }
    
    
    /**
     * Indica si la lista tiene elementos.
     * @param lista
     *            - List
     * @return true o false
     */
    public static boolean esListaConElementos(List<?> lista) {
        boolean tieneElem = false;

        if (!esListaNullVacia(lista) && lista.size() > 0) {
            tieneElem = true;
        }
        return tieneElem;
    }
    


	

	
	
}
