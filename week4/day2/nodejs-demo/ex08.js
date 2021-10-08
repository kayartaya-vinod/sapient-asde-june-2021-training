// using spread operator with object
const p1 = {
    name: 'Vinod',
    email: 'vinod@vinod.co',
    city: 'Bangalore',
    phones: ['9731424784', '9844083934'],
};

const p2 = p1; // another reference to the same object referred by p1

console.log('p1===p2 is', p1 === p2);

const p3 = { ...p1 }; // a shallow copy; adding a new phone to p1 will reflect in p3
const p4 = JSON.parse(JSON.stringify(p1)); // deep copy
console.log('p1===p3 is', p1 === p3);
console.log('p1===p4 is', p1 === p4);

p1.name = p1.name.toLocaleUpperCase();
p1.phones.push('8026661911');
console.log(p1);
console.log(p2);
console.log(p3);
console.log(p4);
