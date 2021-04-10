package world.cepi.level

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random

class LevelDisplayTest {

    @Test
    fun `Displayed XP should be 0 when experience is 0`() {
        assertEquals(0f, LevelDisplay.from(0).displayXP)
    }

    @Test
    fun `Displayed XP should be 0 when experience is level requirement`() {
        assertEquals(0f, LevelDisplay.from(ExperienceManager.experienceRequiredFor(1)).displayXP)
    }

    @Test
    fun `Display XP should be above 0 and below or equal to 1 when experience is greater than 0`() {

        assert(LevelDisplay.from(1).displayXP > 0)

        assert(LevelDisplay.from(1).displayXP <= 1)
    }

}