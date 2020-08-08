export type UnapprovedTransaction = {
    id: string,
    date: Date,
    amount: number,
    categoryMap: [
        { actor: string, category: Category }
    ],
    memo: string,
    payee: string;
}

export type Transaction = {
    id: string,
    actor: string,
    date: Date,
    amount: number,
    category: Category | undefined,
    memo: string,
    payee: string
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

export enum ApprovalOption {Undefined, Robin = "Robin", Sophie = "Sophie", Split = "Split"}

export type ApprovalResult = {success: boolean}

export type SplitTransactionRequest = {
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
