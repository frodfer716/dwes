package Laboral;

/**
 * Clase Nomina de calculo de sueldos
 */
public class Nomina {
	
	/**
	 * Declaracion de biblioteca de sueldos base
	 */
	private static final int SUELDO_BASE[] =
		{50000, 70000, 90000, 110000, 130000,
		150000, 170000, 190000, 210000, 230000};
	
	
	/**
	 * Metodo de calculo de sueldo
	 * @param e
	 * @return sueldo total calculado
	 */
	public double sueldo(Empleado e) {
		Empleado empleado = e;
		
		int sueldoBase = SUELDO_BASE[empleado.getCategoria() - 1];
		double sueldo = sueldoBase + 5000 * empleado.anyos;
		
		return sueldo;
		
	}

}
