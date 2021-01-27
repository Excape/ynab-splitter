export type UserSettings = {
    actors: SettingsActor[]
}

export type SettingsActor = {
    actorName: string,
    budgetName: string,
    accountName: string
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
    message: string,
    actor: SettingsActor | undefined
}