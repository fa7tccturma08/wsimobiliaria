package br.tcc.uni7.negocio;

import br.tcc.uni7.DAO.CidadeDAO;
import br.tcc.uni7.DAO.ImovelDAO;
import br.tcc.uni7.DAO.LogradouroDAO;
import br.tcc.uni7.DAO.PaisDAO;
import br.tcc.uni7.DAO.UnidadeFederativaDAO;
import br.tcc.uni7.DAO.ValorImovelDAO;
import br.tcc.uni7.entidades.CidadeEntidade;
import br.tcc.uni7.entidades.ImovelEntidades;
import br.tcc.uni7.entidades.LogradouroEntidade;
import br.tcc.uni7.entidades.PaisEntidade;
import br.tcc.uni7.entidades.UnidadeFederativaEntidade;
import br.tcc.uni7.entidades.ValorImovelEntidade;
import br.tcc.uni7.utilidades.Constantes;

public class ImovelNegocio {

	private ImovelEntidades getImovelNegocioPorId(Integer codigoImovel) {
		try {

			/*
			 * Instanciando um objeto imovelEntidades e obtendo os dados da base
			 */
			ImovelEntidades imovelEntidades = new ImovelDAO().getImovelPorId(codigoImovel);
			return imovelEntidades;
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	private ValorImovelEntidade getValoresNegocioPorImovelId(Integer codigoImovel) {
		try {

			/*
			 * Instanciando um objeto valorImovelEntidade e obtendo os dados da
			 * base
			 */
			ValorImovelEntidade valorImovelEntidade = new ValorImovelDAO()
					.getValorImovelPorId(getImovelNegocioPorId(codigoImovel).getCodigoValorImovel());
			return valorImovelEntidade;

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	private Double calculaValorCondominioImovel(Integer codigoValorImovel, Integer tipoImovel) {
		try {

			/* Criando as variaveis que irão armazernar os valores */
			Double valorImovel = null;
			Double valorCondomino = null;
			Double acrescimoCondominio = null;

			/* Checando o tipo de imovel da base de dados */
			if (tipoImovel == Constantes.getTipoImovelApartamento()) {

				acrescimoCondominio = 0.15;
				valorImovel = getValorImovelNegocioPorId(codigoValorImovel);
				valorCondomino = (valorImovel * (Double) acrescimoCondominio);

			} else {
				throw new IllegalArgumentException(
						"Dados informados inválidos  " + " " + codigoValorImovel + "  " + tipoImovel);
			}

			return valorCondomino;

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	private ValorImovelEntidade calcularValoresImovelPorIdTipo(Integer codigoImovel) {
		try {

			/* Instanciando os objetos imovelEntidades e valorImovelEntidade */
			ImovelEntidades imovelEntidades = getImovelNegocioPorId(codigoImovel);
			ValorImovelEntidade valorImovelEntidade = getValoresNegocioPorImovelId(imovelEntidades.getCodigoImovel());

			/* Atribuindo os valores as varivaveis que serão utlilizadas */
			Integer codigoValorImovel = valorImovelEntidade.getCodigoValorImovel();
			Integer codigoTipoImovel = imovelEntidades.getCodigoTipoImovel();
			/*
			 * Realizando os calculos dos valores padrões dos imoveis casa e
			 * apartamento
			 */
			Double valorImovel = (getValorImovelNegocioPorId(codigoValorImovel));
			Double valorAluguel = (valorImovel * (Double) 0.4);
			Double valorIPTU = (valorImovel * (Double) 0.1);
			Double valorAcrescimentoAluguel;
			Double valorAcrescimoIPTU;

			if (codigoTipoImovel == Constantes.getTipoImovelCasa()) {

				/* Acrescenta os valores para o aluguel e o IPTU */
				valorAcrescimentoAluguel = 300.00;
				valorAcrescimoIPTU = 400.00;

				/* Realiza o calculo dos valores referentes a aluguel e IPTU */
				valorAluguel = ((Double) valorAluguel + (Double) valorAcrescimentoAluguel);
				valorIPTU = ((Double) valorIPTU + (Double) valorAcrescimoIPTU);

				/* Atualiza os valores do aluguel e do IPTU */
				valorImovelEntidade.setValorAluguelImovel((Double) valorAluguel);
				valorImovelEntidade.setValorIPTUImovel((Double) valorIPTU);

			} else if (codigoTipoImovel == Constantes.getTipoImovelApartamento()) {
				valorAluguel = valorAluguel + calculaValorCondominioImovel(codigoValorImovel, codigoTipoImovel);
				valorImovelEntidade.setValorAluguelImovel(valorAluguel);
				valorImovelEntidade.setValorIPTUImovel(valorIPTU);
			} else {
				throw new IllegalArgumentException("Dados inválidos");
			}

			return valorImovelEntidade;

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	private void setValoresImovel(Integer codigoImovel) {
		try {

			/* Instanciando os objetos imovelEntidades e valorImovelEntidade */
			ImovelEntidades imovelEntidades = getImovelNegocioPorId(codigoImovel);
			ValorImovelEntidade valorImovelEntidade = getValoresNegocioPorImovelId(codigoImovel);

			/* Obtendo os valores de cada tipo de imovel Casa e Apartamento */
			valorImovelEntidade = calcularValoresImovelPorIdTipo(codigoImovel);
			Double valorIPTU = valorImovelEntidade.getValorIPTUImovel();
			Double valorAluguel = valorImovelEntidade.getValorAluguelImovel();

			/*
			 * Instanciando um objeto valorImovelDAO e atualizando os valores do
			 * IPTU e Aluguel para cada tipo de imovel
			 */
			ValorImovelDAO valorImovelDAO = new ValorImovelDAO();
			valorImovelDAO.setValorAluguelImovelPorId(imovelEntidades.getCodigoValorImovel(), valorAluguel);
			valorImovelDAO.setValorIPTUImovelPorId(imovelEntidades.getCodigoValorImovel(), valorIPTU);

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public Double getValorImovelNegocioPorId(Integer codigoValorImovel) {
		try {
			ValorImovelEntidade valorImovelEntidade = new ValorImovelDAO().getValorImovelPorId(codigoValorImovel);
			Double valorImovel = valorImovelEntidade.getValorImovel();
			return valorImovel;

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public Double getValorAluguelNegocioPorId(Integer codigoImovel) {
		try {

			ValorImovelEntidade valorImovelEntidade = new ValorImovelDAO().getValorImovelPorId(codigoImovel);
			Double valorAluguel = valorImovelEntidade.getValorAluguelImovel();

			setValoresImovel(codigoImovel);
			return valorAluguel;

		} catch (Exception e) {
			throw new IllegalArgumentException("Ocorre um erro ao retornar a valor do Aluguel  ");
		}
	}

	public Double getValorIPTUNegocioPorId(Integer id) {
		try {
			ValorImovelEntidade valorImovelEntidade = new ValorImovelDAO().getValorImovelPorId(id);
			Double valorIPTU = valorImovelEntidade.getValorIPTUImovel();
			return valorIPTU;

		} catch (Exception e) {
			throw new IllegalArgumentException("Ocorre um erro ao retornar a valor do imovel  ");
		}
	}

	public String getLocalizacaoNegocioPorId(Integer codigoImovel) {
		try {

			Integer codigoLogradouro = getImovelNegocioPorId(codigoImovel).getCodigoLocalizacaoImovel();
			LogradouroEntidade logradouroEntidade = new LogradouroDAO().getLogradouroPorId(codigoLogradouro);
			CidadeEntidade cidadeEntidade = new CidadeDAO()
					.getCidadePorId(logradouroEntidade.getCodigoCidadeLogradouro());
			UnidadeFederativaEntidade unidadeFederativaEntidade = new UnidadeFederativaDAO()
					.getUnidadeFederativaPorId(cidadeEntidade.getCodigoEstado());
			PaisEntidade paisEntidade = new PaisDAO().getPaisPorId(unidadeFederativaEntidade.getCodigoPais());
			String descricaoLocalizacao = logradouroEntidade.getDescricaoLogradouro() + " / "
					+ cidadeEntidade.getDescricaoCidade() + " / " + paisEntidade.getDescricaoPais();

			return descricaoLocalizacao;

		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}
