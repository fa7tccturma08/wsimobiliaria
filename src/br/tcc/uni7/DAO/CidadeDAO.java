package br.tcc.uni7.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.tcc.uni7.entidades.CidadeEntidade;
import br.tcc.uni7.utilidades.ConnectionFactory;

public class CidadeDAO {

	private Connection conexao;
	private CidadeEntidade cidade = new CidadeEntidade();
	private PreparedStatement pmtp;
	private ResultSet rs;

	public CidadeEntidade getCidadePorId(Integer id) throws SQLException, ClassNotFoundException {

		try {
			StringBuffer consultaSQL = new StringBuffer();
			consultaSQL.append("SELECT DISTINCT ");
			consultaSQL.append("  cidade.cidadeID AS codigoCidade, ");
			consultaSQL.append("  cidade.nome AS descricaoCidade, ");
			consultaSQL.append("  cidade.estadoID AS estadoID ");
			consultaSQL.append("FROM ");
			consultaSQL.append("  tbcidade AS cidade ");
			consultaSQL.append("WHERE ");
			consultaSQL.append("  cidade.cidadeID = ?");
			conexao = ConnectionFactory.getConnection();
			pmtp = conexao.prepareStatement(consultaSQL.toString());
			pmtp.setInt(1, id);
			rs = pmtp.executeQuery();

			try {
				while (rs.next()) {
					cidade.setCodigoCidade(rs.getInt("codigoCidade"));
					cidade.setDescricaoCidade(rs.getString("descricaoCidade"));
					cidade.setCodigoEstado(rs.getInt("estadoID"));
				}
			} catch (NullPointerException e) {
				throw new NullPointerException("A consulta não retornou resultados" + " " + rs.next());
			}

			return cidade;

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
