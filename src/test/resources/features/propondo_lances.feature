Feature: Propondo lances ao leilao

Scenario: Propondo um unico lance valido
Given Dado um lance valido
When Quando propoe o lance ao leilao
Then Entao o lance eh aceito

Scenario: Propondo varios lances validos
Given Dado varios lances validos
When Quando propoe varios lances ao leilao
Then Entao os lances sao aceitos