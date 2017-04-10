package br.tcc.uni7.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import br.tcc.uni7.entidades.TipoImovelEntidade;
import br.tcc.uni7.utilidades.ConnectionFactory;

public class TipoImovelDAO {

	private Connection conexao;
	private PreparedStatement pmtp;
	private ResultSet rs;
	private List<TipoImovelEntidade> listaTipoImoveis = new Vector<TipoImovelEntidade>();

	public List<TipoImovelEntidade> getListaTipoImoveis() throws SQLException, ClassNotFoundException {
		try {

			StringBuilder consultaSQl = new StringBuilder();
			consultaSQl.append(" SELECT DISTINCT  ");
			consultaSQl.append(" tipoImovel.tipoID AS codigoTipoImovel, ");
			consultaSQl.append(" tipoImovel.nome AS descricaoTipoImovel ");
			consultaSQl.append(" FROM	 ");
			consultaSQl.append(" tbtipoimovel AS tipoImovel	 ");

			conexao = ConnectionFactory.getConnection();
			pmtp = conexao.prepareStatement(consultaSQl.toString());
			rs = pmtp.executeQuery();

			try {

				while (rs.next()) {
					TipoImovelEntidade tipoImovelEntidade = new TipoImovelEntidade();
					tipoImovelEntidade.setCodigoTipoImovel(rs.getInt("codigoTipoImovel"));
					tipoImovelEntidade.setDescricaoTipoImovel(rs.getString("descricaoTipoImovel"));
					listaTipoImoveis.add(tipoImovelEntidade);
				}
			} catch (NullPointerException e) {
				throw new NullPointerException("A consulta não retornou resultados ");
			}

			return listaTipoImoveis;
		} catch (SQLException e) {
			throw new SQLException("Ocorreu um erro a acessar a base dados " + " " + e.getMessage());
		}
	}

}
