import java.io.File
import java.io.FileWriter

object Racing {

    private const val START = "Старт"
    private const val WIN = "Победил"
    private const val LOOSE = "Проиграл"
    private const val FILE_NAME = "WorldRacingCup.txt"

    private val fileForLogs
        get() = createFile(FILE_NAME)

    private val logger = FileWriter(fileForLogs)

    private var inRace = 0
    private var allParticipants = 0

    fun startRace(vararg threads: Thread) {
        logger.write("$START\n")
        threads.forEach { thread ->
            allParticipants++
            inRace++
            thread.start()
        }
    }

    @Synchronized
    fun logRace(name: String, distance: Int, finished: Boolean) {
        if (finished) {
            log(distanceCounter(name, distance))
            if (inRace == allParticipants) {
                log(winnerPhrase(name))
            } else {
                log(looserPhrase(name))
            }
            finishRace()
        } else {
            log(distanceCounter(name, distance))
        }
    }

    @Suppress("SameParameterValue")
    private fun createFile(filePath: String) = File(filePath).run {
        if (exists().not()) {
            createNewFile()
            this
        } else {
            this
        }
    }

    private fun log(text: String) = logger.write(text)
    private fun distanceCounter(name: String, distance: Int): String = "$name $distance\n"
    private fun winnerPhrase(name: String): String = "$name $WIN\n"
    private fun looserPhrase(name: String): String = "$name $LOOSE\n"

    private fun finishRace() {
        inRace--
        if (inRace == 0) {
            logger.close()
        }
    }
}
