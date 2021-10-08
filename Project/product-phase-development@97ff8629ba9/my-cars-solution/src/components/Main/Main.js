import React, { Suspense } from "react";
import { Provider } from "react-redux";
import { Route, Switch } from "react-router";
import { BrowserRouter as Router } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import { createStore } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import { ROLE_CUSTOMER, ROLE_DEALER } from "../../constants";
import rootReducer from "../../redux/reducers/rootReducer";
// import AccessorySearchResult from "../AccessorySearchResult/AccessorySearchResult";
// import AddVehicle from "../AddVehicle/AddVehicle";
// import AdvanceSearch from "../AdvanceSearch/AdvanceSearch";
// import CustomerVerification from "../CustomerVerification/CustomerVerification";
// import Dashboard from "../Dashboard/Dashboard";
// import EditVehicleDetails from "../EditVehicleDetails/EditVehicleDetails";
import Footer from "../Footer/Footer";
// import Header from "../Header/Header";
// import Home from "../Home/Home";
// import LoginPage from "../LoginPage/LoginPage";
// import ResetPasswordEmail from "../ResetPasswordEmail/ResetPasswordEmail";
// import SearchResult from "../SearchResult/SearchResult";
// import UpdatePassword from "../UpdatePassword/UpdatePassword";
// import VehicleAccessoryDetails from "../VehicleAccessoryDetails/VehicleAccessoryDetails";
// import VehicleAccessoryEdit from "../VehicleAccessoryEdit/VehicleAccessoryEdit";
// import UpdatedVehicleComparisonMatrix from "../VehicleComparisonMatrix/UpdatedVehicleComparisonMatrix";
// import VehicleDetailsPage from "../VehicleDetailsPage/VehicleDetailsPage";
// import Vehicles from "../Vehicles/Vehicles";
// import DisplayVehicleComparisonMatrix from "../DisplayVehicleComparisonMatrix/DisplayVehicleComparisonMatrix";
// import Register from "../Register/Register";
import PleaseWait from "../PleaseWait/PleaseWait";
import PrivateRoute from "../PrivateRoute/PrivateRoute";
import ScrollToTop from "../ScrollToTop/ScrollToTop";




const AccessorySearchResult = React.lazy(() => import('../AccessorySearchResult/AccessorySearchResult'));
const AddVehicle = React.lazy(() => import("../AddVehicle/AddVehicle"));
const AdvanceSearch = React.lazy(() => import("../AdvanceSearch/AdvanceSearch"));
const CustomerVerification = React.lazy(() => import("../CustomerVerification/CustomerVerification"));
const Dashboard = React.lazy(() => import("../Dashboard/Dashboard"));
const EditVehicleDetails = React.lazy(() => import("../EditVehicleDetails/EditVehicleDetails"));
// const Footer = React.lazy(() => import("../Footer/Footer"));
const Header = React.lazy(() => import("../Header/Header"));
const Home = React.lazy(() => import("../Home/Home"));
const LoginPage = React.lazy(() => import("../LoginPage/LoginPage"));
// const PrivateRoute = React.lazy(() => import("../PrivateRoute/PrivateRoute"));
const Register = React.lazy(() => import("../Register/Register"));
const ResetPasswordEmail = React.lazy(() => import("../ResetPasswordEmail/ResetPasswordEmail"));
const SearchResult = React.lazy(() => import("../SearchResult/SearchResult"));
const UpdatePassword = React.lazy(() => import("../UpdatePassword/UpdatePassword"));
const VehicleAccessoryDetails = React.lazy(() => import("../VehicleAccessoryDetails/VehicleAccessoryDetails"));
const VehicleAccessoryEdit = React.lazy(() => import("../VehicleAccessoryEdit/VehicleAccessoryEdit"));
const UpdatedVehicleComparisonMatrix = React.lazy(() => import("../VehicleComparisonMatrix/UpdatedVehicleComparisonMatrix"));
const VehicleDetailsPage = React.lazy(() => import("../VehicleDetailsPage/VehicleDetailsPage"));
const Vehicles = React.lazy(() => import("../Vehicles/Vehicles"));
const DisplayVehicleComparisonMatrix = React.lazy(() => import("../DisplayVehicleComparisonMatrix/DisplayVehicleComparisonMatrix"));



const store = createStore(
  rootReducer,
  process.env.NODE_ENV === 'development' && composeWithDevTools(),
  // composeWithDevTools(applyMiddleware(thunk))
);
export default function Main () {
  return (
    <Router>
      <Provider store={store}>
        <Suspense fallback={<PleaseWait />}>
          <Header />
          <div style={{ paddingTop: "7em" }}>
            <ToastContainer data-testid="toaster" limit={1}></ToastContainer>
            <ScrollToTop>
              <Switch>
                <Route path="/" exact={true} component={Home} />

                <PrivateRoute role={ROLE_DEALER} path="/vehicles/add" exact={true}>
                  <AddVehicle />
                </PrivateRoute>
                <Route path="/login" component={LoginPage} />
                <Route path="/register" component={Register} />

                <Route path="/vehicles/:id">
                  <VehicleDetailsPage />
                </Route>
                {/* <Route path="/vehicle-compare" component={VehicleComparisonMatrix} /> */}
                <Route
                  path="/vehicle-compare"
                  component={UpdatedVehicleComparisonMatrix}
                />

                <PrivateRoute
                  path="/customer/vehicle-comparison/:id"
                  role={ROLE_CUSTOMER}
                >
                  <DisplayVehicleComparisonMatrix />
                </PrivateRoute>


                <Route path="/reset-password-email" component={ResetPasswordEmail} />
                <Route path="/forgot-password/:token" component={UpdatePassword} />

                <Route path="/search" component={SearchResult} />
                <Route path="/accessories/search" component={AccessorySearchResult} />
                <Route path="/advanced-search">
                  <AdvanceSearch />
                </Route>
                <Route path="/vehicles" component={Vehicles} />
                <Route path="/verify-account/:token">
                  <CustomerVerification />
                </Route>
                <PrivateRoute
                  role={ROLE_DEALER}
                  exact={true}
                  path="/vehicle-accessory/edit/:id"
                >
                  <VehicleAccessoryEdit />
                </PrivateRoute>

                <Route path="/vehicle-accessory/:id" >
                  <VehicleAccessoryDetails />
                </Route>


                <PrivateRoute path="/customer/dashboard" role={ROLE_CUSTOMER}>
                  <Dashboard />
                </PrivateRoute>
                <PrivateRoute path="/dealer/dashboard" role={ROLE_DEALER}>
                  <Dashboard />
                </PrivateRoute>

                <PrivateRoute
                  role={ROLE_DEALER}
                  path="/dealer/edit-vehicle-details/:id"
                >
                  <EditVehicleDetails />
                </PrivateRoute>

                <Route path="/**">
                  <h1>Page Not Found</h1>
                </Route>
              </Switch>
            </ScrollToTop>
          </div>
          <Footer />
        </Suspense>
      </Provider>
    </Router>
  );
}
