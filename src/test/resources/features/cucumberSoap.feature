#language: pt
  Funcionalidade: Testar api com SOAP

    @CT001
    Cenario: Number to words
      Dado enviar a requisicao
      Entao valido o status da minha resposta

    @CT002
    Cenario: Number to dollars
      Dado enviar a requisicao dollars
      Entao valido o status da minha resposta