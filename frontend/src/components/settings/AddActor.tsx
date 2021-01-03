import React, {useEffect} from 'react';
import {Budget, BudgetAccount, SaveActorResult} from './types';
import {Button, Dropdown, DropdownItemProps, Icon, Input, Label, Loader} from 'semantic-ui-react';
import Cookies from 'js-cookie';

const AddActor = () => {
    const [budgets, setBudgets] = React.useState([] as Budget[])
    const [budgetsLoaded, setBudgetsLoaded] = React.useState(false)
    const [selectedName, setSelectedName] = React.useState<string>()
    const [selectedBudget, setSelectedBudget] = React.useState<Budget>()
    const [budgetSelected, setBudgetSelected] = React.useState(false)
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

    function onSave(result: SaveActorResult) {
        setSaveResult(result)
        if (result.success) {
            setSelectedName(undefined)
            setSelectedBudget(undefined)
            setSelectedAccount(undefined)
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
        return budgetSelected && selectedName !== undefined && selectedAccount !== undefined;
    }

    function onBudgetSelect(budget: Budget) {
        setSelectedBudget(budget)
        setBudgetSelected(true)
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
        <div>
            <Input
                value={selectedName}
                onChange={(_, {value}) => setSelectedName(value)}
            />
            <BudgetDropdown budgets={budgets} onSelect={onBudgetSelect}/>
            <AccountDropdown enabled={budgetSelected} accounts={getAccounts()} onSelect={onAccountSelect}/>
            <Button as="div" labelPosition="right">
                <Button
                        icon="plus circle"
                        color="green"
                        disabled={!isSavable()}
                        onClick={() => save()}>
                    Add
                </Button>
                {saveResult !== undefined && (
                    <Label basic pointing="left" color={saveResult.success ? 'green' : 'red'}>
                        {saveResult.message}
                    </Label>
                )}
            </Button>
        </div>
    )
}

const BudgetDropdown = (props: { budgets: Budget[], onSelect: (budget: Budget) => void }) => {
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
        <Dropdown
            placeholder={"Select Budget"}
            fluid
            search
            selection
            options={createBudgetOptions()}
            onChange={(event, data) => onBudgetSelect(data.value)}
        />
    )
}

const AccountDropdown = (props: { enabled: boolean, accounts: BudgetAccount[] | undefined, onSelect: (account: BudgetAccount) => void }) => {
    function createAccountOptions() {
        return props.accounts?.map(account => (
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
        <Dropdown
            disabled={!props.enabled}
            placeholder={"Select Account"}
            fluid
            search
            selection
            options={createAccountOptions()}
            onChange={(event, data) => onAccountSelected(data.value)}
        />
    )
}

export default AddActor