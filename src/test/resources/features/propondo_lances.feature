Feature: Propondo lances ao leilao

Scenario: Propondo um unico lance valido
Given Um lance valido
When Propoe o lance ao leilao
Then O lance eh aceito

Scenario: Propondo varios lances validos
Given Um lance de 10.0 reais do usuario "fulano"
And Um lance de 15.0 reais do usuario "beltrano"
When Propoe varios lances ao leilao
Then Os lances sao aceitos