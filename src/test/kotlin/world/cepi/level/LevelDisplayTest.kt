package world.cepi.level

import org.junit.jupiter.api.RepeatedTest
import kotlin.random.Random

class LevelDisplayTest {

    @RepeatedTest(5)
    fun `Display XP should be between 0 and 1`() {
        assert((0f..1f).contains(LevelDisplay.from(Random.nextInt(200)).displayXP))
    }

}