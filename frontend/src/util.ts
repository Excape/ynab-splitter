
export const renderAmount = (amount: number) => {
    let rounded = Math.round(amount / 10) / 100;
    return rounded.toFixed(2);
}