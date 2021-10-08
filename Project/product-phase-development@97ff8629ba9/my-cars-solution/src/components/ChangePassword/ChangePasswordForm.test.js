import { screen, cleanup, waitFor, fireEvent, render } from "@testing-library/react";
import ChangePasswordForm from "./ChangePasswordForm";
import axiosMock from "axios";
import { createMemoryHistory } from "history";
import configureStore from "redux-mock-store";
import { Provider } from "react-redux";
import { Router } from "react-router";
import thunk from 'redux-thunk';


jest.mock("axios");

describe("ChangePasswordForm ", () => {
    const middlewares = [thunk];
    const mockStore = configureStore(middlewares);

    const history = createMemoryHistory();
    afterEach(cleanup);

    it("should render Change Password Form", () => {
        const initialState = {
            passwordChangeReducer: {
                isPasswordChanged: false,
                error: ""
            }
        };
        let store = mockStore(initialState);

        render(
            <Provider store={store}>
                <Router history={history}>
                    <ChangePasswordForm />
                </Router>
            </Provider>
        );

        const submitBtn = screen.getByTestId("btn-submit");
        const oldPassword = screen.getByTestId("old_password");
        const newPassword = screen.getByTestId("password");
        const confirmPassword = screen.getByTestId("confirm_password");

        expect(submitBtn).toBeInTheDocument();
        expect(oldPassword).toBeInTheDocument();
        expect(newPassword).toBeInTheDocument();
        expect(confirmPassword).toBeInTheDocument();

    });

    it("should change password ", async () => {
        const initialState = {
            passwordChangeReducer: {
                isPasswordChanged: false,
                error: ""
            }
        };
        let store = mockStore(initialState);

        const payload = {
            oldPassword: "old-password",
            newPassword: "new-password",
            confirmPassword: "new-password"
        };

        axiosMock.post.mockResolvedValueOnce({
            data: {
                message: "SUCCESS",
            },
        });

        render(
            <Provider store={store}>
                <Router history={history}>
                    <ChangePasswordForm />
                </Router>
            </Provider>
        );


        const submitBtn = screen.getByTestId("btn-submit");
        const oldPassword = screen.getByTestId("old_password");
        const newPassword = screen.getByTestId("password");
        const confirmPassword = screen.getByTestId("confirm_password");

        fireEvent.change(oldPassword, {
            target: {
                value: payload.oldPassword
            }
        });
        fireEvent.change(newPassword, {
            target: {
                value: payload.newPassword
            }
        });
        fireEvent.change(confirmPassword, {
            target: {
                value: payload.confirmPassword
            }
        });

        fireEvent.click(submitBtn);



        waitFor(() => {
            const alert = screen.getByTestId("alert");
            expect(alert).toBeInTheDocument();
            expect(alert).toContain(/password has been changed/);
            setTimeout(() => {
                expect(alert).toNotBeInTheDocument();
            }, 4000);
            expect(oldPassword).toContain("");
            expect(newPassword).toContain("");
            expect(confirmPassword).toContain("");
        });


    });


    it("should show alert in case the entered passwords don't match", async () => {
        const initialState = {
            passwordChangeReducer: {
                isPasswordChanged: false,
                error: ""
            }
        };
        let store = mockStore(initialState);

        const payload = {
            oldPassword: "old-password",
            newPassword: "new-password",
            confirmPassword: "different-password"
        };


        axiosMock.post.mockResolvedValueOnce({
            data: {
                message: "SUCCESS",
            },
        });

        render(
            <Provider store={store}>
                <Router history={history}>
                    <ChangePasswordForm />
                </Router>
            </Provider>
        );


        const submitBtn = screen.getByTestId("btn-submit");
        const oldPassword = screen.getByTestId("old_password");
        const newPassword = screen.getByTestId("password");
        const confirmPassword = screen.getByTestId("confirm_password");

        fireEvent.change(oldPassword, {
            target: {
                value: payload.oldPassword
            }
        });
        fireEvent.change(newPassword, {
            target: {
                value: payload.newPassword
            }
        });
        fireEvent.change(confirmPassword, {
            target: {
                value: payload.confirmPassword
            }
        });

        fireEvent.click(submitBtn);



        waitFor(() => {
            const alert = screen.getByTestId("alert");
            expect(alert).toBeInTheDocument();
            expect(alert).toContain(/passwords don't match/);
        });
    });

    it("should show alert in case if any value is not entered", async () => {
        const initialState = {
            passwordChangeReducer: {
                isPasswordChanged: false,
                error: ""
            }
        };
        let store = mockStore(initialState);

        axiosMock.post.mockResolvedValueOnce({
            data: {
                message: "SUCCESS",
            },
        });

        render(
            <Provider store={store}>
                <Router history={history}>
                    <ChangePasswordForm />
                </Router>
            </Provider>
        );


        const submitBtn = screen.getByTestId("btn-submit");
        const oldPassword = screen.getByTestId("old_password");
        const newPassword = screen.getByTestId("password");
        const confirmPassword = screen.getByTestId("confirm_password");

        fireEvent.change(oldPassword, {
            target: {
                value: ""
            }
        });
        fireEvent.change(newPassword, {
            target: {
                value: ""
            }
        });
        fireEvent.change(confirmPassword, {
            target: {
                value: ""
            }
        });

        fireEvent.click(submitBtn);



        waitFor(() => {
            const alert = screen.getByTestId("alert");
            expect(alert).toBeInTheDocument();
            expect(alert).toContain(/please enter all details/);
        });
    });

});
