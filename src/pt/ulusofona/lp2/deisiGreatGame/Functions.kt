package pt.ulusofona.lp2.deisiGreatGame

enum class CommandType { POST, GET }

fun router() : Function1<CommandType,Function2<GameManager,List<String>,String?>>{
    return ::command
}

fun command(commandType:CommandType) : Function2<GameManager,List<String>,String?>{
    return when (commandType){
        CommandType.POST -> ::commandPost
        CommandType.GET -> ::commandGet
    }
}

fun commandGet(manager: GameManager, args: List<String>): String?{
    return when (args[0]){
        "PLAYER" -> manager.players.filter{it.name.contains(args[1])}.take(1).toString()
        "PLAYERS_BY_LANGUAGE" -> manager.players.filter{it.programmerFavLan.contains(args[1])}.joinToString {it.name}
        "POLYGLOTS" -> manager.players.filter{it.programmerFavLanList.size > 1}.sortedWith(Comparator<Programmer>{ a, b ->
            when {
                a.programmerFavLanList.size > b.programmerFavLanList.size -> 1
                a.programmerFavLanList.size < b.programmerFavLanList.size -> -1
                else -> 0
            }
        }).joinToString { it.name +":"+ it.programmerFavLanList.size }
        "MOST_USED_POSITIONS" ->manager.positions.sortedWith(Comparator<Position>{ a, b ->
            when {
                a.position < b.position -> 1
                a.position > b.position -> -1
                else -> 0
            }
        }).take(Integer.parseInt(args[1])).joinToString { "${manager.positions.indexOf(it)}:${it.position}\n" }.replace(",","").trim()
        "MOST_USED_ABYSSES" -> manager.abyssesPositions.sortedWith(Comparator<Position>{ a, b ->
            when {
                a.position < b.position -> 1
                a.position > b.position -> -1
                else -> 0
            }
        }).take(Integer.parseInt(args[1])).joinToString { "${it.abyss}:${it.position}\n" }.replace(",","").trim()
        else -> ""
    }
}

fun commandPost(manager: GameManager, args: List<String>): String?{
    return when (args[0]){
        "MOVE" -> teste(manager,args)
        "ABYSS" -> manager.players.filter{it.name == args[1]}.map{it}.toString()
        else -> ""
    }
}
fun teste(manager: GameManager, args: List<String>):String?{
    var answer: String? = "ok"
    manager.moveCurrentPlayer(Integer.parseInt(args[1]))
    answer = manager.reactToAbyssOrTool()
    if(answer == null){
        return "ok"
    }
    return answer
}