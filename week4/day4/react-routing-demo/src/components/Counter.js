import React, { useState } from 'react';

export default function Counter() {
    const [count, setCount] = useState(0);

    const handleClick = (op) => {
        if (op === 'INC') {
            setCount(count + 1);
        } else {
            setCount(count - 1);
        }
    };

    return (
        <div>
            <h3>Counter app</h3>
            <button
                onClick={() => handleClick('INC')}
                className='btn btn-outline-primary'
            >
                +
            </button>
            <h3
                style={{
                    display: 'inline-block',
                    width: '100px',
                    textAlign: 'center',
                }}
            >
                {count}
            </h3>
            <button
                onClick={() => handleClick('DEC')}
                className='btn btn-outline-primary'
            >
                -
            </button>
        </div>
    );
}
