package br.edu.utfpr.dv.ceprest.model;

public class PesquisaCidade {
	private String sigla;
	
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public int getMinPopulacao() {
		return minPopulacao;
	}

	public void setMinPopulacao(int minPopulacao) {
		this.minPopulacao = minPopulacao;
	}

	private int minPopulacao;
	
}
