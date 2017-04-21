package br.tcc.uni7.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection connection = null;
		try {
			String server = Constantes.getServerNameMysql();
			String port = Constantes.getPortaMysql(); // Porta TCP padrão do
														// MySQL
			String database = Constantes.getBdMysql();

			String user = Constantes.getUsuarioMysql();
			String passwd = Constantes.getSenhaMysql();

			String url = "jdbc:mysql://" + server + ":" + port + "/" + database;
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());	
			connection = DriverManager.getConnection(url, user, passwd);
			
			return connection;

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

	}
}
