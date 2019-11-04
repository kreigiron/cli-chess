# cli-chess

a Command Line Chess...

## Requirements 
- JDK 1.8
- gradle
- a Unix console which support terminal colors

## How to compile

$ cd ${project_root_path}
$ ./gradlew jar

## How to run
$ cd ${project_root_path}
$ java -jar build/libs/cli-chess-1.0-SNAPSHOT.jar

## How to play:

* Insert requested player names
* Insert an initial command to play for each player
 - Black player pieces are in uppercase
 - White player pieces are in lowercase
* enjoy

## Valid Commands
* board - show board 
* move [origin] to [destination] - move piece from origin to destination, both origin and destination should be in algebraic notation.
* stats - show current player stats 
* surrender - surrender current player

## What's missing?

Movement for pieces but pawns are currently not available.

(c) 2019 Erik Giron (kreig DOT iron AT gmail DOTcom) 