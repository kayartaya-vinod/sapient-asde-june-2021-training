import React, { useState, useEffect } from 'react';
import { fetchContactById, updateContact } from '../../actions/contactActions';
import { connect } from 'react-redux';

function EditContactForm({
    updateContactDetails,
    contactIn,
    match: { params },
    history,
}) {
    const [contact, setContact] = useState({
        name: '',
        email: '',
        phone: '',
        gender: '',
        city: '',
        state: '',
    });

    const handleChange = ({ target }) => {
        const c = { ...contact };
        c[target.name] = target.value;
        setContact(c);
    };

    const handleSubmit = async (evt) => {
        evt.preventDefault();
        updateContactDetails({ ...contact });
        history.push('/details/' + contact.id);
    };

    useEffect(() => {
        fetchContactById(params.contactId);
        setContact({
            name: '',
            email: '',
            phone: '',
            gender: '',
            city: '',
            state: '',
            ...contactIn,
        });
    }, [params.contactId, contactIn]);

    return (
        <div>
            <h3>Edit contact details</h3>

            <form onSubmit={handleSubmit}>
                <div className='mb-3'>
                    <label htmlFor='name' className='form-label'>
                        Name
                    </label>
                    <input
                        type='text'
                        className='form-control'
                        id='name'
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
                            value='M'
                            checked={contact.gender === 'M'}
                            onChange={handleChange}
                            name='gender'
                        />
                        &nbsp; Male &nbsp;
                    </label>
                    <label>
                        <input
                            type='radio'
                            value='F'
                            checked={contact.gender === 'F'}
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
export default connect((store) => ({ contactIn: store.singleContactReducer }), {
    updateContactDetails: updateContact,
})(EditContactForm);
