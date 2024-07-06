package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes para a classe Banco")
class BancoTest {
  Banco bank = new Banco();

  @Test
  @DisplayName("20 - Testa o gerador de número único para nova conta.")
  void gerarNumeroNovaContaTest() {
    String conta = bank.gerarNumeroNovaConta();
    assertEquals(10, conta.length());
  }

  @Test
  @DisplayName("21 - Testa o método adicionar pessoa cliente retorna o objeto pessoa cliente.")
  void adicionarPessoaClienteTest() {
    PessoaCliente result = bank.adicionarPessoaCliente("Maia", "000.000.000-00", "********");
    assertEquals(PessoaCliente.class, result.getClass());

  }

  @Test
  @DisplayName("22 - Testa o método login da pessoa cliente retorna o objeto pessoa cliente corretamente.")
  void pessoaClienteLoginTest() {
    bank.adicionarPessoaCliente("Maia", "000.000.000-00", "********");
    PessoaCliente person = bank.pessoaClienteLogin("000.000.000-00", "********");
    assertEquals(PessoaCliente.class, person.getClass());
    person = bank.pessoaClienteLogin("00000000000", "********");
    assertNull(person);
    person = bank.pessoaClienteLogin("000.000.000-00", "12345678");
    assertNull(person);
  }

  @Test
  @DisplayName("23 - Testa se o método transferir fundos está transferindo corretamente.")
  void depositarTestTransferirFundosTestmostrarExtratoTest() {
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outStream));
    PessoaCliente person = bank.adicionarPessoaCliente("Maia", "000.000.000-00", "********");
    Conta contaCorrente = new Conta("conta corrente", person, bank);
    Conta contaPoupanca = new Conta("conta poupança", person, bank);
    person.adicionarConta(contaPoupanca);
    person.adicionarConta(contaCorrente);
    person.adicionarTransacaoContaEspecifica(0, 200, "test");
    bank.transferirFundos(person, 0, 1, 100);
    person.retornarResumoContas();
    String result = outStream.toString();
    String expected = "Nova pessoa cliente Maia com CPF: 000.000.000-00 criada!\\n\\n\\n\\n"
        + "Resumo das Contas da pessoa Maia:\\n\\n"
        + "1\\) \\d{10} : R\\$100.00 : conta poupança\\n\\n"
        + "2\\) \\d{10} : R\\$100.00 : conta corrente\\n\\n\\n";
    assertTrue(result.matches(expected));
  }

  @Test
  @DisplayName("24 - Testa se o método sacar está funcionando corretamente.")
  void depositarTestSacarTestMostrarExtratoTest() {
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outStream));
    PessoaCliente person = bank.adicionarPessoaCliente("Maia", "000.000.000-00", "********");
    Conta contaPoupanca = new Conta("conta poupança", person, bank);
    person.adicionarConta(contaPoupanca);
    bank.depositar(person, 0, 200);
    double result = person.retornarSaldoContaEspecifica(0);
    double expected = 200;
    assertEquals(expected, result);
    bank.sacar(person, 0, 50);
    result = person.retornarSaldoContaEspecifica(0);
    expected = 150;
    assertEquals(expected, result);
  }

}
