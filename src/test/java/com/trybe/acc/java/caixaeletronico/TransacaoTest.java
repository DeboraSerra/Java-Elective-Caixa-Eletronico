package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.RegexConversion;

@DisplayName("Testes dos métodos da classe Transação")
class TransacaoTest {



  @Test
  @DisplayName("1 - Testa o método construtor da classe Transação.")
  void construtorTest() {
    Transacao transacao = new Transacao(100, "teste");
    assertEquals(100, transacao.getQuantia());
  }


  @Test
  @DisplayName("2 - Testa o método Getter do atributo quantia.")
  void getQuantiaTest() {
    Transacao transacao = new Transacao(100, "teste");
    assertEquals(100, transacao.getQuantia());
  }

  @Test
  @DisplayName("3 - Testa o método retornar resumo transação.")
  void retornarResumoTransacaoTest() {
    Transacao transacao = new Transacao(100, "teste");
    String expected =  " -------- teste: R$ 100.00 +";
    String result = transacao.retornarResumoTransacao();
    assertTrue(result.contains(expected));

  }

  @Test
  @DisplayName("4 - Testa o método instante está gerando o instante corretamente.")
  void retornarInstanteTest() {
    Transacao transacao = new Transacao(100, "teste");
    String result = transacao.retornarResumoTransacao();
    String expected = "^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2} -------- teste: R\\$ 100.00 \\+$";
    assertTrue(result.matches(expected));
  }

}
