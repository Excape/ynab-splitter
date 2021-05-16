export type UnapprovedTransaction = {
    id: string,
    date: Date,
    actors: string[]
    amount: number,
    categoryMap: [
        { actor: string, category: Category }
    ],
    memo: string,
    payee: string;
}

export type Transaction = {
    id: string,
    actor: Actor,
    date: Date,
    amount: number,
    category?: Category,
    memo: string,
    payee: string
}

export type Actor = {
    name: string
}

export type AuditLog = {
    id: string,
    date: Date,
    payee: string,
    executingActor: string
    transactions: TransactionsByActor[],
}

export type TransactionsByActor = {
    actor: string,
    oldTransaction: Transaction
    newTransaction: Transaction
}

export type Category = {
    id: string,
    name: string,
    group?: string,
    balance?: number
}
export type ApprovalFor = {
    actor?: string,
    splitApproval: boolean
}

export type ApprovalResult = {
    success: boolean,
    auditLog?: AuditLog
}

export type UndoApprovalResult = {
    success: boolean,
    message?: string
}

export type SplitTransactionRequest = {
    executingActor: string
    categories: CategoryRequest[]
    split: SplitRequest[]
}

export type CategoryRequest = {
    actor: string,
    categoryId: string
}

export type SplitRequest = {
    actor: string,
    split: number
}

export type UserSession = {
    actor: string
}
