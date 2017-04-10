package br.tcc.uni7.utilidades;

public class Constantes {

	private final static String DRIVER_MYSQL = "com.mysql.jdbc.Driver";

	private final static String SERVER_NAME_MYSQL = "localhost";

	private final static String PORTA_MYSQL = "3306";

	private final static String USUARIO_MYSQL = "root";

	private final static String SENHA_MYSQL = "root";

	private final static String BD_MYSQL = "wsimobiliaria";
	
	private final static Integer TIPO_IMOVEL_CASA=1;
	
	private final static Integer TIPO_IMOVEL_APARTAMENTO=2;
	

	/**
	 * @return the driverMysql
	 */
	public static String getDriverMysql() {
		return DRIVER_MYSQL;
	}

	/**
	 * @return the serverNameMysql
	 */
	public static String getServerNameMysql() {
		return SERVER_NAME_MYSQL;
	}

	/**
	 * @return the portaMysql
	 */
	public static String getPortaMysql() {
		return PORTA_MYSQL;
	}

	/**
	 * @return the usuarioMysql
	 */
	public static String getUsuarioMysql() {
		return USUARIO_MYSQL;
	}

	/**
	 * @return the senhaMysql
	 */
	public static String getSenhaMysql() {
		return SENHA_MYSQL;
	}

	/**
	 * @return the bdMysql
	 */
	public static String getBdMysql() {
		return BD_MYSQL;
	}

	/**
	 * @return the tipoImovelCasa
	 */
	public static Integer getTipoImovelCasa() {
		return TIPO_IMOVEL_CASA;
	}

	/**
	 * @return the tipoImovelApartamento
	 */
	public static Integer getTipoImovelApartamento() {
		return TIPO_IMOVEL_APARTAMENTO;
	}
	
	
	
}
