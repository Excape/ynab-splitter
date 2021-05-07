import React from "react";
import Slider from '@material-ui/core/Slider';
import {Button, Input, InputProps} from 'semantic-ui-react';

type Props = {
    amountPositive: number;
    onApprove: (splitValueLeft: number) => void;
    actorNames: string[];
}

const CustomSplitApproval = (props: Props) => {
    const [splitValue, setSplitValue] = React.useState(50)
    const [amountLeft, setAmountLeft] = React.useState(calculateSplitAmount(50))
    const [amountRight, setAmountRight] = React.useState(calculateSplitAmount(50))

    function updateSplitValueFromSlider(value: number) {
        setSplitValue(value);
        setAmountLeft(calculateSplitAmount(value))
        setAmountRight(calculateSplitAmount(100 - value));
    }

    function calculateSplitAmount(splitValue: number): number {
        let share = props.amountPositive * (splitValue / 100);
        let roundedShare = Math.round(share / 10) * 10
        return roundedShare / 1000;
    }

    function calculateReverseSplitAmount(amount: number): number {
        let share = (amount * 1000) / props.amountPositive
        return Math.round(share * 100);
    }

    function handleApprove() {
        props.onApprove(splitValue / 100)
    }

    function onUpdateSplitValueLeft(event: React.ChangeEvent<HTMLInputElement>) {
        const amount = Number(event.target.value);
        if (amountIsValid(amount)) {
            const splitPercentage = calculateReverseSplitAmount(amount);
            setAmountLeft(amount)
            setSplitValue(splitPercentage)
            setAmountRight(calculateSplitAmount(100 - splitPercentage))
        }
    }

    function onUpdateSplitValueRight(event: React.ChangeEvent<HTMLInputElement>) {
        const amount = Number(event.target.value);
        if (amountIsValid(amount)) {
            const splitPercentage = calculateReverseSplitAmount(amount);
            setAmountRight(amount)
            setSplitValue(splitPercentage)
            setAmountLeft(calculateSplitAmount(100 - splitPercentage))
        }
    }

    function amountIsValid(amount: number) {
        return amount <= props.amountPositive / 1000
    }

    const splitInputProps: InputProps = {
        type: 'number',
        fluid: true,
        icon: 'dollar',
        iconPosition: 'left',
        step: 0.05
    }

    return (
        <div>
            <div className={"slider"}>
                <Slider
                    color={"primary"}
                    value={splitValue}
                    step={5}
                    marks
                    min={0}
                    max={100}
                    valueLabelDisplay={'on'}
                    valueLabelFormat={(n) => `${n}%`}
                    onChange={(_, value) => updateSplitValueFromSlider(value as number)}
                />
            </div>
            <div className="splitLabel">
                <div className='splitInput'>
                    <Input
                        {...splitInputProps}
                        value={amountLeft}
                        onChange={onUpdateSplitValueLeft}
                    />

                </div>
                <div className='splitInput right'>
                    <Input
                        {...splitInputProps}
                        value={amountRight}
                        onChange={onUpdateSplitValueRight}
                    />
                </div>
            </div>
            <div className="splitLabel">
                {props.actorNames.map(actorName =>
                    <div key={actorName}>{actorName}</div>)}
            </div>
            <div className={'approveBtn'}>
                <Button color={"green"}
                        content={"Approve"}
                        onClick={() => handleApprove()}
                />
            </div>
        </div>
    );
};

export default CustomSplitApproval