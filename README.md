# Desafio Busca - LuizaLabs

#### Requisitos
 `Para a rotina de build é necessário ter instalado Maven na versão 3.6+ e JDK 1.8+.`
#### Sobre
Após os comandos serem executados irá gerar dois arquivos. Um referente ao processo de pré-processamento dos arquivos (que deverá ser executado primeiro), e o segundo que irá efetuar a busca.
A indexação irá criar um diretório `data` de acordo com o contexto passado.

### Build e Execução:
```
$ mvn clean package install
$ cd target/
$ java -jar indexer.jar /example/path
$ java -jar search.jar /example/path "roberts julia"
Foram encontradas 2 ocorrências pelo termo roberts julia
Os arquivos que possuem "roberts julia" são:
larry-crowne.txt
satisfaction.txt
```
