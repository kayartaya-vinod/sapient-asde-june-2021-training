import {  fireEvent, render } from "@testing-library/react"
import ModalAttributes from "./ModalAttributes";


describe("Modal Attribute tests", () => {
    
    it("should download sample.csv file", async () => {
        const { getByTestId } = render(<ModalAttributes />);
        
        expect(getByTestId("sampleDownload").getAttribute("href")).toBe("sampleCSV.csv");
    });

    it("should show instruction", async () => {
        const { getByTestId,findByText } = render(<ModalAttributes />);
        fireEvent.click(getByTestId("instruction"));
        expect(await findByText("Instructions")).toBeTruthy();
    });
    
    it("should close button work",async () => {
        const { getByTestId, findByText } = render(<ModalAttributes />);
        fireEvent.click(getByTestId("instruction"));
        fireEvent.click(getByTestId("closeButton"));
        expect(await findByText("Instructions")).toBeTruthy();
    });
});