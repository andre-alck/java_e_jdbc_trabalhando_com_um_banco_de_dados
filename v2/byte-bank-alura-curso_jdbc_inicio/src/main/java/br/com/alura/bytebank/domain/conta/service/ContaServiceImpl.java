package br.com.alura.bytebank.domain.conta.service;

import br.com.alura.bytebank.domain.conta.dao.ContaDao;
import br.com.alura.bytebank.domain.conta.model.ContaModel;
import br.com.alura.bytebank.domain.conta.model.DadosAberturaContaModel;
import br.com.alura.bytebank.domain.exception.RegraDeNegocioException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ContaServiceImpl implements ContaService {
    private ContaDao contaDao;
    private Set<ContaModel> contaModels = new HashSet<>();

    public ContaServiceImpl(ContaDao contaDao) {
        this.contaDao = contaDao;
    }

    public Set<ContaModel> listarContasAbertas() {
        return contaDao.listarContasAbertas();
    }

    public void abrir(DadosAberturaContaModel dadosDaConta) {
        contaDao.abrir(dadosDaConta);
    }

    public void encerrar(Integer numeroDaConta) {
        var conta = buscarContaPorNumero(numeroDaConta);
        if (conta.possuiSaldo()) {
            throw new RegraDeNegocioException("Conta não pode ser encerrada pois ainda possui saldo!");
        }

        contaDao.remove(conta);
    }

    public BigDecimal consultarSaldo(Integer numeroDaConta) {
        var conta = buscarContaPorNumero(numeroDaConta);
        return conta.getSaldo();
    }

    public void realizarSaque(Integer numeroDaConta, BigDecimal valor) {
        var conta = buscarContaPorNumero(numeroDaConta);
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor do saque deve ser superior a zero!");
        }

        if (valor.compareTo(conta.getSaldo()) > 0) {
            throw new RegraDeNegocioException("Saldo insuficiente!");
        }

        contaDao.sacar(conta, valor);
    }

    public void realizarDeposito(Integer numeroDaConta, BigDecimal valor) {
        var conta = buscarContaPorNumero(numeroDaConta);
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor do deposito deve ser superior a zero!");
        }

        contaDao.depositar(conta, valor);
    }

    public ContaModel buscarContaPorNumero(Integer numero) {
        return contaDao.buscarContaPorNumero(numero);
    }
}
