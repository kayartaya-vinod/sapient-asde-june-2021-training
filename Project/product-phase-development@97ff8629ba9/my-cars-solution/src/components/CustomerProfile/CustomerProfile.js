/**
@Author Mutharasan E - mutharasan.e@publicissapient.com 
*/

import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchCustomerById } from '../../redux/actionCreators/customerActionCreator/customerActionCreator';

export default function CustomerProfile () {
    const singleCustomer = useSelector((store) => store.singleCustomerReducer);

    const loggedInUser = useSelector((store) => store.authReducer);

    const dispatch = useDispatch();
    useEffect(() => {
        (async function () {
            dispatch(await fetchCustomerById());
        })();
    }, [dispatch]);

    const profile = singleCustomer.error ? (
        <h1 style={{ color: 'red' }}>{singleCustomer.error}</h1>
    ) : (
        <div className='container'>
            <h2>Your Profile</h2>
            <hr />
            <div className='row'>
                <div className='col'>
                    <h3>
                        {singleCustomer.firstName} {singleCustomer.lastName}
                    </h3>

                    <table className='table'>
                        <tbody>
                            <tr>
                                <td>Email</td>
                                <td>{loggedInUser.user.email}</td>
                            </tr>
                            <tr>
                                <td>Alternate Email</td>
                                <td>{singleCustomer.alternateEmail}</td>
                            </tr>
                            <tr>
                                <td>Default Address</td>
                                <td>
                                    Building No:
                                    {singleCustomer.address.default.buildingNo}
                                    <br />
                                    Address:Street-
                                    {singleCustomer.address.default.street},
                                    LandMark-
                                    {singleCustomer.address.default.landMark},
                                    City-
                                    {singleCustomer.address.default.city},
                                    State-
                                    {singleCustomer.address.default.state},
                                    PinCode-
                                    {singleCustomer.address.default.pinCode}
                                </td>
                            </tr>
                            <tr>
                                <td>Alternate Address</td>
                                <td>
                                    Building No:
                                    {
                                        singleCustomer.address.anotherAddress
                                            .buildingNo
                                    }
                                    <br />
                                    Address:Street-
                                    {
                                        singleCustomer.address.anotherAddress
                                            .street
                                    }
                                    , LandMark-
                                    {
                                        singleCustomer.address.anotherAddress
                                            .landMark
                                    }
                                    , City-
                                    {singleCustomer.address.anotherAddress.city}
                                    , State-
                                    {
                                        singleCustomer.address.anotherAddress
                                            .state
                                    }
                                    , PinCode-
                                    {
                                        singleCustomer.address.anotherAddress
                                            .pinCode
                                    }
                                </td>
                            </tr>
                            <tr>
                                <td>Contact</td>
                                <td>{singleCustomer.contactNo}</td>
                            </tr>
                            <tr>
                                <td>Alternate Contact</td>
                                <td>{singleCustomer.alternateContactNo}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
    return { ...profile };
}
