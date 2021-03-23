package world.cepi.level

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import world.cepi.level.ExperienceManager.levelFromExperience
import world.cepi.level.ExperienceManager.maxExperienceOf

class LevelTests {

    @Test
    fun `level equation should match with generation`() {

        val level = 6

        assertEquals(level, levelFromExperience(maxExperienceOf(level)))
    }
}