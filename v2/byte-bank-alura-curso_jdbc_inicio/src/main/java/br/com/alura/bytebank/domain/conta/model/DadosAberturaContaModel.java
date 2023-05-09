package br.com.alura.bytebank.domain.conta.model;

import br.com.alura.bytebank.domain.cliente.DadosCadastroCliente;

public record DadosAberturaContaModel(Integer numero, DadosCadastroCliente dadosCliente) {
}
