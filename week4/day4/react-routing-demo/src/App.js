import Header from './components/Header';
import Sidebar from './components/Sidebar';
import Counter from './components/Counter';
import PageNotFound from './components/PageNotFound';
import Home from './components/Home';
import Table from './components/Table';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Info from './components/Info';

function App() {
    return (
        <Router>
            <Header />
            <div className='container'>
                <div className='row'>
                    <div className='col-md-4'>
                        <Sidebar />
                    </div>
                    <div className='col-md-8'>
                        <Switch>
                            <Route path='/' exact={true} render={Home} />

                            <Route path='/counter'>
                                <Counter />
                            </Route>

                            <Route path='/info' component={Info} />

                            <Route
                                path='/multiplication-table/:num'
                                component={Table}
                            />

                            <Route
                                path='/multiplication-table'
                                component={Table}
                            />
                            <Route path='/about-us'>
                                <h3>
                                    We are a group of developers experienced in
                                    Micro service based UI applications
                                </h3>
                            </Route>

                            <Route path='/**'>
                                <PageNotFound />
                            </Route>
                        </Switch>
                    </div>
                </div>
            </div>
        </Router>
    );
}

export default App;
