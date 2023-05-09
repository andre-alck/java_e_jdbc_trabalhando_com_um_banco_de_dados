package br.com.alura.bytebank.domain.conta.dao;

import br.com.alura.bytebank.domain.cliente.Cliente;
import br.com.alura.bytebank.domain.cliente.DadosCadastroCliente;
import br.com.alura.bytebank.domain.conta.model.ContaModel;
import br.com.alura.bytebank.domain.conta.model.DadosAberturaContaModel;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ContaDao {
    private Connection connection;

    public ContaDao(Connection connection) {
        this.connection = connection;
    }

    public void abrir(DadosAberturaContaModel dadosDaConta) {
        var cliente = new Cliente(dadosDaConta.dadosCliente());
        var conta = new ContaModel(dadosDaConta.numero(), cliente);

        String sql = "insert into conta (numero, saldo, cliente_nome, cliente_cpf, cliente_email) values (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, conta.getNumero());
            preparedStatement.setBigDecimal(2, BigDecimal.ZERO);
            preparedStatement.setString(3, dadosDaConta.dadosCliente().nome());
            preparedStatement.setString(4, dadosDaConta.dadosCliente().cpf());
            preparedStatement.setString(5, dadosDaConta.dadosCliente().email());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<ContaModel> listarContasAbertas() {
        String sql = "select c.numero, c.saldo, c.cliente_nome, c.cliente_cpf, c.cliente_email from conta c";

        Set<ContaModel> contas = new HashSet<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while(resultSet.next()) {
                int numero = resultSet.getInt("numero");
                String nome = resultSet.getString("cliente_nome");
                String cpf = resultSet.getString("cliente_cpf");
                String email = resultSet.getString("cliente_email");
                DadosCadastroCliente dadosCadastroCliente = new DadosCadastroCliente(nome, cpf, email);
                Cliente cliente = new Cliente(dadosCadastroCliente);

                ContaModel novaConta = new ContaModel(numero, cliente);
                contas.add(novaConta);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch(SQLException exception) {
            throw new RuntimeException(exception);
        }

        return contas;
    }

    public void remove(ContaModel conta) {
        String sql = "delete from conta c where c.numero = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, conta.getNumero());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch(SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public ContaModel buscarContaPorNumero(Integer numero) {
        String sql = "select c.numero, c.saldo, c.cliente_nome, c.cliente_cpf, c.cliente_email from conta c where c.numero = ?";

        ContaModel contaModel = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, numero);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                int n = resultSet.getInt("numero");
                float saldo = resultSet.getFloat("saldo");
                String clienteNome = resultSet.getString("cliente_nome");
                String clienteCpf = resultSet.getString("cliente_cpf");
                String clienteEmail = resultSet.getString("cliente_email");
                DadosCadastroCliente dadosCadastroCliente = new DadosCadastroCliente(clienteNome, clienteCpf, clienteEmail);
                Cliente cliente = new Cliente(dadosCadastroCliente);

                contaModel = new ContaModel(numero, cliente);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return contaModel;
    }

    public void sacar(ContaModel conta, BigDecimal valor) {
        BigDecimal novoSaldo = conta.getSaldo().subtract(valor);
        String sql = "update conta c set c.saldo = ? where numero = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setBigDecimal(1, novoSaldo);
            preparedStatement.setInt(2, conta.getNumero());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch(SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void depositar(ContaModel conta, BigDecimal valor) {
        BigDecimal novoSaldo = conta.getSaldo().add(valor);
        String sql = "update conta c set c.saldo = ? where numero = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setBigDecimal(1, novoSaldo);
            preparedStatement.setInt(2, conta.getNumero());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch(SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
