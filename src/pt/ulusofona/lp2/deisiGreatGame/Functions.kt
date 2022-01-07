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
        "PLAYERS_BY_LANGUAGE" -> manager.players.map{it.programmerFavLanList.contains(args[1])}.toString()
        "POLYGLOTS" -> manager.players.filter{it.name == args[1]}.map{it}.toString()
        "MOST_USED_POSITIONS" ->manager.players.filter{it.name == args[1]}.map{it}.toString()
        "MOST_USED_ABYSSES" -> manager.players.filter{it.name == args[1]}.map{it}.toString()
        else ->manager.players.filter{it.name == args[1]}.map{it}.toString()
    }
}

fun commandPost(manager: GameManager, args: List<String>): String?{
    return when (args[0]){
        "MOVE" -> manager.players.filter{it.name == args[1]}.map{it}.toString()
        "ABYSS" -> manager.players.filter{it.name == args[1]}.map{it}.toString()
        else -> manager.players.filter{it.name == args[1]}.map{it}.toString()
    }
}