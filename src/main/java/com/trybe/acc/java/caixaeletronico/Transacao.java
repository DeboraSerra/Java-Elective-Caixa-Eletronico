package com.trybe.acc.java.caixaeletronico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Transaction.
 **/
public class Transacao {

  private double quantia;
  private String instante;
  private String descricao;

  /**
   * Construtor da classe Transacao.
   */
  public Transacao(double quantia, String descricao) {
    this.quantia = quantia;
    this.instante = this.retornarInstante();
    this.descricao = descricao;

  }

  /**
   * Retorna a quantia de uma transação.
   */
  public double getQuantia() {
    return this.quantia;
  }

  /**
   * Retorna o resumo da transação.
   */
  public String retornarResumoTransacao() {

    if (this.quantia >= 0) {
      return String.format("%s -------- %s: R$ %.02f +", this.instante, this.descricao,
          this.quantia);

    } else {
      return String.format("%s -------- %s: R$ %.02f -", this.instante, this.descricao,
          -this.quantia);
    }
  }

  private String retornarInstante() {

    String formatoInstante = "dd/MM/yyyy HH:mm:ss";
    DateTimeFormatter formatadorInstante = DateTimeFormatter.ofPattern(formatoInstante);
    LocalDateTime instanteAux = LocalDateTime.now();

    return formatadorInstante.format(instanteAux);
  }

}
