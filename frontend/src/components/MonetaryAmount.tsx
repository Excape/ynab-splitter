import React from 'react';

type Props = {
    amount: number
}

const displayStyleNegative = {
    color: "#ca481d"
}

const displayStylePositive = {
    color: "#016936"
}

const displayStyleNeutral = {
    color: "#A0A0A0"
}

const MonetaryAmount = ({amount}: Props) => {
    const formatAmount = () => {
        let rounded = Math.round(amount / 10) / 100;
        return rounded.toFixed(2);
    }


    function getStyle() {
        if (amount < 0) {
            return displayStyleNegative
        } else if (amount > 0) {
            return displayStylePositive
        } else {
            return displayStyleNeutral
        }
    }

    return (
        <span style={getStyle()}>
            {formatAmount()}
        </span>
    )
}

export default MonetaryAmount;