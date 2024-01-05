package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;

/**
 * Account.
 **/
public class Conta {

  private String tipoConta;
  private String idConta;
  private PessoaCliente pessoaCliente;
  private ArrayList<Transacao> transacoes;

  /**
   * Construtor para a classe Conta.
   */
  public Conta(String tipoConta, PessoaCliente pessoaCliente, Banco banco) {

    this.tipoConta = tipoConta;
    this.pessoaCliente = pessoaCliente;
    this.idConta = banco.gerarNumeroNovaConta();
    this.transacoes = new ArrayList<Transacao>();

  }

  /**
   * Adiciona nova transação na conta.
   */
  public void adicionarTransacao(double quantia, String descricao) {

    // cria nova transacao e adiciona na lista de transacoes da conta
    Transacao novaTransacao = new Transacao(quantia, descricao);
    this.transacoes.add(novaTransacao);

  }


  /**
   * Retorna o saldo da conta somando as transacoes. Obs:depositos sao transacoes com valores
   * positivos e saques sao transacoes com valores negativos.
   */
  public double retornarSaldo() {
    double saldo = 0;
    for (Transacao transacao : this.transacoes) {
      saldo += transacao.getQuantia();
    }

    return saldo;
  }

  /**
   * Retorna o resumo da conta em uma linha.
   */
  public String retornarResumoConta() {

    double saldo = this.retornarSaldo();

    if (saldo >= 0) {
      return String.format("%s : R$%.02f : %s", this.idConta, saldo, this.tipoConta);
    } else {
      return String.format("%s : R$(%.02f) : %s", this.idConta, saldo, this.tipoConta);
    }

  }

  /**
   * Retorna o extrato da conta.
   */
  public void retornarExtrato() {

    System.out.println("\nExtrato da conta " + this.idConta + "\n");
    for (int i = this.transacoes.size() - 1; i >= 0; i--) {
      System.out.println(this.transacoes.get(i).retornarResumoTransacao());
    }
    System.out.println();

  }

  public String getIdConta() {
    return this.idConta;
  }

  public PessoaCliente getPessoaCliente() {
    return this.pessoaCliente;
  }

}