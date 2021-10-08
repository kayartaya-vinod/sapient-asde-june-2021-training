// use of spread operator in arrays

const people = require('./data');

const names = people.map((person) => person.name);

// both names and newNames are two references to the same array object
// const newNames = names;

// [] means new array, ...names means spread the members of names
const newNames = [...names]; // shallow copy

console.log('names===newNames is', names === newNames);
console.log('names.length is', names.length);
console.log('newNames.length is', newNames.length);

names.push('Vinod', 'Kumar');
console.log('after pushing 2 entries to "names"...');
console.log('names.length is', names.length);
console.log('newNames.length is', newNames.length);

const newPeople = [...people]; // shallow copy

console.log(newPeople[0]);
people[0].email = people[0].email.toUpperCase();
console.log(newPeople[0]);

// to do a deep copy, use the following technique
const peopleCopy = JSON.parse(JSON.stringify(people));
console.log(peopleCopy[0]);
people[0].name = people[0].name.toUpperCase();
console.log(people[0]);
console.log(peopleCopy[0]);
