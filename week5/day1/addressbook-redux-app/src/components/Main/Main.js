import React from 'react';

import ContactDetails from '../ContactDetails/ContactDetails';
import EditContactForm from '../EditContactForm/EditContactForm';
import Login from '../Login/Login';
import { useSelector } from 'react-redux';
import NewContactForm from '../NewContactForm/NewContactForm';
import ContactList from '../ContactList/ContactList';
import { Route, Switch } from 'react-router-dom';

export default function Main() {
    const loginReducer = useSelector((store) => store.loginReducer);

    const toDisplay = !loginReducer.token ? (
        <div className='row'>
            <div className='col'></div>
            <div className='col'>
                <Login />
            </div>
            <div className='col'></div>
        </div>
    ) : (
        <div className='container'>
            <Switch>
                <div className='row'>
                    <div className='col-4 col-xs-12'>
                        <Route
                            path='/'
                            exact={true}
                            component={NewContactForm}
                        />
                        <Route
                            path='/details/:contactId'
                            component={ContactDetails}
                        />
                        <Route
                            path='/edit/:contactId'
                            component={EditContactForm}
                        />
                    </div>
                    <div className='col-8 col-xs-12'>
                        <ContactList />
                    </div>
                </div>
                <Route path='/**'>
                    <h3 className='text-danger text-center'>
                        OOPS! not a valid url
                    </h3>
                </Route>
            </Switch>
        </div>
    );

    return <div>{toDisplay}</div>;
}
