package br.tcc.uni7.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.tcc.uni7.entidades.LogradouroEntidade;
import br.tcc.uni7.utilidades.ConnectionFactory;

public class LogradouroDAO {

	private Connection conexao;
	private PreparedStatement pmtp;
	private ResultSet rs;
	private LogradouroEntidade logradouro = new LogradouroEntidade();

	public LogradouroEntidade getLogradouroPorId(Integer id) throws SQLException, ClassNotFoundException {

		try {
			StringBuffer consultaSQL = new StringBuffer();
			consultaSQL.append("SELECT DISTINCT ");
			consultaSQL.append("  logradouro.localizacaoID AS codigoLogradouro, ");
			consultaSQL.append("  logradouro.logradouro AS descricaoLogradouro, ");
			consultaSQL.append("  logradouro.cidadeID AS cidadeID ");
			consultaSQL.append("FROM ");
			consultaSQL.append("  tblocalizacao AS logradouro ");
			consultaSQL.append("WHERE ");
			consultaSQL.append("  logradouro.localizacaoID = ?");
			conexao = ConnectionFactory.getConnection();
			pmtp = conexao.prepareStatement(consultaSQL.toString());
			pmtp.setInt(1, id);
			rs = pmtp.executeQuery();

			try {
				while (rs.next()) {
					logradouro.setCodigoLogradouro(rs.getInt("codigoLogradouro"));
					logradouro.setDescricaoLogradouro(rs.getString("descricaoLogradouro"));
					logradouro.setCodigoCidadeLogradouro(rs.getInt("cidadeID"));
				}
			} catch (NullPointerException e) {
				throw new NullPointerException("A consulta não retornou resultados" + " " + rs.next());
			}

			return logradouro;

		} catch (SQLException e) {
			throw new SQLException("Ocorreu um erro ao conectar com a base de dados" + " " + e.getMessage());
		} finally {
			if (pmtp != null) {
				pmtp.close();
			}

			if (conexao != null) {
				conexao.close();
			}
		}

	}

}
