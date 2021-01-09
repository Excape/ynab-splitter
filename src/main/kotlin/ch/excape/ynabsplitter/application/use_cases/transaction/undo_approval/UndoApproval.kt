package ch.excape.ynabsplitter.application.use_cases.transaction.undo_approval

import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.UndoApprovalResult
import ch.excape.ynabsplitter.application.outbound_ports.presentation.UndoneApprovalPresenter
import ch.excape.ynabsplitter.application.outbound_ports.ynab.SaveTransactionRepository
import ch.excape.ynabsplitter.application.use_cases.transaction.undo_approval.ports.IUndoApproval
import ch.excape.ynabsplitter.application.use_cases.transaction.undo_approval.ports.UndoApprovalInput
import ch.excape.ynabsplitter.domain.AuditLog
import java.lang.IllegalArgumentException

class UndoApproval(
        private val auditLogRepository: AuditLogRepository,
        private val saveTransactionRepository: SaveTransactionRepository
) : IUndoApproval {
    override fun executeWith(input: UndoApprovalInput, presenter: UndoneApprovalPresenter) {
        try {
            val auditLog = fetchAuditLog(input.auditLogId)
            checkAuditLogBelongsToUser(auditLog, input.userId)
            undoApproval(auditLog)
            removeAuditLog(auditLog)
            presenter.present(UndoApprovalResult.success())
        } catch (ex: Exception) {
            println("Exception while undoing approval: $ex")
            presenter.present(UndoApprovalResult.failure(ex.message))
        }
    }

    private fun undoApproval(auditLog: AuditLog) {
        auditLog.oldTransactions.forEach {
            saveTransactionRepository.saveTransaction(it)
        }
    }

    private fun removeAuditLog(auditLog: AuditLog) {
        auditLogRepository.delete(auditLog.id)
    }

    private fun fetchAuditLog(auditLogId: String): AuditLog =
            auditLogRepository.getById(auditLogId)
                    ?: throw IllegalArgumentException("Audit log with id $auditLogId not found")

    private fun checkAuditLogBelongsToUser(auditLog: AuditLog, userId: String) {
        if (auditLog.userId != userId) {
            throw IllegalAccessException("User is not allowed to undo a transaction of another user")
        }
    }
}