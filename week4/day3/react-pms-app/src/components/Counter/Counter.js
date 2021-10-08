import React, { useState } from 'react';
import './Counter.css';

// useState is one of many react hooks introdcued in 16.3+

export default function Counter() {
    // useState() returns an array of 2 elements, one is the state, and another is a function to change the state
    const [count, setCount] = useState(0);

    const incrementCount = () => {
        setCount(count + 1); // changing the state re-renders the component
        console.log('count is', count);
    };

    const decrementCount = () => {
        setCount(count - 1);
        console.log('count is', count);
    };

    return (
        <div className='container'>
            <h1>Counter app</h1>
            <hr />

            <h3>Value of count is {count}</h3>

            <button onClick={incrementCount} className='btn btn-success'>
                Increment
            </button>
            <button onClick={decrementCount} className='btn btn-danger'>
                Decrement
            </button>
        </div>
    );
}
