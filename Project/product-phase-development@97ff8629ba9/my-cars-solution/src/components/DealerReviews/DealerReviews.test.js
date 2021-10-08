
/**
@author Neha neha1@publicisssapient.com
*/
import { screen, cleanup, render } from "@testing-library/react";
import { createMemoryHistory } from "history";
import configureStore from "redux-mock-store";
import { Provider } from "react-redux";
import { Router } from "react-router";
import thunk from "redux-thunk";

import axiosMock from "axios";

import DealerReviews from "./DealerReviews";

jest.mock("axios");

describe("DealerReviews", () => {
    const middlewares = [thunk];
    const mockStore = configureStore(middlewares);
    const origConsole = console.error;
    beforeEach(() => {
        console.error = jest.fn();
    });
    afterEach(() => {
        console.error = origConsole;
    });


    afterEach(cleanup);



    const review = {


        vehicleName: "C200",
        Feedback: {
            id: "6125342e430d19c28b31098d",
            vehicleId: "1",
            userId: "612497d6638c3a92700092d1",
            review: "This is a comfortable car.Price is reasonable. This is a comfortable car.Price is reasonable.This is a comfortable car.Price is reasonable.This is a comfortable car.Price is reasonable.This is a comfortable car.Price is reasonable.This is a comfortable car.Price is reasonable",
            rating: 4
        },
        userName: "user300001 300001"
    };

    it("should render Dealer Vehicle Header", () => {
        axiosMock.get.mockResolvedValueOnce();

        const history = createMemoryHistory();
        const initialState = {
            dealerReviewReducer: {
                isFirst: false,
                isLast: false,
                data: [review],

            },
        };

        let store = mockStore(initialState);

        render(
            <Provider store={store}>
                <Router history={history}>
                    <DealerReviews />
                </Router>
            </Provider>
        );



        const previousBtn = screen.getByTestId("previous-btn");
        const nextBtn = screen.getByTestId("next-btn");
        expect(previousBtn).toBeInTheDocument();
        expect(nextBtn).toBeInTheDocument();

    });
});