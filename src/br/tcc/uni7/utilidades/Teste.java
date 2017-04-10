package br.tcc.uni7.utilidades;

import java.sql.SQLException;

import br.tcc.uni7.DAO.ValorImovelDAO;
import br.tcc.uni7.entidades.ValorImovelEntidade;

public class Teste {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ValorImovelEntidade valor = new ValorImovelEntidade();
		ValorImovelDAO dao = new ValorImovelDAO();
		valor=dao.getValorImovelPorId(1);
		
		dao.setValorAluguelImovelPorId(1, 0.00);
		
		
		
		System.out.println((Double)valor.getValorAluguelImovel());
		

	}

}
