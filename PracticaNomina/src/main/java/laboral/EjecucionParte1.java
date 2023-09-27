package laboral;

/**
 * Clase contenedora del codigo Main de la Parte 1
 */
public class EjecucionParte1 {
	
	public static void ejecucion() {
		try {
			
			Empleado e1 = new Empleado("James Cosling", "32000032G", 'M', 4, 7);
			Empleado e2 = new Empleado("Ada Lovelace", "32000031R", 'F');
			
			System.out.println("Empleado 1:");
			escribe(e1);
			System.out.println();
			System.out.println("Empleado 2:");
			escribe(e2);
			System.out.println();
			
			System.out.println("Actualizacion empleados");
			e2.incrAnyo();
			e1.setCategoria(9);
			System.out.println();
			
			System.out.println("Empleado 1:");
			escribe(e1);
			System.out.println();
			System.out.println("Empleado 2:");
			escribe(e2);
			
			System.out.println("Actualizacion e1 para prueba de errores");
			e1.setCategoria(11);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Salida por consola de todos los datos del empleado + su sueldo total
	 * @param e
	 */
	private static void escribe(Empleado e) {
		e.imprime();
		Nomina n = new Nomina();
		System.out.println("Sueldo: " + n.sueldo(e));
	}
	
}
