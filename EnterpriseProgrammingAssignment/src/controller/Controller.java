package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import coreservlets.String;
import model.Film;
import model.FilmDAO;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

		switch (action) {
		case "/allFilms":
			getAllFilms(request, response);
			break;
		case "/searchID":
			getFilmByID(request, response);
			break;
		case "/delete":
			deleteFilm(request, response);
			break;
		case "/update":
			updateFilm(request, response);
			break;
		case "/insert":
			insertFilm(request, response);
			break;
		default:
			getAllFilms(request, response);
			break;
		}

	}

	public void getAllFilms(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FilmDAO fdao = new FilmDAO();
		ArrayList<Film> films = new ArrayList<Film>();
		films = fdao.getAllFilms();

		request.setAttribute("films", films);
		String format = request.getParameter("format");

		String outputPage;
		if ("xml".equals(format)) {
			response.setContentType("text/xml");
			outputPage = "/WEB-INF/results/films-xml.jsp";
		} else if ("json".equals(format)) {
			response.setContentType("application/json");
			outputPage = "/WEB-INF/results/films-json.jsp";
		} else {
			response.setContentType("text/plain");
			outputPage = "/WEB-INF/results/films-string.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(outputPage);
		dispatcher.include(request, response);

	}

	public void getFilmByID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FilmDAO fdao = new FilmDAO();
		ArrayList<Film> films = new ArrayList<Film>();
		films = fdao.getFilmByID(10013);

		request.setAttribute("films", films);
		String format = request.getParameter("format");

		String outputPage;
		if ("xml".equals(format)) {
			response.setContentType("text/xml");
			outputPage = "/WEB-INF/results/films-xml.jsp";
		} else if ("json".equals(format)) {
			response.setContentType("application/json");
			outputPage = "/WEB-INF/results/films-json.jsp";
		} else {
			response.setContentType("text/plain");
			outputPage = "/WEB-INF/results/films-string.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(outputPage);
		dispatcher.include(request, response);

	}

	public void deleteFilm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FilmDAO fdao = new FilmDAO();
		ArrayList<Film> films = new ArrayList<Film>();
		
		try {
			films = fdao.deleteFilm(10002);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("films", films);
		String format = request.getParameter("format");

		String outputPage;
		if ("xml".equals(format)) {
			response.setContentType("text/xml");
			outputPage = "/WEB-INF/results/films-xml.jsp";
		} else if ("json".equals(format)) {
			response.setContentType("application/json");
			outputPage = "/WEB-INF/results/films-json.jsp";
		} else {
			response.setContentType("text/plain");
			outputPage = "/WEB-INF/results/films-string.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(outputPage);
		dispatcher.include(request, response);
	}

	public void updateFilm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void insertFilm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
