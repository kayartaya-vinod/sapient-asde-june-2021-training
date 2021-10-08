const data = require('./data');

console.log('There are %d objects in data', data.length);

// first two elements from the array 'data'
// let p1 = data[0];
// let p2 = data[1];

// array destructuring
let [p1, p2, p3, ...smallData] = data;
// p1 --> first element from data
// p2 --> second element from data
// p3 --> third element from data
// smallData --> array consisting of remaining 7 elements from data

// let name = p3.name;
// let email = p3.email;
// let gender = p3.gender;
// let phone = p3.phone;

// object destructuring
let { name, email, gender, phone } = p3;

console.log('p1 is', p1);
console.log('p2 is', p2);
console.log(
    'from p3, name=%s, email=%s, gender=%s, phone=%s',
    name,
    email,
    gender,
    phone
);

console.log(
    'For p1, name=%s, email=%s, gender=%s',
    p1.name,
    p1.email,
    p1['gender']
);

console.log('smallData is', smallData);

module.exports = data;
