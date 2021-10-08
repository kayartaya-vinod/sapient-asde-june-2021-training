import React, { useState } from 'react';

export default function Table({ match: { params } }) {

    const [num, setNum] = useState(params.num || 100);
    
    const [values, setValues] = useState([]);

    const handleChange = ({ target }) => {
        setNum(target.value);
    };

    const handleSubmit = (evt) => {
        evt.preventDefault();
        let v = [];
        for (let i = 1; i <= 10; i++) {
            v.push(
                <li className='list-group-item' key={i}>
                    {num} X {i} = {num * i}
                </li>
            );
        }
        setValues(v);
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <input
                    type='num'
                    placeholder='enter a number'
                    value={num}
                    onChange={handleChange}
                />
            </form>
            <ul className='list-group' style={{ width: '300px' }}>
                {values}
            </ul>
        </div>
    );
}
