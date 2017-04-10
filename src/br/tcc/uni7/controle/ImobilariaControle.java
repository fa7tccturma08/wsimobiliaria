package br.tcc.uni7.controle;

import java.util.ArrayList;
import java.util.List;

import br.tcc.uni7.DAO.ImovelDAO;
import br.tcc.uni7.entidades.ImovelEntidades;
import br.tcc.uni7.negocio.ImovelNegocio;

public class ImobilariaControle {
	private ImovelNegocio imovelNegocio = new ImovelNegocio();

	private Boolean validaDadosImoveis(Integer codigoImovel) {
		try {
			List<ImovelEntidades> listImovel = new ArrayList<>();
			ImovelEntidades imovelEntidades = new ImovelDAO().getImovelPorId(codigoImovel);
			listImovel.add(imovelEntidades);
			Boolean imovelValido;
			if (!listImovel.isEmpty() && listImovel != null) {
				imovelValido = true;
			} else {
				imovelValido = false;
			}

			return imovelValido;
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public String getLocalizacaoImovel(Integer codigoImovel) {
		try {

			Boolean imovelValido = validaDadosImoveis(codigoImovel);
			String localizacao = " ";
			if (imovelValido == true) {
				localizacao = imovelNegocio.getLocalizacaoNegocioPorId(codigoImovel);
			} else if (imovelValido == false) {
				throw new IllegalArgumentException("Valor informado inválido" + " " + codigoImovel);
			}

			return localizacao;

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	public Double getValorImovel(Integer codigoImovel) {
		try {

			Boolean imovelValido = validaDadosImoveis(codigoImovel);
			Double valorImovel = 0.00;
			if (imovelValido == true) {
				valorImovel = (Double) new ImovelNegocio().getValorImovelNegocioPorId(codigoImovel);
			} else if (imovelValido == false) {
				throw new IllegalArgumentException("Valor informado inválido" + " " + codigoImovel);
			}
			return valorImovel;
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public Double getValorIPTUImovel(Integer codigoImovel) {
		try {

			Boolean imovelValido = validaDadosImoveis(codigoImovel);
			Double valorImovelIPTU = 0.00;
			if (imovelValido == true) {
				valorImovelIPTU = (Double) new ImovelNegocio().getValorIPTUNegocioPorId(codigoImovel);
			} else if (imovelValido == false) {
				throw new IllegalArgumentException("Valor informado inválido" + " " + codigoImovel);
			}
			return valorImovelIPTU;
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public Double getValorAluguelImovel(Integer codigoImovel) {
		try {

			Boolean imovelValido = validaDadosImoveis(codigoImovel);
			Double valorImovelAluguel = 0.00;
			if (imovelValido == true) {
				valorImovelAluguel = (Double) new ImovelNegocio().getValorAluguelNegocioPorId(codigoImovel);
			} else if (imovelValido == false) {
				throw new IllegalArgumentException("Valor informado inválido" + " " + codigoImovel);
			}
			return valorImovelAluguel;
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}
