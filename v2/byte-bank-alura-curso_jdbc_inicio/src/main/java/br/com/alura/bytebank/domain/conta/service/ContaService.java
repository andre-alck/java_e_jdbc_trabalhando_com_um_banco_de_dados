package br.com.alura.bytebank.domain.conta.service;

import br.com.alura.bytebank.domain.conta.model.ContaModel;
import br.com.alura.bytebank.domain.conta.model.DadosAberturaContaModel;

import java.math.BigDecimal;
import java.util.Set;

public interface ContaService {
    Set<ContaModel> listarContasAbertas();

    void abrir(DadosAberturaContaModel dadosDaConta);

    void encerrar(Integer numeroDaConta);

    BigDecimal consultarSaldo(Integer numeroDaConta);

    void realizarSaque(Integer numeroDaConta, BigDecimal valor);

    void realizarDeposito(Integer numeroDaConta, BigDecimal valor);

    ContaModel buscarContaPorNumero(Integer numero);
}
