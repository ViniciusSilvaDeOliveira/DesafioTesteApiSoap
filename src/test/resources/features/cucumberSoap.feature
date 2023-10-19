#language: pt
  Funcionalidade: Testar api com SOAP

    @CT001
    Cenario: Number to dollars
      Dado enviar a requisicao
      Entao valido o status da minha resposta

    @CT002
    Cenario: Number to words
      Dado enviar a requisicao words
      Entao valido a resposta da requisicao

    @CT003
    Cenario: Add
      Dado enviar a requisicao add
      Entao valido a resposta da requisicao add

    @CT004
    Cenario: Divide
      Dado enviar a requisicao divide
      Entao valido a resposta da requisicao divide

    @CT005
    Cenario: Multiply
      Dado enviar a requisicao multiply
      Entao valido a resposta da requisicao multiply

    @CT006
    Cenario: Subtract
      Dado enviar a requisicao Subtract
      Entao valido a resposta da requisicao Subtract

    @CT007
    Cenario: List of Continents by Name
      Dado enviar a requisicao continents
      Entao valido a resposta da requisicao continents