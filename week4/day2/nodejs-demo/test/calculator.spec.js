// test suite

const { assert, expect } = require('chai');
const { add, subtract } = require('../calculator');

// we are calling a mocha method called 'describe', which is considered as a
// test suite, and it consists of one or more test cases.
describe('Calculator tests for "add()"', function () {
    // one or more test cases (by calling a mocha method called 'it')

    it('should add 4 and 5 to get 9 as result', () => {
        // one ore more assertions
        assert.equal(9, add(4, 5));
    });

    it('should add "4" and "5" to get 9 as result', () => {
        assert.equal(9, add('4', '5'));
    });

    it('should add 4 and "5" to get 9 as result', () => {
        assert.equal(9, add(4, '5'));
    });

    it('should add -4 and "5" to get 1 as result', () => {
        assert.equal(1, add(-4, '5'));
    });
});

describe('Calculator test for "subtract()"', () => {
    it('should subtract 4 from 9 to get 5', () => {
        expect(subtract(9, 4)).to.equal(5);
    });
    it('should subtract 9 from 4 to get -5', () => {
        expect(subtract(4, 9)).to.equal(-5);
    });
});
