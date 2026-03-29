@leilao
Feature: Cadastrando um leilao
  Scenario: Um usuario logado cadastrar um leilao
    Given o usuario logado
    When acessa a pagina de novo leilao
    And preeche o formulario com dados validos
    Then volta para a pagina de leiloes
    And o novo leilao aparece na tabela