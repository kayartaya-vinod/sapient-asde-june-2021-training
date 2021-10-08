import { screen, cleanup, render, fireEvent } from "@testing-library/react";
import configureStore from "redux-mock-store";
import { Provider } from "react-redux";
import { BrowserRouter } from "react-router-dom";
import UpdatedVehicleComparisonMatrix from "./UpdatedVehicleComparisonMatrix";

const contents = {
      "basicInformation": {
        "brand": ["Honda", "Honda", "Honda"],
        "model" :["c100","d100","r560"],
        "price": [918231.0,null,true],

    }}
    const idss = ["id1"];
    const imagess = ["img1.jpg"];
jest.mock("axios"); 
const mockStore = configureStore();
afterEach(cleanup);
describe("UpdateVehicleComparisonMatrix test", () => {
  it("should render UpdateVehicleComparisonMatrix", () => {
     const initialState = {
       vehicleObjectCompareReducer: {
         content: contents,
         ids: idss,
         images: imagess,
       },
       vehicleCompareListReducer: ["id1","id2"],
       authReducer: {
         isLoggedIn: false,
         token: null,
         user: { id: "", name: "", email: "", role: "" },
       },
     };
     let store = mockStore(initialState);
    const { getByTestId } = render(
      <Provider store={store}>
        <BrowserRouter>
          <UpdatedVehicleComparisonMatrix />
        </BrowserRouter>
      </Provider>
    );
    expect(screen.getByText(/honda-c100/i)).toBeInTheDocument();

    fireEvent.click(screen.getByTestId("remove0"));
  });
});