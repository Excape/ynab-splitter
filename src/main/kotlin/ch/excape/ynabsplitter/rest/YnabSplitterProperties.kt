package ch.excape.ynabsplitter.rest

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("ynabsplitter")
data class YnabSplitterProperties(
        val dryRun: Boolean,
        val host: String,
        val notifications: NotificationProperties
) {
    data class NotificationProperties(
            val publicKey: String,
            var privateKey: String
    )
}


