let square = function (num) {
    if (isNaN(num)) {
        throw new Error('input must be a number');
    }
    return num * num;
};

let a = 123;
console.log('square of', a, 'is', square(a));
