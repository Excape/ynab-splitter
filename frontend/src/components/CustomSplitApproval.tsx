import React from "react";
import Slider from '@material-ui/core/Slider';
import {Button, Icon} from 'semantic-ui-react';

type Props = {
    amount: number;
    onApprove: (splitValueRobin: number) => void;
}

const CustomSplitApproval = (props: Props) => {
    const [splitValue, setSplitValue] = React.useState(50)

    function updateSplitValue(value: number | number[]) {
        if (typeof value === 'number') {
            setSplitValue(value as number)
        }
    }

    function calculateSplitAmount(splitValue: number): string {
        let share = props.amount * (splitValue / 100);
        let roundedShare = Math.round(share/10) * 10
        return `${roundedShare / 1000}`
    }

    function handleApprove() {
        props.onApprove(splitValue / 100)
    }

    return (
        <div>
            <Slider
                color={"secondary"}
                defaultValue={50}
                step={5}
                marks
                min={0}
                max={100}
                valueLabelDisplay={'auto'}
                valueLabelFormat={(n) => `${n}%`}
                onChange={(_, value) => updateSplitValue(value)}
            />
            <div className="splitLabel">
                <div>
                    <Icon name="dollar sign"/>
                    {calculateSplitAmount(splitValue)}
                </div>
                <div>
                    <Icon name="dollar sign"/>
                    {calculateSplitAmount(100 - splitValue)}
                </div>
            </div>
            <div className="splitLabel">
                <div>Robin</div>
                <div>Sophie</div>
            </div>
            <div>
                <Button color={"green"}
                        content={"Approve"}
                        onClick={() => handleApprove()}
                />
            </div>
        </div>
    );
};

export default CustomSplitApproval