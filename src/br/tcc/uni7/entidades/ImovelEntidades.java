package br.tcc.uni7.entidades;

public class ImovelEntidades {
	private Integer codigoImovel;
	private LogradouroEntidade logradouro;
	private ValoresImovelEntidade valores;

	/**
	 * @return the codigoImovel
	 */
	public Integer getCodigoImovel() {
		return codigoImovel;
	}

	/**
	 * @param codigoImovel
	 *            the codigoImovel to set
	 */
	public void setCodigoImovel(Integer codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	/**
	 * @return the logradouro
	 */
	public LogradouroEntidade getLogradouro() {
		return logradouro;
	}

	/**
	 * @param logradouro
	 *            the logradouro to set
	 */
	public void setLogradouro(LogradouroEntidade logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * @return the valores
	 */
	public ValoresImovelEntidade getValores() {
		return valores;
	}

	/**
	 * @param valores
	 *            the valores to set
	 */
	public void setValores(ValoresImovelEntidade valores) {
		this.valores = valores;
	}

}
