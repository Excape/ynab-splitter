package ch.excape.ynabsplitter.adapter.ynab

import ch.excape.ynabsplitter.domain.Actor
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class YnabCategoriesRepositoryTest(
        @Autowired
        private val testee: YnabCategoriesRepository
) {

    @Test
    fun getCategories() {
        val categories = testee.getCategories(Actor.ROBIN)
        println(categories)
        assertThat(categories).isNotEmpty
    }
}