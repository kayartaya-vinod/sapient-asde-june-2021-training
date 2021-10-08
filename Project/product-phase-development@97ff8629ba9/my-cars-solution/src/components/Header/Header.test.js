import { cleanup, fireEvent, screen } from "@testing-library/react";
import Header from "./Header";
import { renderWithReduxAndRouter } from "../../utils/testUtils";
import configureStore from "redux-mock-store";
import { Provider } from "react-redux";

describe("Header test", () => {
  const mockStore = configureStore();
  afterEach(cleanup);

  it("should render Header", () => {
    renderWithReduxAndRouter(<Header />);
    expect(screen.getByTestId(/home-link/i)).toBeInTheDocument();
  });

  it("should render Header with Login link when not logged in", () => {
    const initialState = {
      authReducer: { isLoggedIn: false },
    };
    let store = mockStore(initialState);
    renderWithReduxAndRouter(
      <Provider store={store}>
        <Header />
      </Provider>
    );
  });
});
