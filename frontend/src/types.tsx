export type UnapprovedTransaction = {
    id: string,
    date: Date,
    amount: number,
    categoryMap: [
        { actor: string, category: string }
    ],
    memo: string,
    payee: string;
}

export enum ApprovalOption {Undefined, Robin = "Robin", Sophie = "Sophie", Split = "Split"}