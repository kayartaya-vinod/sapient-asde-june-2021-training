let nums = [12, 34, 59, 39, 190, 38, 598, 383, 223, 484];

let searchNum = 598;
let index = nums.findIndex((n) => n === searchNum);
console.log('index of', searchNum, 'is', index);

searchNum = 5981;
index = nums.findIndex((n) => n === searchNum);
console.log('index of', searchNum, 'is', index);

let evenNums = nums.filter((n) => n % 2 === 0);
console.log('even numbers are', evenNums);

let oddNums = nums.filter((n) => n % 2);
console.log('odd numbers are', oddNums);
