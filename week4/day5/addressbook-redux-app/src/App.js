import Header from './components/Header/Header';
import NewContactForm from './components/NewContactForm/NewContactForm';
import ContactList from './components/ContactList/ContactList';

import { createStore, applyMiddleware } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import { Provider } from 'react-redux';
import thunk from 'redux-thunk';

import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import rootReducer from './reducers/rootReducer';
import ContactDetails from './components/ContactDetails/ContactDetails';
import EditContactForm from './components/EditContactForm/EditContactForm';

const store = createStore(
    rootReducer,
    composeWithDevTools(applyMiddleware(thunk))
);

function App() {
    return (
        <Router>
            <Provider store={store}>
                <Header />
                <div className='container'>
                    <div className='row'>
                        <div className='col-4 col-xs-12'>
                            <Switch>
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
                                <Route path='/**'>
                                    <h3 className='text-danger text-center'>
                                        OOPS! not a valid url
                                    </h3>
                                </Route>
                            </Switch>
                        </div>
                        <div className='col-8 col-xs-12'>
                            <ContactList a='asd' b='qwe' c='ert' />
                        </div>
                    </div>
                </div>
            </Provider>
        </Router>
    );
}

export default App;
