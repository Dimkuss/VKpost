
object WallServiceMain {
    val service = WallService()
}

fun main() {
    WallServiceMain.service.add(Post(text = "Это что то") )
    WallServiceMain.service.add(Post(text = "Дальше вот это вот"))
    WallServiceMain.service.update(Post(id = 2, text = "И это вот"))
    WallServiceMain.service.add(Post(text = "Хелло"))

}