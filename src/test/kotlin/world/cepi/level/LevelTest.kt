package world.cepi.level

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import world.cepi.level.ExperienceManager.nextLevelFromExperience
import world.cepi.level.ExperienceManager.experienceRequiredFor
import kotlin.random.Random

class LevelTest {

    companion object {
        /**
         * Max number the RNG can generate.
         */
        const val maxLevel = 20
    }

    /**
     * [ExperienceManager.nextLevelFromExperience] returns the next level this XP needs to complete.
     *
     * If none are removed, it should return the next level as that's the next milestone
     */
    @RepeatedTest(5)
    fun `levelFromExperience should return high if equal to requirement`() {

        val level = Random.nextInt(maxLevel)

        // If the XP is the same, it should return the next level.
        assertEquals(level + 1, nextLevelFromExperience(experienceRequiredFor(level)))
    }

    /**
     * [ExperienceManager.nextLevelFromExperience] returns the next level this XP needs to complete.
     *
     * If one is added, it should return the next level as that's the next milestone
     */
    @RepeatedTest(5)
    fun `levelFromExperience should return high if greater than requirement`() {

        val level = Random.nextInt(maxLevel)

        // If the XP is slightly higher than required, it should return the next level.
        assertEquals(level + 1, nextLevelFromExperience(experienceRequiredFor(level) + 1))
    }

    /**
     * [ExperienceManager.nextLevelFromExperience] returns the next level this XP needs to complete.
     *
     * If one is removed, it should return the level.
     */
    @RepeatedTest(5)
    fun `levelFromExperience should return same if less than requirement`() {

        val level = Random.nextInt(maxLevel)

        // If the XP is slightly lower than required, it should return the last level.
        assertEquals(level, nextLevelFromExperience(experienceRequiredFor(level)) - 1)
    }
}