import reducer from "./passwordChangeReducer";
import { PASSWORD_SUCCESS, PASSWORD_FAILED, RESET_CHANGE_PASSWORD_STORE } from "../../actionTypes";

describe('passwordChangeReducer test', () => {
    test('should return the initial state', () => {
        expect(reducer(undefined, {})).toEqual({ isPasswordChanged: false, error: "" });
    });

    test('should return the  change password success', () => {
        const previousState = { isPasswordChanged: false, error: "" };
        expect(reducer(previousState, { type: PASSWORD_SUCCESS })).toEqual({ isPasswordChanged: true, error: "" });
    });

    test('should handle the password change failure ', () => {
        const previousState = { isPasswordChanged: false, error: "" };
        expect(reducer(previousState, { type: PASSWORD_FAILED, payload: { error: "error" } })).toEqual({ isPasswordChanged: false, error: "error" });
    });

    test('should reset the change password strore ', () => {
        const previousState = { isPasswordChanged: true, error: "error" };
        expect(reducer(previousState, { type: RESET_CHANGE_PASSWORD_STORE })).toEqual({ isPasswordChanged: false, error: "" });
    });
});