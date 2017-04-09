package br.tcc.uni7.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.tcc.uni7.entidades.PaisEntidade;
import br.tcc.uni7.utilidades.ConnectionFactory;

public class PaisDAO {

	private Connection conexao;
	private PreparedStatement pmtp;
	private ResultSet rs;
	private PaisEntidade pais = new PaisEntidade();

	public PaisEntidade getPaisPorId(Integer id) throws SQLException, ClassNotFoundException {

		try {
			StringBuilder consultaSQL = new StringBuilder();
			consultaSQL.append("  SELECT DISTINCT ");
			consultaSQL.append("  pais.paisID AS codigoPais,  ");
			consultaSQL.append("  pais.nome AS descricaoPais, ");
			consultaSQL.append("  pais.sigla AS abreviacaoPais	");
			consultaSQL.append("  FROM		");
			consultaSQL.append("  tbpais AS pais ");
			consultaSQL.append("  WHERE ");
			consultaSQL.append("  pais.paisID = ?  ");
			conexao = ConnectionFactory.getConnection();
			pmtp = conexao.prepareStatement(consultaSQL.toString());
			pmtp.setInt(1, id);
			rs = pmtp.executeQuery();

			try {
				while (rs.next()) {
					pais.setCodigoPais(rs.getInt("codigoPais"));
					pais.setDescricaoPais(rs.getString("descricaoPais"));
					pais.setSiglaPais("abreviacaoPais");
				}
			} catch (NullPointerException e) {
				throw new NullPointerException("A consulta não retornou resultados");
			}

			return pais;

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
