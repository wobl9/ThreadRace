fun main() {
    val michaelSchumacher = RacerThread("Поток А")
    val ayrtonSenna = RacerThread("Поток Б")
    Racing.startRace(michaelSchumacher, ayrtonSenna)
}