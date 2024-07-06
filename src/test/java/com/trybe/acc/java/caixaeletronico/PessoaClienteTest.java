package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Teste dos métodos da classe PessoaCliente")
class PessoaClienteTest {
  PessoaCliente person = new PessoaCliente("Maia", "000.000.000-00", "********");

  @Test
  @DisplayName("11 - Testa o construtor da classe Pessoa Cliente.")
  void construtorTest() {
    String result = person.getCpf();
    assertEquals("000.000.000-00", result);
  }

  @Test
  @DisplayName("12 - Testa o método adicionar conta e o método retornar número de contas.")
  void adicionarContaTestRetornaNumeroDeContasTest() {
    Banco bank = new Banco();
    Conta conta = new Conta("conta corrente", person, bank);
    person.adicionarConta(conta);
    int result = person.retornaNumeroDeContas();
    System.out.println(result);
    assertEquals(1, result);
  }

  @Test
  @DisplayName("13 - Testa o método retornar saldo de uma conta específica da pessoa cliente.")
  void retornarSaldoContaEspecificaTest() {
    Banco bank = new Banco();
    Conta conta = new Conta("conta corrente", person, bank);
    conta.adicionarTransacao(100, "test");
    person.adicionarConta(conta);
    double result = person.retornarSaldoContaEspecifica(0);
    assertEquals(100, result);
  }


  @Test
  @DisplayName("14 - Testa o método retornar id de uma conta específica da pessoa cliente.")
  void retornarIdContaEspecificaTest() {
    Banco bank = new Banco();
    Conta conta = new Conta("conta corrente", person, bank);
    conta.adicionarTransacao(100, "test");
    person.adicionarConta(conta);
    String id = person.retornarIdContaEspecifica(0);
    assertEquals(10, id.length());
  }

  @Test
  @DisplayName("15 - Testa o método retornar o extrato de uma conta específica da pessoa cliente.")
  void retornarExtratoContaEspecificaTest() {
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outStream));
    Banco bank = new Banco();
    Conta conta = new Conta("conta corrente", person, bank);
    conta.adicionarTransacao(100, "test");
    person.adicionarConta(conta);
    person.retornarExtratoContaEspecifica(0);
    String result = outStream.toString();
    assertTrue(result.contains("Extrato"));
  }

  @Test
  @DisplayName("16 - Testa o método adiciona transação de uma conta específica da pessoa cliente.")
  void adicionarTransacaoContaEspecificaTest() {
    Banco bank = new Banco();
    Conta conta = new Conta("conta corrente", person, bank);
    conta.adicionarTransacao(100, "test");
    person.adicionarConta(conta);
    person.adicionarTransacaoContaEspecifica(0, 100, "test");
    String result = conta.retornarResumoConta();
    String expected = "\\d{10} : R\\$200.00 : conta corrente";
    assertTrue(result.matches(expected));
  }

  @Test
  @DisplayName("17 - Testa o método validar senha.")
  void validarSenhaTest() {
    boolean resultValid = person.validarSenha("********");
    boolean resultInvalid = person.validarSenha("12345678");
    assertTrue(resultValid);
    assertFalse(resultInvalid);
  }

  @Test
  @DisplayName("18 - Testa o método retornar resumo contas.")
  void retornarResumoContasTest() {
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outStream));
    Banco bank = new Banco();
    Conta conta = new Conta("conta corrente", person, bank);
    conta.adicionarTransacao(100, "test");
    person.adicionarConta(conta);
    person.retornarResumoContas();
    String result = outStream.toString();
    String expected = "\\n\\nResumo das Contas da pessoa Maia:\\n\\n"
        + "1\\) \\d{10} : R\\$100.00 : conta corrente\\n\\n\\n";

    System.out.println(result);
    assertTrue(result.matches(expected));
  }

  @Test
  @DisplayName("19 - Testa o método Getter do atributo cpf está retornando.")
  void getCpfTest() {
    String result = person.getCpf();
    assertEquals("000.000.000-00", result);
  }

}
