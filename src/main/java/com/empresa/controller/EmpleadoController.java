package com.empresa.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empresa.dao.AuthDAO;
import com.empresa.dao.EmpleadoDAO;
import com.empresa.model.Empleado;
import com.empresa.model.Nomina;
import com.empresa.model.Usuario;

/**
 * Servlet de implementacion, clase ProductoController
 */
@WebServlet(description = "administra peticiones para la tabla productos", urlPatterns = { "/empresa" })
public class EmpleadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String token;

	/**
	 * constructor EmpleadoController vacio
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpleadoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * metodo doGet para escoger vista a mostrar segun parametro opcion
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String opcion = request.getParameter("opcion");

		if (opcion.equals("menu")) {
			request.setAttribute("content", "/views/menu.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("inicio")) {
			request.setAttribute("content", "/views/inicio.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("log")) {
			request.setAttribute("content", "/views/login.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("registration")) {
			request.setAttribute("content", "/views/register.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("delog")) {
			System.out.println("Token JWT liberado: " + token);
			token = null;
			request.setAttribute("content", "/views/inicio.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("listarEmpleado")) {
			obtenerEmpleado(request, response);

		} else if (opcion.equals("listarSalario")) {
			request.setAttribute("content", "/views/listarSalario.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("buscarEmpleado")) {
			request.setAttribute("content", "/views/buscarEmpleado.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("crear")) {
			request.setAttribute("content", "/views/crear.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("meditar")) {
			String dni = request.getParameter("dni");
			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
			Empleado empleado = new Empleado();
			try {
				empleado = empleadoDAO.obtenerEmpleado(dni);
				request.setAttribute("empleado", empleado);
				request.setAttribute("content", "/views/editar.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException err) {
				// TODO Auto-generated catch block
				err.printStackTrace();
			}

		} else if (opcion.equals("eliminar")) {
			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
			String dni = request.getParameter("dni");
			try {
				empleadoDAO.eliminar(dni);
				obtenerEmpleado(request, response);
				request.setAttribute("mensaje", "Producto eliminado con exito.");
				request.setAttribute("content", "/views/listarEmpleado.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException err) {
				// TODO Auto-generated catch block
				err.printStackTrace();
			}

		}
	}

	/**
	 * metodo doPost para escoger operacion a realizar segun parametro opcion
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		if (opcion.equals("guardar")) {
			String dni = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String sexoStr = request.getParameter("sexo");
			String categoriaStr = request.getParameter("categoria");
			String anyosStr = request.getParameter("anyos");

			String dniError = null;
			String sexoError = null;
			String categoriaError = null;
			String anyosError = null;

			// Validaciones
			if (!esDNIValido(dni)) {
				dniError = "El DNI debe tener el formato español (8 números naturales y una letra).";
			} else {
		        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
		        try {
		            if (empleadoDAO.existeDNI(dni)) {
		                dniError = "El DNI ya existe en la base de datos.";
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

			if (!esSexoValido(sexoStr)) {
				sexoError = "El sexo debe ser 'M' o 'F' (mayúscula o minúscula).";
			}

			try {
				int categoria = Integer.parseInt(categoriaStr);
				if (categoria < 1 || categoria > 10) {
					categoriaError = "La categoría debe ser un número natural entre 1 y 10.";
				}
			} catch (NumberFormatException e) {
				categoriaError = "La categoría debe ser un número.";
			}

			try {
				double anyos = Double.parseDouble(anyosStr);
				if (anyos <= 0) {
					anyosError = "Los años trabajados deben ser un número positivo.";
				}
			} catch (NumberFormatException e) {
				anyosError = "Los años trabajados deben ser un número.";
			}

			if (dniError == null && sexoError == null && categoriaError == null && anyosError == null) {
				Empleado empleado = new Empleado();
				empleado.setDni(dni);
				empleado.setNombre(nombre);
				empleado.setSexo(sexoStr.charAt(0));
				empleado.setCategoria(Integer.parseInt(categoriaStr));
				empleado.setAnyos(Double.parseDouble(anyosStr));

				EmpleadoDAO empleadoDAO = new EmpleadoDAO();

				try {
					empleadoDAO.guardar(empleado);
					obtenerEmpleado(request, response);

					request.setAttribute("content", "/views/listarEmpleado.jsp");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
					requestDispatcher.forward(request, response);
				} catch (SQLException e) {
					// Manejar el error
					e.printStackTrace();
				}
			} else {
				// Enviar errores a la vista
				request.setAttribute("dniError", dniError);
				request.setAttribute("sexoError", sexoError);
				request.setAttribute("categoriaError", categoriaError);
				request.setAttribute("anyosError", anyosError);

				// Redirigir de nuevo a la vista de edición con errores
				request.setAttribute("content", "/views/crear.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);
			}
		} else if (opcion.equals("buscarSalario")) {
			// Código para buscar un empleado por DNI y calcular su salario
			String dniBusqueda = request.getParameter("dniBusqueda");
			String dniError = null;

			if (!esDNIValido(dniBusqueda)) {
				dniError = "El DNI debe tener el formato español.";
			}

			if (dniBusqueda != null && !dniBusqueda.isEmpty()) {
				Nomina nomina = new Nomina();
				EmpleadoDAO empleadoDAO = new EmpleadoDAO();
				try {
					Empleado empleado = empleadoDAO.obtenerEmpleado(dniBusqueda);
					double salario = nomina.sueldo(empleado);

					request.setAttribute("empleado", empleado);
					request.setAttribute("sueldo", salario);

				} catch (Exception e) {
					e.printStackTrace();
				}

				request.setAttribute("dniError", dniError);
				request.setAttribute("content", "/views/listarSalario.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);

			}
		} else if (opcion.equals("buscarEmpleado")) {
			String tipoBusqueda = request.getParameter("tipoBusqueda");
			String valorBusqueda = request.getParameter("valorBusqueda");

			buscarEmpleadosPorCriterio(tipoBusqueda, valorBusqueda, request, response);

		} else if (opcion.equals("editar")) {
			String dni = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String sexoStr = request.getParameter("sexo");
			String categoriaStr = request.getParameter("categoria");
			String anyosStr = request.getParameter("anyos");

			String dniError = null;
			String sexoError = null;
			String categoriaError = null;
			String anyosError = null;

			// Validaciones
			if (!esDNIValido(dni)) {
				dniError = "El DNI debe tener el formato español (8 números naturales y una letra).";
			}

			if (!esSexoValido(sexoStr)) {
				sexoError = "El sexo debe ser 'M' o 'F' (mayúscula o minúscula).";
			}

			try {
				int categoria = Integer.parseInt(categoriaStr);
				if (categoria < 1 || categoria > 10) {
					categoriaError = "La categoría debe ser un número natural entre 1 y 10.";
				}
			} catch (NumberFormatException e) {
				categoriaError = "La categoría debe ser un número.";
			}

			try {
				double anyos = Double.parseDouble(anyosStr);
				if (anyos <= 0) {
					anyosError = "Los años trabajados deben ser un número positivo.";
				}
			} catch (NumberFormatException e) {
				anyosError = "Los años trabajados deben ser un número.";
			}

			if (dniError == null && sexoError == null && categoriaError == null && anyosError == null) {
				Empleado empleado = new Empleado();
				empleado.setDni(dni);
				empleado.setNombre(nombre);
				empleado.setSexo(sexoStr.charAt(0));
				empleado.setCategoria(Integer.parseInt(categoriaStr));
				empleado.setAnyos(Double.parseDouble(anyosStr));

				EmpleadoDAO empleadoDAO = new EmpleadoDAO();

				try {
					empleadoDAO.editar(empleado);
					obtenerEmpleado(request, response);

					request.setAttribute("content", "/views/listarEmpleados.jsp");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
					requestDispatcher.forward(request, response);
				} catch (SQLException e) {
					// Manejar el error
					e.printStackTrace();
				}
				
			} else {
				// Enviar errores a la vista
				request.setAttribute("dniError", dniError);
				request.setAttribute("sexoError", sexoError);
				request.setAttribute("categoriaError", categoriaError);
				request.setAttribute("anyosError", anyosError);

				// Redirigir de nuevo a la vista de edición con errores
				request.setAttribute("content", "/views/editar.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);
			}
			
		} else if (opcion.equals("registrarUsuario")) {
			AuthDAO authDAO = new AuthDAO();

			String name = request.getParameter("name");
			String surnames = request.getParameter("surnames");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			Usuario user = new Usuario(name, surnames, email, password);

			try {
				boolean exito = authDAO.registrarUsuario(user);

				if (exito) {
					request.setAttribute("content", "/views/inicio.jsp");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			        requestDispatcher.forward(request, response);
				} else {
					request.setAttribute("mensajeError",
							"No se pudo completar el registro, porque el email ya existe, o has puesto un campo malo.");
					request.setAttribute("content", "/views/register.jsp");
					
				    RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
					requestDispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		else if (opcion.equals("loginUsuario")) {
			AuthDAO authDAO = new AuthDAO();
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			try {
			    token = authDAO.login(email, password);

			    if (token != null) {
			        request.getSession().setAttribute("token", token);
					request.setAttribute("content", "/views/menu.jsp");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			        requestDispatcher.forward(request, response);
			    } else {
			    	request.setAttribute("mensajeError", "Email o contraseña inválidos");
					request.setAttribute("content", "/views/login.jsp");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			        requestDispatcher.forward(request, response);
			    }

			} catch (SQLException e) {
			    e.printStackTrace();
			}
		}
	}

	/**
	 * metodo para obtener todos los empleados
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public List<Empleado> obtenerEmpleado(HttpServletRequest request, HttpServletResponse response) {
		EmpleadoDAO empleadoDAO = new EmpleadoDAO();
		List<Empleado> lista = new ArrayList<>();
		try {
			lista = empleadoDAO.obtenerEmpleados();

			request.setAttribute("lista", lista);
			request.setAttribute("content", "/views/listarEmpleado.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);

		} catch (SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	/**
	 * metodo para obtener empleado/s segun variable a buscar
	 * 
	 * @param tipoBusqueda
	 * @param valorBusqueda
	 * @param request
	 * @param response
	 * @return
	 */
	private List<Empleado> buscarEmpleadosPorCriterio(String tipoBusqueda, String valorBusqueda,
			HttpServletRequest request, HttpServletResponse response) {
		EmpleadoDAO empleadoDAO = new EmpleadoDAO();
		List<Empleado> lista = new ArrayList<>();

		String dniError = null;
		String sexoError = null;
		String categoriaError = null;
		String anyosError = null;

		if (tipoBusqueda.equals("dni")) {
			if (!esDNIValido(valorBusqueda)) {
				dniError = "El DNI debe tener el formato español.";
			}
		} else if (tipoBusqueda.equals("sexo")) {
			if (!esSexoValido(valorBusqueda)) {
				sexoError = "El sexo debe ser 'M' o 'F'.";
			}
		} else if (tipoBusqueda.equals("categoria")) {
			if (!esNumeroNatural(valorBusqueda, 1, 10)) {
				categoriaError = "La categoría debe estar entre 1 y 10.";
			}
		} else if (tipoBusqueda.equals("anyos")) {
			if (!esNumeroPositivo(valorBusqueda)) {
				anyosError = "Años trabajados no válidos.";
			}
		}

		if (dniError == null && sexoError == null && categoriaError == null && anyosError == null) {
			try {
				switch (tipoBusqueda) {
				case "dni":
					lista = empleadoDAO.buscarPorDNI(valorBusqueda);
					break;
				case "nombre":
					lista = empleadoDAO.buscarPorNombre(valorBusqueda);
					break;
				case "sexo":
					lista = empleadoDAO.buscarPorSexo(valorBusqueda);
					break;
				case "categoria":
					lista = empleadoDAO.buscarPorCategoria(valorBusqueda);
					break;
				case "anyos":
					lista = empleadoDAO.buscarPorAnyosTrabajados(valorBusqueda);
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("lista", lista);
		request.setAttribute("dniError", dniError);
		request.setAttribute("sexoError", sexoError);
		request.setAttribute("categoriaError", categoriaError);
		request.setAttribute("anyosError", anyosError);
		request.setAttribute("content", "/views/buscarEmpleado.jsp");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}

	/**
	 * metodo de validacion de dni.
	 * 
	 * @param dni
	 * @return
	 */
	public boolean esDNIValido(String dni) {
		if (dni == null || dni.length() != 9) {
			return false;
		}
		for (int i = 0; i < 8; i++) {
			if (!Character.isDigit(dni.charAt(i))) {
				return false;
			}
		}
		char letra = dni.charAt(8);
		return Character.isLetter(letra) && Character.isUpperCase(letra);
	}

	/**
	 * metodo de validacion de sexo.
	 * 
	 * @param sexo
	 * @return
	 */
	public boolean esSexoValido(String sexo) {
		return sexo != null && (sexo.equalsIgnoreCase("M") || sexo.equalsIgnoreCase("F"));
	}

	/**
	 * metodo de validacion de categoria.
	 * 
	 * @param valor
	 * @param min
	 * @param max
	 * @return
	 */
	private boolean esNumeroNatural(String valor, int min, int max) {
		try {
			int num = Integer.parseInt(valor);
			return num >= min && num <= max;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * metodo de validacion de anyos.
	 * 
	 * @param valor
	 * @return
	 */
	private boolean esNumeroPositivo(String valor) {
		try {
			double num = Double.parseDouble(valor);
			return num > 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
