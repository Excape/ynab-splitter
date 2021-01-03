export type UserSettings = {
    actors: SettingsActor[]
}

type SettingsActor = {
    name: string,
    budgetId: string,
    accountId: string
}

export type Budget = {
    name: string,
    budgetId: string,
    accounts: BudgetAccount[]
}

export type BudgetAccount = {
    name: string,
    accountId: string
}

export type SaveActorResult = {
    success: boolean,
    message: string
}