package br.tcc.uni7.utilidades;

import java.sql.SQLException;

import br.tcc.uni7.DAO.LogradouroDAO;
import br.tcc.uni7.entidades.LogradouroEntidade;

public class Teste {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		LogradouroEntidade logradouro = new LogradouroDAO().getLogradouroPorId(1);
		
		System.out.println(logradouro);
		
		

	}

}
