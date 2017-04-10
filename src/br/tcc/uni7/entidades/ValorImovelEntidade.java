package br.tcc.uni7.entidades;

public class ValorImovelEntidade {
	private Integer codigoValorImovel;
	private Double valorImovel;
	private Double valorIPTUImovel;
	private Double valorAluguelImovel;

	/**
	 * @return the codigoValorImovel
	 */
	public Integer getCodigoValorImovel() {
		return codigoValorImovel;
	}

	/**
	 * @param codigoValorImovel
	 *            the codigoValorImovel to set
	 */
	public void setCodigoValorImovel(Integer codigoValorImovel) {
		this.codigoValorImovel = codigoValorImovel;
	}

	/**
	 * @return the valorIPTUImovel
	 */
	public Double getValorIPTUImovel() {
		return (Double) valorIPTUImovel;
	}

	/**
	 * @param valorIPTUImovel
	 *            the valorIPTUImovel to set
	 */
	public void setValorIPTUImovel(Double valorIPTUImovel) {
		this.valorIPTUImovel = valorIPTUImovel;
	}

	/**
	 * @return the valorAluguelImovel
	 */
	public Double getValorAluguelImovel() {
		return (Double) valorAluguelImovel;
	}

	/**
	 * @param valorAluguelImovel
	 *            the valorAluguelImovel to set
	 */
	public void setValorAluguelImovel(Double valorAluguelImovel) {
		this.valorAluguelImovel = valorAluguelImovel;
	}

	/**
	 * @return the valorImovel
	 */
	public Double getValorImovel() {
		return (Double) valorImovel;
	}

	/**
	 * @param valorImovel
	 *            the valorImovel to set
	 */
	public void setValorImovel(Double valorImovel) {
		this.valorImovel = valorImovel;
	}

}
