package ch.excape.ynabsplitter.application.use_cases.usermanagement.get_user.ports

data class GetUserInput(
        /**
         * id from YNAB
         */
        val userId: String
)