Feature: Propondo lances

Scenario: Propondo um unico lance válido
Given Dado um lance valido
When Quando propoe o lance ao leilao
Then Entao o lance eh aceito

Scenario: Propondo vários lances válidos
Given Dado vários lances válidos
When Quando propoe varios lances ao leilao
Then Entao os lances sao aceitos