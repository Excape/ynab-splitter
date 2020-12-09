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
    category: Category | undefined,
    memo: string,
    payee: string
}

export type Actor = {
    name: string
}

export type AuditLog = {
    date: Date,
    oldTransaction: Transaction,
    newTransaction: Transaction,
    executingActor: string
}

export type Category = {
    id: string,
    name: string,
    group?: string,
    balance?: number
}
export type ApprovalFor = {
    actor: string | undefined,
    splitApproval: boolean
}

export type ApprovalResult = {success: boolean}

export type SplitTransactionRequest = {
    executingActor: string
    categories: CategoryRequest[]
    split: SplitRequest[]
}

export type CategoryRequest = {
    actor: String,
    categoryId: String
}

export type SplitRequest = {
    actor: String,
    split: number
}

export type UserSession = {
    actor: string
}
