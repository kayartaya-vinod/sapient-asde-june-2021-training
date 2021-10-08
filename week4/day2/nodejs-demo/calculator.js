module.exports = {};

// adding a new member to the empty {}
module.exports.add = (num1, num2) => {
    num1 = parseFloat(num1);
    num2 = parseFloat(num2);
    return num1 + num2;
};

module.exports.subtract = (num1, num2) => {
    return num1 - num2;
};

module.exports.multiply = (num1, num2) => {
    return num1 * num2;
};

module.exports.divide = (num1, num2) => {
    return num1 / num2;
};
