/**
@Author Aditya Gheewala aditya.gheewala@publicissapient.com
*/
import React, { Suspense, useState } from "react";
import { Route, Switch, useHistory } from "react-router-dom";
import { ROLE_CUSTOMER, ROLE_DEALER } from "../../constants";
// import AddAccessory from "../AddAccessory/AddAccessory";
// import AddVehicle from "../AddVehicle/AddVehicle";
// import ChangePasswordForm from "../ChangePassword/ChangePasswordForm";
// import ComparisonMatrixMetadata from "../ComparisonMatrixMetadata/ComparisonMatrixMetadata";
// import CustomerProfile from "../CustomerProfile/CustomerProfile";
// import DealerAccessories from "../DealerAccessories/DealerAccessories";
// import DealerReviews from "../DealerReviews/DealerReviews";
// import DealerVehicles from "../DealerVehicles/DealerVehicles";
// import DisplayDealerUploads from "../DisplayDealerUploads/DisplayDealerUploads";
// import FavoriteVehicles from "../FavoriteVehicles/FavoriteVehicles";
import IfLoggedIn from "../IfLoggedIn/IfLoggedIn";
import PleaseWait from "../PleaseWait/PleaseWait";
// import UpdateCustomer from "../UpdateCustomer/UpdateCustomer";
// import UploadJson from "../UploadJson/UploadJson";
// import UploadPage from "../UploadPage/UploadPage";
import "./Dashboard.css";



const AddAccessory = React.lazy(() => import("../AddAccessory/AddAccessory"));
const AddVehicle = React.lazy(() => import("../AddVehicle/AddVehicle"));
const ChangePasswordForm = React.lazy(() => import("../ChangePassword/ChangePasswordForm"));
const ComparisonMatrixMetadata = React.lazy(() => import("../ComparisonMatrixMetadata/ComparisonMatrixMetadata"));
const CustomerProfile = React.lazy(() => import("../CustomerProfile/CustomerProfile"));
const DealerAccessories = React.lazy(() => import("../DealerAccessories/DealerAccessories"));
const DealerReviews = React.lazy(() => import("../DealerReviews/DealerReviews"));
const DealerVehicles = React.lazy(() => import("../DealerVehicles/DealerVehicles"));
const DisplayDealerUploads = React.lazy(() => import("../DisplayDealerUploads/DisplayDealerUploads"));
const FavoriteVehicles = React.lazy(() => import("../FavoriteVehicles/FavoriteVehicles"));
// const IfLoggedIn = React.lazy(() => import("../IfLoggedIn/IfLoggedIn"));
const UpdateCustomer = React.lazy(() => import("../UpdateCustomer/UpdateCustomer"));
const UploadJson = React.lazy(() => import("../UploadJson/UploadJson"));
const UploadPage = React.lazy(() => import("../UploadPage/UploadPage"));


export default function Dashboard() {
  const history = useHistory();
  const [activeComponent, setActiveComponent] = useState(window.location.href)

  const CommonOptions = (role) => {
    return (
      <>
        <tr
          className={activeComponent.includes("/dashboard/change-password") ? "table-active" : ""}
          data-testid={"changePassword"}
          onClick={() =>{
            history.push(`/` + role + `/dashboard/change-password`);
            setActiveComponent("/dashboard/change-password");
          }}
        >
          <td>
            <h6>Change Password</h6>
          </td>
        </tr>
      </>
    );
  };

  return (
    <>
      <div className="text-left tr-hover">
        <IfLoggedIn role={ROLE_CUSTOMER}>
          <h2>Customer Dashboard </h2>
        </IfLoggedIn>
        <IfLoggedIn role={ROLE_DEALER}>
          <h2>Dealer Dashboard </h2>
        </IfLoggedIn>
      </div>
      <hr />

      <div className="row ">
        <div className="col-md-2 col-xs-12">
          <div className="row">
            <table className="table table-hover tr-hover">
              <tbody>
                <IfLoggedIn role={ROLE_CUSTOMER}>
                  <tr
                    className={activeComponent.includes("/customer/dashboard/my-account") ? "table-active" : ""}
                    data-testid={"myAccount"}
                    onClick={() => {
                      history.push(`/customer/dashboard/my-account`);
                      setActiveComponent("/customer/dashboard/my-account")
                    }}
                  >
                    <td>
                      <h6>My Account </h6>
                    </td>
                  </tr>
                  <tr
                    className={activeComponent.includes("/customer/dashboard/profile/edit") ? "table-active" : ""}
                    data-testid={"editProfile"}
                    onClick={() => {
                      history.push(`/customer/dashboard/profile/edit`);
                      setActiveComponent("/customer/dashboard/profile/edit")
                    }}
                  >
                    <td>
                      <h6>Edit Profile</h6>
                    </td>
                  </tr>
                  <IfLoggedIn role={ROLE_CUSTOMER}>
                    {CommonOptions("customer")}
                  </IfLoggedIn>
                  <tr
                    className={activeComponent.includes("/customer/dashboard/vehicle-comparisons") ? "table-active" : ""}
                    data-testid={"savedComparisons"}
                    onClick={() => {
                      history.push(`/customer/dashboard/vehicle-comparisons`);
                      setActiveComponent("/customer/dashboard/vehicle-comparisons")
                    }}
                  >
                    <td>
                      <h6>Saved Comparisons</h6>
                    </td>
                  </tr>
                  <tr
                    className={activeComponent.includes("/customer/dashboard/favorite-vehicles") ? "table-active" : ""}
                    data-testid={"favoriteVehicles"}
                    onClick={() => {
                      history.push(`/customer/dashboard/favorite-vehicles`);
                      setActiveComponent("/customer/dashboard/favorite-vehicles")
                    }}
                  >
                    <td>
                      <h6>Favorite Vehicles</h6>
                    </td>
                  </tr>
                </IfLoggedIn>


                <IfLoggedIn role={ROLE_DEALER}>
                  <tr
                    className={activeComponent.includes("/dealer/dashboard/dealer-vehicles") ? "table-active" : ""}
                    data-testid={"dealerVehicles"}
                    onClick={() => {
                      history.push(`/dealer/dashboard/dealer-vehicles`);
                      setActiveComponent("/dealer/dashboard/dealer-vehicles")
                    }}
                  >
                    <td>
                      <h6>My Vehicles</h6>
                    </td>
                  </tr>
                  <tr
                    className={activeComponent.includes("/dealer/dashboard/dealer-accessories") ? "table-active" : ""}
                    data-testid={"dealerAccessories"}
                    onClick={() => {
                      history.push(`/dealer/dashboard/dealer-accessories`);
                      setActiveComponent("/dealer/dashboard/dealer-accessories")
                    }}
                  >
                    <td>
                      <h6>My Accessories</h6>
                    </td>
                  </tr>
                  <tr
                    className={activeComponent.includes("/dealer/dashboard/vehicles/add") ? "table-active" : ""}
                    data-testid={"addVehicles"}
                    onClick={() => {
                      history.push(`/dealer/dashboard/vehicles/add`);
                      setActiveComponent("/dealer/dashboard/vehicles/add");
                    }}
                  >
                    <td>
                      <h6>Add Vehicles</h6>
                    </td>
                  </tr>
                  <tr
                    className={activeComponent.includes("/dealer/dashboard/vehicles/upload-csv") ? "table-active" : ""}
                    data-testid={"uploadVehicles"}
                    onClick={() => {
                      history.push(`/dealer/dashboard/vehicles/upload-csv`);
                      setActiveComponent("/dealer/dashboard/vehicles/upload-csv");
                    }}
                  >
                    <td>
                      <h6>Upload Vehicles</h6>
                    </td>
                  </tr>
                  <tr
                    className={activeComponent.includes("/dealer/dashboard/feedbacks") ? "table-active" : ""}
                    data-testid={"feedback"}
                    onClick={() => {
                      history.push(`/dealer/dashboard/feedbacks`);
                      setActiveComponent("/dealer/dashboard/feedbacks");
                    }}
                  >
                    <td>
                      <h6>My Feedbacks</h6>
                    </td>
                  </tr>
                  <tr
                    className={activeComponent.includes("/dealer/dashboard/vehicles/upload-json") ? "table-active" : ""}
                    data-testid={"uploadAccessories"}
                    onClick={() => {
                      history.push(`/dealer/dashboard/vehicles/upload-json`);
                      setActiveComponent("/dealer/dashboard/vehicles/upload-json");
                    }}
                  >
                    <td>
                      <h6>Upload Accessories</h6>
                    </td>
                  </tr>
                  <tr
                    className={activeComponent.includes("/dealer/dashboard/vehicles/upload-success") ? "table-active" : ""}
                    data-testid={"uploadSuccess"}
                    onClick={() => {
                      history.push(`/dealer/dashboard/vehicles/upload-success`);
                      setActiveComponent("/dealer/dashboard/vehicles/upload-success");
                    }}
                  >
                    <td>
                      <h6>Uploaded Files</h6>
                    </td>
                  </tr>

                  <tr
                    className={activeComponent.includes("/dealer/dashboard/add-accessory") ? "table-active" : ""}
                    data-testid={"addAccessory"}
                    onClick={() => {
                      history.push(`/dealer/dashboard/add-accessory`);
                      setActiveComponent("/dealer/dashboard/add-accessory");
                    }}
                  >
                    <td>
                      <h6>Add Accessory</h6>
                    </td>
                  </tr>
                  <IfLoggedIn role={ROLE_DEALER}>
                    {CommonOptions("dealer")}
                  </IfLoggedIn>
                </IfLoggedIn>
              </tbody>
            </table>
          </div>
        </div>
        <div className="col-md-1 col-xs-12"></div>

        <div className="col-md-9 col-xs-12 mb-5">
          <Suspense fallback={<PleaseWait />}>
            <Switch>
              <Route
                path="/customer/dashboard/my-account"
                exact={true}
                component={CustomerProfile}
              />
              <Route
                path="/customer/dashboard/profile/edit"
                component={UpdateCustomer}
              />
              <Route
                path="/customer/dashboard/change-password"
                component={ChangePasswordForm}
              />
              <Route
                path="/customer/dashboard/vehicle-comparisons"
                component={ComparisonMatrixMetadata}
              />
              <Route path="/customer/dashboard/favorite-vehicles">
                <FavoriteVehicles />
              </Route>

              {/* <Route
              path="/dealer/dashboard/profile/edit"
              component={UpdateCustomer}
            /> */}
              <Route
                path="/dealer/dashboard/change-password"
                component={ChangePasswordForm}
              />
              <Route
                path="/dealer/dashboard/vehicles/add"
                component={AddVehicle}
              />
              <Route
                path="/dealer/dashboard/dealer-vehicles"
                component={DealerVehicles}
              />
              <Route
                path="/dealer/dashboard/vehicles/upload-csv"
                component={UploadPage}
              />
              <Route
                path="/dealer/dashboard/vehicles/upload-json"
                component={UploadJson}
              />
              <Route
                path="/dealer/dashboard/vehicles/upload-success"
                component={DisplayDealerUploads}
              />
              <Route
                path="/dealer/dashboard/dealer-accessories"
                component={DealerAccessories}
              />
              <Route
                path="/dealer/dashboard/feedbacks"
                component={DealerReviews}
              />
              <Route
                path="/dealer/dashboard/add-accessory"
                component={AddAccessory}
              />
            </Switch>
          </Suspense>
        </div>
      </div>
    </>
  );
}
