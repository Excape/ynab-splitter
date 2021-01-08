import React, {useEffect} from 'react';
import {Budget, BudgetAccount, SaveActorResult, SettingsActor} from './types';
import {Button, DropdownItemProps, Form, Label, Loader} from 'semantic-ui-react';
import Cookies from 'js-cookie';

type Props = {
    onAddActor: (addedActor: SettingsActor) => void
}

const AddActor = (props: Props) => {
    const [budgets, setBudgets] = React.useState([] as Budget[])
    const [budgetsLoaded, setBudgetsLoaded] = React.useState(false)
    const [selectedName, setSelectedName] = React.useState<string>("")
    const [selectedBudget, setSelectedBudget] = React.useState<Budget>()
    const [selectedAccount, setSelectedAccount] = React.useState<BudgetAccount>()
    const [saveResult, setSaveResult] = React.useState<SaveActorResult>()

    useEffect(() => {
        fetch("/api/v1/user/budgets")
            .then(result => result.json())
            .then(budgets => {
                setBudgets(budgets)
                setBudgetsLoaded(true)
            })
    }, [])

    function resetForm() {
        setSelectedName("")
        setSelectedBudget(undefined)
        setSelectedAccount(undefined)
    }

    function onSave(result: SaveActorResult) {
        setSaveResult(result)
        if (result.success) {
            resetForm();
            props.onAddActor(result.actor!)
        }
    }

    function save() {
        const request = {
            actorName: selectedName,
            budgetId: selectedBudget!.budgetId,
            accountId: selectedAccount!.accountId
        }

        fetch("/api/v1/user/addActor", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': Cookies.get("XSRF-TOKEN") ?? 'NULL'
            },
            body: JSON.stringify(request)
        })
            .then(result => result.json())
            .then(result => {
                onSave(result)
            })
    }

    function isSavable() {
        return selectedBudget !== undefined && selectedName !== undefined
            && selectedAccount !== undefined;
    }

    function onBudgetSelect(budget: Budget) {
        setSelectedBudget(budget)
    }

    function onAccountSelect(account: BudgetAccount) {
        setSelectedAccount(account)
    }

    function getAccounts(): BudgetAccount[] | undefined {
        return selectedBudget?.accounts
    }

    if (!budgetsLoaded) {
        return (<Loader active inline='centered'/>)
    }

    return (
        <Form>
            <Form.Group widths="equal">
                <Form.Input
                    placeholder="Choose a name"
                    value={selectedName}
                    onChange={(_, {value}) => setSelectedName(value)}
                />
                <BudgetDropdown budgets={budgets} value={selectedBudget} onSelect={onBudgetSelect}/>
                <AccountDropdown
                    enabled={selectedBudget !== undefined}
                    value={selectedAccount}
                    accounts={getAccounts()}
                    onSelect={onAccountSelect}/>
            </Form.Group>
                <Button as="div" labelPosition="right">
                    <Button
                        icon="plus circle"
                        content="Add"
                        color="green"
                        disabled={!isSavable()}
                        onClick={() => save()}>
                    </Button>
                    {saveResult !== undefined && (
                        <Label basic pointing="left" color={saveResult.success ? 'green' : 'red'}>
                            {saveResult.message}
                        </Label>
                    )}
                </Button>
        </Form>
    )
}

const BudgetDropdown = (props: { budgets: Budget[], value: Budget | undefined, onSelect: (budget: Budget) => void }) => {
    function createBudgetOptions(): DropdownItemProps[] {
        return props.budgets.map(budget => (
                {
                    value: budget.budgetId,
                    key: budget.budgetId,
                    text: budget.name
                }
            )
        );
    }

    function onBudgetSelect(value: any) {
        if (value !== undefined) {
            props.onSelect(props.budgets.find(budget => budget.budgetId === value)!)
        }
    }

    return (
        <Form.Dropdown
            placeholder={"Select Budget"}
            value={props.value?.budgetId ?? ""}
            fluid
            search
            selection
            options={createBudgetOptions()}
            onChange={(event, data) => onBudgetSelect(data.value)}
        />
    )
}

type AccountDropdownProps = {
    enabled: boolean;
    value: BudgetAccount | undefined;
    accounts: BudgetAccount[] | undefined;
    onSelect: (account: BudgetAccount) => void
}

const AccountDropdown = (props: AccountDropdownProps) => {
    function createAccountOptions() {
        if (props.accounts === undefined) {
            return []
        }
        return props.accounts.map(account => (
                {
                    value: account.accountId,
                    key: account.accountId,
                    text: account.name
                }
            )
        );
    }

    function onAccountSelected(value: any) {
        if (value !== undefined) {
            props.onSelect(props.accounts?.find(account => account.accountId === value)!)
        }
    }

    return (
        <Form.Dropdown
            disabled={!props.enabled}
            placeholder={"Select Account"}
            value={props.value?.accountId ?? ""}
            fluid
            search
            selection
            options={createAccountOptions()}
            onChange={(event, data) => onAccountSelected(data.value)}
        />
    )
}

export default AddActor