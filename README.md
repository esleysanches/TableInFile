# TesteTQI
Projeto simples que tem como objetivo responder as questões do PDF "Avaliação.pdf".

O projeto está em JAVA desenolvido em eclipse no sistema operacional UBUNTU 16, separado corretamente através de pacotes. Os testes unitários foram executados com JUnit4


As primeiras perguntas que exigem testes em bash seguem abaixo:


    Para o arquivo “Input” utilize comandos bash para:
    a) Identificar o usuário que tem o maior “size”;
    b) Ordenar o “username” em ordem alfabética.



    A) Vou mostrar o comando abaixo utilizado em seguida vou explicar
    RESP: sort -t' ' -k5 input | tail -1
    EXPLICAÇÃO: 

    sort 
    Comando no linux utilizado para ordenar dados a partir de um arquivo;

    -t' '
    Parametro que informa qual caracter será utilizado como dlimitador de coluna, nesse caso apenas um espaço;

    -k5
    Parametro que pede qual coluna será selecionada, no caso a 5;

    input
    Nome do arquivo que contém os registros;

    |
    Pipe, é utilizado para pegar a saida do comando anterior e enviar ao proximo;

    tail
    Mostra as últimas 10 linhas de um arquivo;

    -1 
    QUantas linhas quero visualizar (Nese caso apenas a ultima).

    

    B) Vou mostrar o comando abaixo utilizado em seguida vou explicar
    RESP: sort input -t' ' -k1 input
    EXPLICAÇÃO: Quase identico ao comando anterior, mudando apenas a coluna. Poderia ser utilizado somente o "sort input" (Sem aspas).


