export const getColor = (i: number): "orange" | "teal" | "purple" | "red" => {
    const colors = ["orange", "teal", "purple", "red"];

    return colors[i % (colors.length - 1)] as "orange" | "teal" | "purple" | "red"
};