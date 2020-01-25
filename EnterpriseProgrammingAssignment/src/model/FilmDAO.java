package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.sql.PreparedStatement;

public class FilmDAO {

	Film oneFilm = null;
	Connection conn = null;
	Statement stmt = null;
	String user = "mkojij";
	String password = "desrtrYo5";
	// Note none default port used, 6306 not 3306
	String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/" + user;
	ArrayList<Film> allFilms = new ArrayList<Film>();

	private static final String InsertFilmSQL = "INSERT INTO films"
			+ "  (id, title, year, director, stars, review) VALUES " + " (?, ?, ?, ?, ?, ?)";
	private static final String SelectFilmByIdSQL = "select * from films where id= ?";
	private static final String SelectFilmSQL = "select * from films limit 20";
	private static final String DeleteFilmSQL = "delete from films where id = ?";
	private static final String updateFilmSQL = "update films set title = ?, year = ?, director = ?, stars = ?, review = ?,  where id= ?";

	public FilmDAO() {
	}

	protected Connection getConnection() {
		// loading jdbc driver for mysql
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	private Film getNextFilm(ResultSet rs) {
		Film thisFilm = null;
		try {
			thisFilm = new Film(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getString("director"),
					rs.getString("stars"), rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thisFilm;
	}

	public ArrayList<Film> getAllFilms() {

		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SelectFilmSQL);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				oneFilm = getNextFilm(rs);
				allFilms.add(oneFilm);
			}
		} catch (SQLException se) {
			System.out.println(se);
		}
		return allFilms;
	}

	public ArrayList<Film> getFilmByID(int id) {
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SelectFilmByIdSQL);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				oneFilm = getNextFilm(rs);
				allFilms.add(oneFilm);
			}
		} catch (SQLException se) {
			System.out.println(se);
		}
		return allFilms;
	}

	public ArrayList<Film> deleteFilm(int id) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DeleteFilmSQL);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();

		}
		return getAllFilms();
	}

	public ArrayList<Film> updateFilm(Film film) throws SQLException {

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(updateFilmSQL);) {
			preparedStatement.setInt(1, film.getId());
			preparedStatement.setString(2, film.getTitle());
			preparedStatement.setInt(3, film.getYear());
			preparedStatement.setString(4, film.getDirector());
			preparedStatement.setString(5, film.getStars());
			preparedStatement.setString(6, film.getReview());

			preparedStatement.executeUpdate();
		}
		return getAllFilms();
	}

	public ArrayList<Film> insertUser(Film film) throws SQLException {
		System.out.println(InsertFilmSQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(InsertFilmSQL)) {
			preparedStatement.setInt(1, film.getId());
			preparedStatement.setString(2, film.getTitle());
			preparedStatement.setInt(3, film.getYear());
			preparedStatement.setString(4, film.getDirector());
			preparedStatement.setString(5, film.getStars());
			preparedStatement.setString(6, film.getReview());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se);
		}
		return getAllFilms();
	}
}
