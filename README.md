# DeisiGreatGame
![](diagrama.png?raw=true "Diagrama UML")

Ficamos um pouco em duvida porque utilizamos o collectionSort para ordenar as posições dos jogagores.
Pensamos em fazer uma lista ligada circular mas acabamos por utilizar o proprio array onde guardamos os jogadores para fazer a ordem pois a complexidade é menor e gastamos menos memoria,  (currentPlayer + 1) % numberOfPlayers com esta formula quando current player +1 for igual a number of Players o resto vai dar 0 e volta ao inicio da lista
