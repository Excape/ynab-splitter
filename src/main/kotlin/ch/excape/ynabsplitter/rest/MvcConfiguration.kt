package ch.excape.ynabsplitter.rest

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class MvcConfiguration : WebMvcConfigurer {
    /**
     * Forward everything but /api to index.html
     * This is to support react router
     */
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/{spring:\\w+}")
                .setViewName("forward:/")
    }
}