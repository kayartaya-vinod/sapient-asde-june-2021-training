import React, { useState } from 'react';

export default function NewContactForm({ addContact }) {
    const [contact, setContact] = useState({
        name: '',
        email: '',
        phone: '',
        gender: 'Male',
        city: 'Bangalore',
        state: 'Karnataka',
    });

    const handleChange = ({ target }) => {
        const c = { ...contact }; // local shallow copy of state
        c[target.name] = target.value; // for example, c['email'] = 'vin@'
        // this works, because the name of the field matches with the name of the textfield
        setContact(c);
    };

    const handleSubmit = async (evt) => {
        evt.preventDefault();
        await addContact({ ...contact });
        setContact({
            name: '',
            email: '',
            phone: '',
            gender: 'Male',
            city: 'Bangalore',
            state: 'Karnataka',
        });
    };

    return (
        <div>
            <h3>Add new contact</h3>
            <form onSubmit={handleSubmit}>
                <div className='mb-3'>
                    <label htmlFor='name' className='form-label'>
                        Name
                    </label>
                    <input
                        type='text'
                        className='form-control'
                        id='name'
                        data-testid='name'
                        value={contact.name}
                        onChange={handleChange}
                        name='name'
                    />
                </div>
                <div className='mb-3'>
                    <label htmlFor='name' className='form-label'>
                        Gender &nbsp;
                    </label>
                    <label>
                        <input
                            type='radio'
                            value={contact.gender}
                            onChange={handleChange}
                            name='gender'
                        />
                        &nbsp; Male &nbsp;
                    </label>
                    <label>
                        <input
                            type='radio'
                            value={contact.gender}
                            onChange={handleChange}
                            name='gender'
                        />
                        &nbsp; Female &nbsp;
                    </label>
                </div>
                <div className='mb-3'>
                    <label htmlFor='email' className='form-label'>
                        Email address
                    </label>
                    <input
                        type='email'
                        className='form-control'
                        id='email'
                        data-testid='email'
                        value={contact.email}
                        onChange={handleChange}
                        name='email'
                    />
                </div>
                <div className='mb-3'>
                    <label htmlFor='phone' className='form-label'>
                        Phone number
                    </label>
                    <input
                        type='tel'
                        className='form-control'
                        id='phone'
                        data-testid='phone'
                        value={contact.phone}
                        onChange={handleChange}
                        name='phone'
                    />
                </div>
                <div className='mb-3'>
                    <label htmlFor='city' className='form-label'>
                        City
                    </label>
                    <input
                        type='text'
                        className='form-control'
                        id='city'
                        data-testid='city'
                        value={contact.city}
                        onChange={handleChange}
                        name='city'
                    />
                </div>
                <div className='mb-3'>
                    <label htmlFor='state' className='form-label'>
                        State
                    </label>
                    <input
                        type='text'
                        className='form-control'
                        id='state'
                        data-testid='state'
                        value={contact.state}
                        onChange={handleChange}
                        name='state'
                    />
                </div>

                <button
                    data-testid='btn-add'
                    type='submit'
                    className='btn btn-primary'
                >
                    Submit
                </button>
            </form>
        </div>
    );
}
