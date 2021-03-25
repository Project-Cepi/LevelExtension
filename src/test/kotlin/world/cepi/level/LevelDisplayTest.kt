package world.cepi.level

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import kotlin.random.Random

class LevelDisplayTest {

    @Test
    fun `Display XP should be between 0 and 1 when 0`() {
        assert((0f..1f).contains(LevelDisplay.from(0).displayXP))
    }

    @RepeatedTest(5)
    fun `Display XP should be between 0 and 1`() {
        assert((0f..1f).contains(LevelDisplay.from(Random.nextInt(1, 200)).displayXP))
    }

}