import { fireEvent } from "@testing-library/react"
import { renderWithReduxAndRouter } from "../../utils/testUtils"
import SaveComparisonMatrix from "./SaveComparisonMatrix"
import axiosMock from "axios"

jest.mock("axios");

describe("SaveComparisonMatrix Test", () => {
    it("should render SaveComparisonMatrix", () => {
        const { container } = renderWithReduxAndRouter(<SaveComparisonMatrix />)
        expect(container).not.toBeNull();
    })
    xit("should click save button", () => {
        const { getByTestId } = renderWithReduxAndRouter(<SaveComparisonMatrix />)
        fireEvent.click(getByTestId("save"))
        expect(getByTestId("save")).not.toBeNull();
    })
    xit("should check error", () => {
        axiosMock.get.mockResolvedValueOnce({
            data: {
                error: "Network Error",
            }
        })
        const { getByTestId } = renderWithReduxAndRouter(<SaveComparisonMatrix />)
        fireEvent.click(getByTestId("save"))
        expect(getByTestId("save")).not.toBeNull();
    })

})