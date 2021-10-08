import React, { useState } from 'react';
import { login } from '../../actions/contactActions';
import { useDispatch } from 'react-redux';

export default function Login() {
    const [user, setUser] = useState({
        email: 'vinod@vinod.co',
        password: 'topsecret',
    });
    const [isError, setIsError] = useState(false);
    const [errMsg, setErrMsg] = useState('');

    const dispatch = useDispatch();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            dispatch(await login(user));
        } catch (error) {
            console.log(error);
            setUser({ email: '', password: '' });
            setIsError(true);
            setErrMsg(error.response.data);
            setTimeout(() => {
                setIsError(false);
                setErrMsg('');
            }, 2000);
        }
    };

    const handleChange = ({ target: { name, value } }) => {
        setUser({ ...user, [name]: value });
    };
    return (
        <div>
            <h3>Login to access your contacts</h3>
            <hr />
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor='email'>Email address</label>
                    <div>
                        <input
                            type='email'
                            name='email'
                            value={user.email}
                            onChange={handleChange}
                            className='form-control'
                        />
                    </div>
                </div>
                <div>
                    <label htmlFor='password'>Password</label>
                    <div>
                        <input
                            type='password'
                            name='password'
                            value={user.password}
                            onChange={handleChange}
                            className='form-control'
                        />
                    </div>
                </div>
                <button className='btn btn-primary'>Login</button>
            </form>

            {isError && <p className='text-danger'>{errMsg}</p>}
        </div>
    );
}
