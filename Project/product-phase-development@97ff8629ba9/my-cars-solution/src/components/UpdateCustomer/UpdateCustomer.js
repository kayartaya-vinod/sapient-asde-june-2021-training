/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import sanitize from 'mongo-sanitize';
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useHistory } from 'react-router-dom';

import {
    fetchCustomerById,
    updateCustomer,
} from '../../redux/actionCreators/customerActionCreator/customerActionCreator';

function UpdateCustomer() {
    const singleCustomer = useSelector((store) => store.singleCustomerReducer);
    const loggedInUser = useSelector((store) => store.authReducer);
    const dispatch = useDispatch();

    const [customer, setCustomer] = useState({
        _id: '',
        firstName: '',
        lastName: '',
        email: '',
        alternateEmail: '',
        password: '',
        address: {
            default: {
                buildingNo: '',
                street: '',
                landMark: '',
                city: '',
                state: '',
                pinCode: '',
            },
            anotherAddress: {
                buildingNo: '',
                street: '',
                landMark: '',
                city: '',
                state: '',
                pinCode: '',
            },
        },
        contactNo: '',
        alternateContactNo: '',
    });

    useEffect(() => {
        (async function () {
            dispatch(await fetchCustomerById());
        })();
    }, [dispatch]);

    useEffect(() => {
        if (typeof singleCustomer !== 'undefined') {
            setCustomer(JSON.parse(JSON.stringify(singleCustomer)));
        }
    }, [singleCustomer]);
    const handleChange = ({ target }) => {
        const c = { ...customer };
        c[target.name] = sanitize(target.value);
        setCustomer(c);
    };

    const handleChangeDefaultAddress = ({ target }) => {
        const newCustomer = { ...customer };
        newCustomer.address.default[target.name] = sanitize(target.value);
        setCustomer({ ...newCustomer });
    };

    const handleChangeAnotherAddress = ({ target }) => {
        const newCustomer = { ...customer };
        newCustomer.address.anotherAddress[target.name] = sanitize(target.value);
        setCustomer({ ...newCustomer });
    };

    const history = useHistory();
    const handleSubmit = async (evt) => {
        evt.preventDefault();
        dispatch(await updateCustomer({ ...customer }));
        history.push("/customer/dashboard");
    };

    const profile =
        singleCustomer && singleCustomer.error ? (
            <h1 style={{ color: 'red' }}>{singleCustomer.error}</h1>
        ) : (
            <div>
                    <h2>Update your details</h2>
                    <hr />
                <form onSubmit={handleSubmit} data-testid='updateDetailsForm'>
                    <div className='container'>
                        <div className='row'>
                            <div className='col-md-4'>
                                <div className='card p-3'>
                                    <div className='card-body'>
                                        <h4>Basic Details</h4>
                                        <div className='mb-3'>
                                            <label
                                                htmlFor='firstName'
                                                className='form-label'
                                            >
                                                First Name
                                            </label>
                                            <input
                                                data-testid='firstName'
                                                type='text'
                                                className='form-control'
                                                id='firstName'
                                                defaultValue={
                                                    loggedInUser.user.name
                                                }
                                                readOnly
                                                onChange={handleChange}
                                                name='firstName'
                                                required
                                            />
                                        </div>

                                        <div className='mb-3'>
                                            <label
                                                htmlFor='lastName'
                                                className='form-label'
                                            >
                                                Last Name
                                            </label>
                                            <input
                                                type='text'
                                                className='form-control'
                                                id='lastName'
                                                defaultValue={
                                                    loggedInUser.user.lastName
                                                }
                                                readOnly
                                                onChange={handleChange}
                                                name='lastName'
                                                required
                                            />
                                        </div>

                                        <div className='mb-3'>
                                            <label
                                                htmlFor='email'
                                                className='form-label'
                                            >
                                                Email address
                                            </label>
                                            <input
                                                type='email'
                                                className='form-control'
                                                id='email'
                                                defaultValue={
                                                    loggedInUser.user.email
                                                }
                                                readOnly
                                                onChange={handleChange}
                                                name='email'
                                                required
                                            />
                                        </div>

                                        <div className='mb-3'>
                                            <label
                                                htmlFor='alternateEmail'
                                                className='form-label'
                                            >
                                                Alternate Email address
                                            </label>
                                            <input
                                                type='email'
                                                className='form-control'
                                                id='alternateEmail'
                                                defaultValue={
                                                    customer.alternateEmail
                                                }
                                                onChange={handleChange}
                                                name='alternateEmail'
                                            />
                                        </div>

                                        <div className='mb-3'>
                                            <label
                                                htmlFor='contactNo'
                                                className='form-label'
                                            >
                                                Phone Number
                                            </label>
                                            <input
                                                type='tel'
                                                className='form-control'
                                                id='contactNo'
                                                defaultValue={
                                                    customer.contactNo
                                                }
                                                onChange={handleChange}
                                                name='contactNo'
                                                required
                                            />
                                        </div>

                                        <div className='mb-3'>
                                            <label
                                                htmlFor='alternateContactNo'
                                                className='form-label'
                                            >
                                                Alternate Phone Number
                                            </label>
                                            <input
                                                type='tel'
                                                className='form-control'
                                                id='alternateContactNo'
                                                defaultValue={
                                                    customer.alternateContactNo
                                                }
                                                onChange={handleChange}
                                                name='alternateContactNo'
                                            />
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className='col-md-4'>
                                <div className='card p-3'>
                                    <div className='card-body'>
                                        <div className='mb-3'>
                                            <label htmlFor='address'>
                                                <h4>Address</h4>
                                            </label>

                                            <div className='mb-3'>
                                                <label
                                                    htmlFor='defaultBuildingNo'
                                                    className='form-label'
                                                >
                                                    Building No
                                                </label>
                                                <input
                                                    data-testid='defaultBuildingNo'
                                                    type='tel'
                                                    className='form-control'
                                                    id='defaultBuildingNo'
                                                    defaultValue={
                                                        customer.address.default
                                                            .buildingNo
                                                    }
                                                    onChange={
                                                        handleChangeDefaultAddress
                                                    }
                                                    name='buildingNo'
                                                />
                                            </div>

                                            <div className='mb-3'>
                                                <label
                                                    htmlFor='defaultStreet'
                                                    className='form-label'
                                                >
                                                    Street
                                                </label>
                                                <input
                                                    type='tel'
                                                    className='form-control'
                                                    id='defaultStreet'
                                                    defaultValue={
                                                        customer.address.default
                                                            .street
                                                    }
                                                    onChange={
                                                        handleChangeDefaultAddress
                                                    }
                                                    name='street'
                                                />
                                            </div>

                                            <div className='mb-3'>
                                                <label
                                                    htmlFor='defaultLandMark'
                                                    className='form-label'
                                                >
                                                    Land Mark
                                                </label>
                                                <input
                                                    type='tel'
                                                    className='form-control'
                                                    id='defaultLandMark'
                                                    defaultValue={
                                                        customer.address.default
                                                            .landMark
                                                    }
                                                    onChange={
                                                        handleChangeDefaultAddress
                                                    }
                                                    name='landMark'
                                                />
                                            </div>

                                            <div className='mb-3'>
                                                <label
                                                    htmlFor='defaultCity'
                                                    className='form-label'
                                                >
                                                    City
                                                </label>
                                                <input
                                                    type='tel'
                                                    className='form-control'
                                                    id='defaultCity'
                                                    defaultValue={
                                                        customer.address.default
                                                            .city
                                                    }
                                                    onChange={
                                                        handleChangeDefaultAddress
                                                    }
                                                    name='city'
                                                />
                                            </div>

                                            <div className='mb-3'>
                                                <label
                                                    htmlFor='defaultState'
                                                    className='form-label'
                                                >
                                                    State
                                                </label>
                                                <input
                                                    type='tel'
                                                    className='form-control'
                                                    id='defaultState'
                                                    defaultValue={
                                                        customer.address.default
                                                            .state
                                                    }
                                                    onChange={
                                                        handleChangeDefaultAddress
                                                    }
                                                    name='state'
                                                />
                                            </div>

                                            <div className='mb-3'>
                                                <label
                                                    htmlFor='defaultPinCode'
                                                    className='form-label'
                                                >
                                                    Pin Code
                                                </label>
                                                <input
                                                    type='tel'
                                                    className='form-control'
                                                    id='defaultPinCode'
                                                    defaultValue={
                                                        customer.address.default
                                                            .pinCode
                                                    }
                                                    onChange={
                                                        handleChangeDefaultAddress
                                                    }
                                                    name='pinCode'
                                                />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className='col-md-4'>
                                <div className='card p-3'>
                                    <div className='card-body'>
                                        <div className='mb-3'>
                                            <label htmlFor='address'>
                                                <h4>Alternate Address</h4>
                                            </label>

                                            <div className='mb-3'>
                                                <label
                                                    htmlFor='buildingNo'
                                                    className='form-label'
                                                >
                                                    Building No
                                                </label>
                                                <input
                                                    data-testid='alternateBuildingNo'
                                                    type='tel'
                                                    className='form-control'
                                                    id='buildingNo'
                                                    defaultValue={
                                                        customer.address
                                                            .anotherAddress
                                                            .buildingNo
                                                    }
                                                    onChange={
                                                        handleChangeAnotherAddress
                                                    }
                                                    name='buildingNo'
                                                />
                                            </div>

                                            <div className='mb-3'>
                                                <label
                                                    htmlFor='street'
                                                    className='form-label'
                                                >
                                                    Street
                                                </label>
                                                <input
                                                    type='tel'
                                                    className='form-control'
                                                    id='street'
                                                    defaultValue={
                                                        customer.address
                                                            .anotherAddress
                                                            .street
                                                    }
                                                    onChange={
                                                        handleChangeAnotherAddress
                                                    }
                                                    name='street'
                                                />
                                            </div>

                                            <div className='mb-3'>
                                                <label
                                                    htmlFor='landMark'
                                                    className='form-label'
                                                >
                                                    Land Mark
                                                </label>
                                                <input
                                                    type='tel'
                                                    className='form-control'
                                                    id='landMark'
                                                    defaultValue={
                                                        customer.address
                                                            .anotherAddress
                                                            .landMark
                                                    }
                                                    onChange={
                                                        handleChangeAnotherAddress
                                                    }
                                                    name='landMark'
                                                />
                                            </div>

                                            <div className='mb-3'>
                                                <label
                                                    htmlFor='city'
                                                    className='form-label'
                                                >
                                                    City
                                                </label>
                                                <input
                                                    type='tel'
                                                    className='form-control'
                                                    id='city'
                                                    defaultValue={
                                                        customer.address
                                                            .anotherAddress.city
                                                    }
                                                    onChange={
                                                        handleChangeAnotherAddress
                                                    }
                                                    name='city'
                                                />
                                            </div>

                                            <div className='mb-3'>
                                                <label
                                                    htmlFor='state'
                                                    className='form-label'
                                                >
                                                    State
                                                </label>
                                                <input
                                                    type='tel'
                                                    className='form-control'
                                                    id='state'
                                                    defaultValue={
                                                        customer.address
                                                            .anotherAddress
                                                            .state
                                                    }
                                                    onChange={
                                                        handleChangeAnotherAddress
                                                    }
                                                    name='state'
                                                />
                                            </div>

                                            <div className='mb-3'>
                                                <label
                                                    htmlFor='pinCode'
                                                    className='form-label'
                                                >
                                                    Pin Code
                                                </label>
                                                <input
                                                    type='tel'
                                                    className='form-control'
                                                    id='pinCode'
                                                    defaultValue={
                                                        customer.address
                                                            .anotherAddress
                                                            .pinCode
                                                    }
                                                    onChange={
                                                        handleChangeAnotherAddress
                                                    }
                                                    name='pinCode'
                                                />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button
                            data-testid='submit-btn'
                            className='btn btn-primary'
                            type='submit'
                            style={{ marginTop: '5px' }}
                        >
                            Update Details
                        </button>
                    </div>
                </form>
            </div>
        );

    return { ...profile };
}

export default UpdateCustomer;
