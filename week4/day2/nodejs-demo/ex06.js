const people = require('./data');

const names = people.map((person) => person.name);
const emails = people.map((p) => p.email);

console.log('names is', names);
console.log('emails is', emails);

// array.map --> convert individual elements into some other kind of values
// array.filter --> pick few from the entire array
// array.find --> find a matching element
// array.findIndex --> find the index of a matching element

// the call back to all of the above functions receive 3 parameters:
// 1. one element at a time
// 2. loop index
// 3. reference to the entire array
// In most cases, we are happy with the first parameter
