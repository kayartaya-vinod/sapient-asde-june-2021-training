// object destructuring can be used while importing a module's members
const { add, subtract, multiply } = require('./calculator');

let num1 = 123,
    num2 = 39;

console.log(add(num1, num2));
console.log(subtract(num1, num2));
console.log(multiply(num1, num2));
