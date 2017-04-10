package br.tcc.uni7.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.tcc.uni7.entidades.ValorImovelEntidade;
import br.tcc.uni7.utilidades.ConnectionFactory;

public class ValorImovelDAO {

	private Connection conexao;
	private PreparedStatement pmtp;
	private ResultSet rs;
	private ValorImovelEntidade valor = new ValorImovelEntidade();

	public ValorImovelEntidade getValorImovelPorId(Integer id) throws SQLException, ClassNotFoundException {
		try {

			StringBuffer consultaSQL = new StringBuffer();
			consultaSQL.append("SELECT DISTINCT ");
			consultaSQL.append("  valores.valorID AS codigoValorImovel, ");
			consultaSQL.append("  valores.valorImovel AS valorImovel, ");
			consultaSQL.append("  valores.valorIPTU as valorIPTU, ");
			consultaSQL.append("  valores.valorAluguel AS valorAluguel ");
			consultaSQL.append("FROM ");
			consultaSQL.append("  tbvalores AS valores ");
			consultaSQL.append("WHERE ");
			consultaSQL.append("  valores.valorID = ?");
			conexao = ConnectionFactory.getConnection();
			pmtp = conexao.prepareStatement(consultaSQL.toString());
			pmtp.setInt(1, id);
			rs = pmtp.executeQuery();

			try {

				while (rs.next()) {
					valor.setCodigoValorImovel(rs.getInt("codigoValorImovel"));
					valor.setValorImovel(rs.getDouble("valorImovel"));
					valor.setValorIPTUImovel(rs.getDouble("valorIPTU"));
					valor.setValorAluguelImovel(rs.getDouble("valorAluguel"));
				}

				return valor;
			} catch (NullPointerException e) {
				throw new NullPointerException("A consulta não retornou resultados");
			}
		} catch (SQLException e) {
			throw new SQLException("Ocorreu um erro ao conectar a base de dados " + "  " + e.getMessage());

		} finally {
			if (pmtp != null) {
				pmtp.close();
			}
			if (conexao != null) {
				conexao.close();
			}
		}
	}

	public void setValorAluguelImovelPorId(Integer id, Double valor) throws SQLException, ClassNotFoundException {

		try {

			StringBuilder consultaSQL = new StringBuilder();
			consultaSQL.append(" UPDATE  ");
			consultaSQL.append(" tbvalores	 ");
			consultaSQL.append(" SET ");
			consultaSQL.append(" valorAluguel = ? ");
			consultaSQL.append(" WHERE ");
			consultaSQL.append(" valorID = ? ");
			conexao = ConnectionFactory.getConnection();
			pmtp = conexao.prepareStatement(consultaSQL.toString());
			pmtp.setDouble(1, (Double) valor);
			pmtp.setInt(2, id);
			conexao.setAutoCommit(false);
			pmtp.executeUpdate();
			conexao.commit();

		} catch (SQLException e) {
			conexao.setAutoCommit(false);
			conexao.rollback();
			throw new SQLException("Ocorreu um erro ao conectar a base de dados " + e.getMessage());
		} finally {
			if (pmtp != null) {
				pmtp.close();
			}
			if (conexao != null) {
				conexao.close();
			}
		}
	}

	public void setValorIPTUImovelPorId(Integer id, Double valor) throws SQLException, ClassNotFoundException {

		try {

			StringBuilder consultaSQL = new StringBuilder();
			consultaSQL.append(" UPDATE  ");
			consultaSQL.append(" tbvalores	 ");
			consultaSQL.append(" SET ");
			consultaSQL.append(" valorIPTU = ? ");
			consultaSQL.append(" WHERE ");
			consultaSQL.append(" valorID = ? ");
			conexao = ConnectionFactory.getConnection();
			pmtp = conexao.prepareStatement(consultaSQL.toString());
			pmtp.setDouble(1, (Double) valor);
			pmtp.setInt(2, id);
			conexao.setAutoCommit(false);
			pmtp.execute();
			conexao.commit();

		} catch (SQLException e) {
			conexao.rollback();
			throw new SQLException("Ocorreu um erro ao conectar a base de dados " + e.getMessage());
		}
	}

}
