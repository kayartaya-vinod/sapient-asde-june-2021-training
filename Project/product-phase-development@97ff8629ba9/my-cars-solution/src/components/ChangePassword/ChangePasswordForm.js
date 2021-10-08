import React, { useState, useRef } from 'react';
import { passwordStrengthCheck } from '../../utils/utils';
import { changePassword } from '../../redux/actionCreators/passwordChangeCreator/passwordChangeCreator';
import sanitize from 'mongo-sanitize';

export default function ChangePasswordForm () {
    const [changePwd, setChangePwd] = useState({
        input: {
            old_password: '',
            password: '',
            confirm_password: '',
        },
        alert: '',
        alertClassName: 'danger',
    });

    const pwdRef = useRef();

    const handleChange = ({ target }) => {
        let newpwd = { ...changePwd };
        newpwd.input[target.name] = sanitize(target.value);

        setChangePwd(newpwd);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        setChangePwd({ ...changePwd, alert: '' });

        if (validate()) {
            const resetPwdState = {
                input: {
                    old_password: '',
                    password: '',
                    confirm_password: '',
                },
            };

            const resp = await changePassword(changePwd.input);
            if (resp.success === true) {
                resetPwdState.alert = 'Password has been changed';
                resetPwdState.alertClassName = 'success';
            } else {
                resetPwdState.alert = resp.error;
                resetPwdState.alertClassName = 'danger';
            }
            setChangePwd({ ...changePwd, ...resetPwdState });
            pwdRef.current.focus();
        }
    };

    const validate = () => {
        let newPwd = { ...changePwd };
        let input = newPwd.input;
        let isValid = true;

        if (
            !input['old_password'] ||
            !input['password'] ||
            !input['confirm_password']
        ) {
            isValid = false;
            newPwd.alert = 'Please enter all details.';
        } else if (
            typeof input['password'] !== 'undefined' &&
            typeof input['confirm_password'] !== 'undefined'
        ) {
            if (input['password'] !== input['confirm_password']) {
                isValid = false;
                newPwd.alert = "Passwords don't match.";
            }
            if (passwordStrengthCheck(input.password) === false) {
                isValid = false;
                newPwd.alert =
                    'Must contain at least one digit and one uppercase and one lowercase letter and one special character, and at least 8 or more characters.';
            }
        }

        setChangePwd(newPwd);
        pwdRef.current.focus();
        return isValid;
    };

    return (
        <>
            <h2>Change Password</h2>
            <hr />
            <div className='row'>
                {/* <div className='col-3'></div> */}
                <div className='col'>
                    <div className='card p-3'>
                        <div className='card-body'>

                            <br />
                            <form
                                id='form'
                                className='form'
                                onSubmit={handleSubmit}
                            >
                                <div className='form-group mb-3 row'>
                                    <label
                                        htmlFor='password'
                                        className='col-form-label col-4'
                                    >
                                        Old Password:
                                    </label>
                                    <div className='col-8'>
                                        <input
                                            autoFocus
                                            ref={pwdRef}
                                            type='password'
                                            name='old_password'
                                            value={changePwd.input.old_password}
                                            onChange={handleChange}
                                            className='form-control'
                                            placeholder='Enter old password'
                                            id='old_password'
                                            data-testid='old_password'
                                        />
                                    </div>
                                </div>

                                <div className='form-group mb-3 row'>
                                    <label
                                        htmlFor='password'
                                        className='col-form-label col-4'
                                    >
                                        New Password:
                                    </label>
                                    <div className='col-8'>
                                        <input
                                            type='password'
                                            name='password'
                                            value={changePwd.input.password}
                                            onChange={handleChange}
                                            className='form-control'
                                            placeholder='Enter new password'
                                            id='password'
                                            data-testid='password'
                                        />
                                    </div>
                                </div>

                                <div className='form-group mb-3 row'>
                                    <label
                                        htmlFor='password'
                                        className='col-form-label col-4'
                                    >
                                        Confirm Password:
                                    </label>
                                    <div className='col-8'>
                                        <input
                                            type='password'
                                            name='confirm_password'
                                            value={
                                                changePwd.input.confirm_password
                                            }
                                            onChange={handleChange}
                                            className='form-control'
                                            placeholder='Confirm password'
                                            id='confirm_password'
                                            data-testid='confirm_password'
                                        />
                                    </div>
                                </div>
                                <div className='form-group mb-3 row'>
                                    <label className='col-form-label col-4'></label>
                                    <div className='col-8'>
                                        <input
                                            type='submit'
                                            value='Change Password'
                                            className='btn btn-primary'
                                            data-testid='btn-submit'
                                        />
                                    </div>
                                </div>
                            </form>
                            {changePwd.alert !== '' && (
                                // <div
                                //     className={`alert alert-${changePwd.alertClassName} d-flex align-items-center`}
                                //     role='alert'
                                //     data-testid='alert'
                                // >
                                //     {changePwd.alert}
                                //     <button
                                //         type='button'
                                //         className='btn-close'
                                //         data-bs-dismiss='alert'
                                //         aria-label='Close'
                                //     ></button>
                                // </div>
                                <div
                                    className={`text-${changePwd.alertClassName}`}
                                    style={{ paddingTop: '30px' }}
                                >
                                    {changePwd.alert}
                                </div>
                            )}
                        </div>
                    </div>
                </div>
                {/* <div className='col-3'></div> */}
            </div>
        </>
    );
}
