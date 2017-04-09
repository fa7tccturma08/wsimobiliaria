package br.tcc.uni7.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.tcc.uni7.entidades.UnidadeFederativaEntidade;
import br.tcc.uni7.utilidades.ConnectionFactory;

public class UnidadeFederativaDAO {

	private UnidadeFederativaEntidade uf = new UnidadeFederativaEntidade();
	private Connection conexao;
	private PreparedStatement pmtp;
	private ResultSet rs;

	public UnidadeFederativaEntidade getUnidadeFederativaPorId(Integer id) throws SQLException, ClassNotFoundException {

		try {
			StringBuffer consultaSQL = new StringBuffer();
			consultaSQL.append("SELECT DISTINCT ");
			consultaSQL.append("  estado.estadoID AS codigoUF, ");
			consultaSQL.append("  estado.nome AS descricaoUF, ");
			consultaSQL.append("  estado.uf AS siglaUF, ");
			consultaSQL.append("  estado.paisID AS paisID ");
			consultaSQL.append("FROM ");
			consultaSQL.append("  tbestado AS estado ");
			consultaSQL.append("WHERE ");
			consultaSQL.append("  estado.estadoID = ?");
			conexao = ConnectionFactory.getConnection();
			pmtp = conexao.prepareStatement(consultaSQL.toString());
			pmtp.setInt(1, id);
			rs = pmtp.executeQuery();

			try {
				while (rs.next()) {
					uf.setCodigoUF(rs.getInt("codigoUF"));
					uf.setDescricaoUF(rs.getString("descricaoUF"));
					uf.setSiglaUF(rs.getString("siglaUF"));
					uf.setCodigoPais(rs.getInt("paisID"));

				}
			} catch (NullPointerException e) {
				throw new NullPointerException("A consulta não retornou resultados");
			}

			return uf;

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
