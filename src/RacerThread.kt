import Racing.logRace

class RacerThread(
    name: String
) : Thread(name) {

    override fun run() {
        for (distance in START..FINISH) {
            if (distance == FINISH) {
                logRace(name, distance, true)
            } else {
                logRace(name, distance, false)
            }
        }
    }

    companion object {
        private const val START = 1
        private const val FINISH = 100
    }
}


