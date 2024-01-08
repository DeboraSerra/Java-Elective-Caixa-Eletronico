package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;
import java.util.Random;

/**
 * Bank.
 **/
public class Banco {

  private ArrayList<PessoaCliente> pessoasClientes;
  private ArrayList<Conta> contas;

  /**
   * Construtor para a classe Banco.
   */
  public Banco() {
    pessoasClientes = new ArrayList<PessoaCliente>();
    contas = new ArrayList<Conta>();
  }

  /**
   * Gera um número de conta aleatório.
   */
  public String gerarNumeroNovaConta() {

    String idConta;
    Random numeroAleatorio = new Random();
    int tamanhoId = 10;
    boolean naoUnico;

    // aqui vamos ficar em loop ate gerar um id unico
    do {

      idConta = "";

      for (int i = 0; i < tamanhoId; i++) {
        idConta += String.valueOf(Integer.valueOf(numeroAleatorio.nextInt(10)));
      }

      // verifica se unicidade
      naoUnico = false;
      for (Conta conta : this.contas) {
        if (idConta.compareTo(conta.getIdConta()) == 0) {
          naoUnico = true;
          break;
        }
      }

    } while (naoUnico);

    return idConta;
  }

  /**
   * Vincula uma pessoa cliente ao Banco.
   */
  public PessoaCliente adicionarPessoaCliente(String nome, String cpf, String senha) {

    PessoaCliente pessoaCliente = new PessoaCliente(nome, cpf, senha);
    this.pessoasClientes.add(pessoaCliente);

    return pessoaCliente;

  }

  /**
   * Vincula uma pessoa cliente a uma nova conta.
   */
  public void adicionarConta(String tipoConta, PessoaCliente pessoaCliente) {
    Conta novaConta = new Conta(tipoConta, pessoaCliente, this);
    pessoaCliente.adicionarConta(novaConta);
    this.contas.add(novaConta);
  }

  /**
   * Verifica se o CPF e a senha de uma PessoaCliente são válidos.
   */
  public PessoaCliente pessoaClienteLogin(String cpf, String senha) {

    for (PessoaCliente pessoa : this.pessoasClientes) {
      if (pessoa.getCpf().compareTo(cpf) == 0 && pessoa.validarSenha(senha)) {
        return pessoa;
      }
    }

    /*
     * se a pessoa cliente tiver entrado com o cpf ou a senha incorreta retorna null
     */
    return null;

  }

  /**
   * Transfere fundos de uma conta para outra da pessoa cliente.
   */
  public void transferirFundos(PessoaCliente pessoaCliente, int daConta, int paraConta,
      double quantia) {

    // realiza a transferencia
    pessoaCliente.adicionarTransacaoContaEspecifica(daConta, -1 * quantia, "Transferência enviada");
    pessoaCliente.adicionarTransacaoContaEspecifica(paraConta, quantia, "Transferência recebida");

  }

  /**
   * Deposita na conta.
   */
  public void depositar(PessoaCliente pessoaCliente, int paraConta, double quantia) {
    pessoaCliente.adicionarTransacaoContaEspecifica(paraConta, quantia, "Depósito recebido");
  }

  /**
   * Saca dinheiro da conta.
   *
   * @param deConta o número da conta.
   * @param quantia o valor a ser sacado.
   */
  public void sacar(PessoaCliente pessoaCliente, int deConta, double quantia) {
    // executa o saque
    pessoaCliente.adicionarTransacaoContaEspecifica(deConta, -1 * quantia, "Saque efetuado");
  }

  /**
   * Mostra o extrato.
   */
  public void mostrarExtrato(PessoaCliente pessoaCliente, int conta) {

    // imprime o extrato de uma conta especifica
    pessoaCliente.retornarExtratoContaEspecifica(conta);

  }

  public ArrayList<PessoaCliente> getPessoasClientes() {
    return this.pessoasClientes;
  }
}