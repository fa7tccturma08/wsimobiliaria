package br.tcc.uni7.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.tcc.uni7.entidades.ImovelEntidades;
import br.tcc.uni7.utilidades.ConnectionFactory;

public class ImovelDAO {

	private Connection conexao;
	private PreparedStatement pmtp;
	private ResultSet rs;
	private ImovelEntidades imovel = new ImovelEntidades();

	public ImovelEntidades getImovelPorId(Integer id) throws SQLException, ClassNotFoundException {
		try {

			StringBuilder consultaSQL = new StringBuilder();
			consultaSQL.append("SELECT ");
			consultaSQL.append("imovel.ImovelID AS codigoImovel,   ");
			consultaSQL.append("imovel.TipoID AS codigoTipoImovel, ");
			consultaSQL.append("imovel.LocalizacaoID AS codigoTipoLocalizacao, ");
			consultaSQL.append("imovel.valorID AS codigoValorImovel ");
			consultaSQL.append("FROM tbimovel AS imovel ");
			consultaSQL.append("WHERE  ");
			consultaSQL.append("imovel.ImovelID = ?  ");
			conexao = ConnectionFactory.getConnection();
			pmtp = conexao.prepareStatement(consultaSQL.toString());
			pmtp.setInt(1, id);
			rs = pmtp.executeQuery();

			try {
				while (rs.next()) {
					imovel.setCodigoImovel(rs.getInt("codigoImovel"));
					imovel.setCodigoTipoImovel(rs.getInt("codigoTipoImovel"));
					imovel.setCodigoLocalizacaoImovel(rs.getInt("codigoTipoLocalizacao"));
					imovel.setCodigoValorImovel(rs.getInt("codigoValorImovel"));
				}
				return imovel;
			} catch (NullPointerException e) {
				throw new NullPointerException("A consulta não retornou resultados");
			}

		} catch (SQLException e) {
			throw new SQLException("Ocorreu um erro a acessar a base de dados " + " " + e.getMessage());
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
