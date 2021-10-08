/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import { screen, cleanup, fireEvent, render } from "@testing-library/react";
import { createMemoryHistory } from "history";
import configureStore from "redux-mock-store";
import { Provider } from "react-redux";
import { Router } from "react-router";
import thunk from "redux-thunk";

import DealerAccessoriesListItem from "./DealerAccessoriesListItem";

describe("DealerAccessoriesListItem", () => {
  const middlewares = [thunk];
  const mockStore = configureStore(middlewares);

  afterEach(cleanup);

  it("should render Dealer Accessories List Item", () => {
    const history = createMemoryHistory();
    const initialState = {
      dealerAccessoriesReducer: {
        isFirst: true,
        isLast: false,
        accessories: [],
      },
    };

    const accessory = {
      id: "61121ad598f95c2cba086f6",
      pictureUrls: [
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2019-honda-civic-sedan-1558453497.jpg?crop=1xw:0.9997727789138833xh;center,top&resize=480:*",
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2019-honda-civic-sedan-1558453497.jpg?crop=1xw:0.9997727789138833xh;center,top&resize=480:*",
      ],
      description: "This is an accessory",
      price: 918231.0,
      name: "BMW",
    };

    let store = mockStore(initialState);

    render(
      <Provider store={store}>
        <Router history={history}>
          <DealerAccessoriesListItem accessory={accessory} />
        </Router>
      </Provider>
    );

    const accessoryDetails = screen.getByTestId("accessory-details");
    const accessoryImg = screen.getByTestId("accessory-img");
    const accessoryLink = screen.getAllByTestId("accessory-link");
    const accessoryEditLink = screen.getAllByTestId("accessory-edit-link");

    accessoryLink.forEach((al) => al.click());
    accessoryEditLink.forEach((al) => al.click());

    expect(accessoryDetails).toBeInTheDocument();
    expect(accessoryImg).toBeInTheDocument();
  });

  it("should delete the accessory successully", () => {
    const history = createMemoryHistory();
    const initialState = {
      dealerAccessoriesReducer: {
        isFirst: true,
        isLast: false,
        accessories: [],
      },
    };

    const accessory = {
      id: "61121ad598f95c2cba086f6",
      pictureUrls: [
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2019-honda-civic-sedan-1558453497.jpg?crop=1xw:0.9997727789138833xh;center,top&resize=480:*",
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2019-honda-civic-sedan-1558453497.jpg?crop=1xw:0.9997727789138833xh;center,top&resize=480:*",
      ],
      description: "This is an accessory",
      price: 918231.0,
      name: "BMW",
    };

    let store = mockStore(initialState);

    const { container } = render(
      <Provider store={store}>
        <Router history={history}>
          <DealerAccessoriesListItem accessory={accessory} />
        </Router>
      </Provider>
    );

    const deleteBtn = screen.getByTestId("delete-btn");
    fireEvent.click(deleteBtn);
    expect(container).not.toBeNull();
  });
});
