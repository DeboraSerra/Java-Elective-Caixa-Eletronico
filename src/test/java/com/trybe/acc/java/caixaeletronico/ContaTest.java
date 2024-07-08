package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Teste da classe Conta")
class ContaTest {
  PessoaCliente person = new PessoaCliente("Maia", "000.000.000-00", "********");
  Banco bank = new Banco();
  Conta conta = new Conta("corrente",  person,  bank);

  @Test
  @DisplayName("5 - Testa o construtor da classe conta.")
  void construtorTest() {
    assertEquals(10, conta.getIdConta().length());
    assertEquals("000.000.000-00", conta.getPessoaCliente().getCpf());;
  }

  @Test
  @DisplayName("6 - Testa o método adicionar transação e retornar saldo da conta.")
  void adicionarTransacaoTestRetornarSaldoTest() {
    conta.adicionarTransacao(100, "test");
    double result = conta.retornarSaldo();
    assertEquals(100, result);
  }

  @Test
  @DisplayName("7 - Testa o método retornar resumo está retornando uma string com os valores corretamente.")
  void retornarResumoContaTest() {
    conta.adicionarTransacao(100, "test");
    String result = conta.retornarResumoConta();
    String expected = "\\d{10} : R\\$100.00 : corrente";
    assertTrue(result.matches(expected));
  }

  @Test
  @DisplayName("8 - Testa o método retornar extrato está imprimindo os valores corretamente.")
  void retornarExtratoTest() {
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outStream));
    conta.adicionarTransacao(100, "test");
    conta.retornarExtrato();

    String result = outStream.toString();
    assertTrue(result.contains("Extrato da conta"));
  }

  @Test
  @DisplayName("9 - Testa o método Getter do atributo idConta está retornando.")
  void getIdContaTest() {
    String id = conta.getIdConta();
    assertEquals(10, id.length());
  }

  @Test
  @DisplayName("10 - Testa o método método Getter do atributo pessoaCliente está retornando.")
  void getPessoaClienteTest() {
    PessoaCliente client = conta.getPessoaCliente();
    assertEquals(PessoaCliente.class, client.getClass());
  }

}
