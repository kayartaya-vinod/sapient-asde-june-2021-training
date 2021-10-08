import Header from './components/Header/Header';

import { createStore, applyMiddleware } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import { Provider } from 'react-redux';
import thunk from 'redux-thunk';

import { BrowserRouter as Router } from 'react-router-dom';

import rootReducer from './reducers/rootReducer';
import Main from './components/Main/Main';

const store = createStore(
    rootReducer,
    composeWithDevTools(applyMiddleware(thunk))
);

function App() {
    return (
        <Router>
            <Provider store={store}>
                <Header />
                <Main />
            </Provider>
        </Router>
    );
}

export default App;
