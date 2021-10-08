/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
import React, { useState, useEffect } from 'react';
import { login } from '../../redux/actionCreators/authActionCreator/authActionCreator';
import ForgetPassword from '../ForgetPassword/ForgetPassword';
import { useDispatch, useSelector } from 'react-redux';
import { useHistory } from 'react-router-dom';
import { LOGIN_SUCCESS } from '../../redux/actionTypes';
import { getFavoriteVehicles } from '../../redux/actionCreators/favoriteVehicleActionCreator/favoriteVehicleActionCreator';
import {ROLE_CUSTOMER, ROLE_DEALER, ROLE_KEY } from "../../constants"
import sanitize from 'mongo-sanitize';

function LoginPage() {
    const [user, setUser] = useState({
        email: '',
        password: '',
        errors: {
            email: <li key="0">email cannot be empty</li>,
            pwd: <li key="1">password cannot be empty</li>,
        },
        errorMsg: null,
    });
    const doThis = async (data) =>{
        if (data.type === LOGIN_SUCCESS) {
            dispatch(data);
            dispatch(await getFavoriteVehicles());
            if (localStorage.getItem(ROLE_KEY) === ROLE_CUSTOMER) {
                history.push('/customer/dashboard/my-account');
            }
            else if (localStorage.getItem(ROLE_KEY) === ROLE_DEALER) {
                history.push('/dealer/dashboard/dealer-vehicles');
            }
            else {
                history.push('/');
            }
        }
        else {
            let errorMsg = <li>{data.payload}</li>;
            setUser({ ...user, errorMsg });
        }
    }
    const { isLoggedIn } = useSelector((store) => store.authReducer);
    const history = useHistory();
    const dispatch = useDispatch();
    const handleSubmit = async (e) => {
        let { errors } = user;
        e.preventDefault();
        if (errors.email === null && errors.pwd === null) {
            const current = (({ email, password }) => ({
                email,
                password,
            }))(user);
            doThis(await login(current));
           
        }
         else {
            let errorMsg = Object.values(errors)
            setUser({ ...user, errorMsg });
        }
    };

    useEffect(() => {
        if (isLoggedIn) {
            if (localStorage.getItem(ROLE_KEY) === ROLE_CUSTOMER) {
                history.push('/customer/dashboard');
            }
            else if (localStorage.getItem(ROLE_KEY) === ROLE_DEALER) {
                history.push('/dealer/dashboard');
            }
            else {
                history.push('/');
            }
        }
    }, [isLoggedIn,history]);
    const handleEmailChange = (event) => {
        let { name, value } = event.target;
        let { errors } = user;
            if (!value || value.length === 0) {
                errors.email = <li key="0">email cannot be empty</li>;
            } else {
                errors.email = null;
            }
        setUser({ ...user, [name]: sanitize(value), errors });
    };
    const handlePasswordChange = (event) => {
        let { name, value } = event.target;
        let { errors } = user;
            if (!value || value.length === 0) {
                errors.pwd = <li key="1">password cannot be empty</li>;
            } else {
                errors.pwd = null;
            }
        setUser({ ...user, [name]: sanitize(value), errors });
    };
    return (
        <div className='row'>
            <div className='col-md-3'></div>
            <div className='col-md'>
                <br />
                <br />
                <div className='card p-3'>
                    <div className='card-body'>
                        <h2>Existing users, login here !</h2>
                        <form onSubmit={handleSubmit} data-testid='formSubmit'>
                            <label htmlFor='email' className='form-label mt-3'>
                                Email
                            </label>
                            <div>
                                <input
                                    type='email'
                                    autoFocus
                                    name='email'
                                    id='email'
                                    value={user.email}
                                    onChange={handleEmailChange}
                                    className='form-control mb-4'
                                    placeholder='JohnDoe@akatsuki.com'
                                    data-testid='email'
                                    style={{ mt: '0' }}
                                />
                            </div>
                            <label htmlFor='password' className='form-label'>
                                Password
                            </label>
                            <div>
                                <input
                                    type='password'
                                    name='password'
                                    id='password'
                                    value={user.password}
                                    onChange={handlePasswordChange}
                                    className='form-control mb-4'
                                    placeholder='topsecret'
                                    data-testid='password'
                                />
                            </div>
                            <div data-testid='btn-add'>
                                <button className='btn btn-primary'>
                                    Login
                                </button>
                                &nbsp; &nbsp;
                                <ForgetPassword />
                            </div>
                            <br/>
                            <ul className='form-error text-danger'>
                                {user.errorMsg}
                            </ul>
                        </form>
                    </div>
                </div>
            </div>
            <div className='col-md-3'></div>
        </div>
    );
}

export default LoginPage;