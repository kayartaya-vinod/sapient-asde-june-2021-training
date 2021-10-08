import React, { useState, useRef } from 'react';

export default function Table() {
    const [output, setOutput] = useState(<h3>Table not ready yet</h3>);
    const numRef = useRef();
    // refs are references to DOM elements (usually, form inputs)

    const handleSubmit = (evt) => {
        evt.preventDefault();
        let num = numRef.current.valueAsNumber;

        let arr = [];
        for (let i = 1; i <= 10; i++) {
            arr.push(
                <div className='list-group-item' key={i}>
                    {num} X {i} = {num * i}
                </div>
            );
        }
        setOutput(arr); // react internally changes the value of output to str
    };

    return (
        <>
            <h1>Multiplication table</h1>
            <hr />
            <form onSubmit={handleSubmit}>
                <input
                    ref={numRef}
                    type='number'
                    placeholder='Enter a number'
                    className='form-control'
                />

                <br />
                <button className='btn btn-primary'>Generate table</button>
            </form>
            <br />
            <div className='list-group' style={{ width: '200px' }}>
                {output}
            </div>
        </>
    );
}
