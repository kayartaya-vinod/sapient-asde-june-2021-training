function square(num) {
    if (isNaN(num)) {
        throw new Error('input must be a number');
    }
    return num * num;
}

let a = 123;
console.log('square of', a, 'is', square(a));

a = 'vinod';
console.log('square of', a, 'is', square(a));
a = true;
console.log('square of', a, 'is', square(a));
a = {};
console.log('square of', a, 'is', square(a));
a = [];
console.log('square of', a, 'is', square(a));
a = undefined;
console.log('square of', a, 'is', square(a));
