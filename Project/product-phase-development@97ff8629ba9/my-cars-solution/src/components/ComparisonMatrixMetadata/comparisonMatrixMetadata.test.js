/**
@Author <Aditya Gheewala> <aditya.gheewala@publicissapient.com>
*/
import { screen, cleanup, waitFor, fireEvent } from "@testing-library/react";
import axiosMock from "axios";
import ComparisonMatrixMetadata from "./ComparisonMatrixMetadata";
import { renderWithReduxAndRouter } from "../../utils/testUtils";
import {downloadMatrix } from '../../redux/actionCreators/matrixMetadataActionCreator/matrixMetadataActionCreator';


jest.mock("axios");

describe("Comparison Matrix Metadata Test", () => {
    afterEach(cleanup);

    const mockData = {
        data: {data: [
            {
                "id": "hjasghdakjshdkj",
                "userId": "hgfhzgvcx65c4xc",
                "vehicleIds": ["id1", "id2"],
                "comparisonMatrixName": "Name1",
                "createdDate": "1/1/1"
            },
            {
                "id": "hjasghdakjshdkjk",
                "userId": "hgfhzgvcx65c4xc",
                "vehicleIds": ["id1", "id2"],
                "comparisonMatrixName": "Name2",
                "createdDate": "1/1/1"
            },
            {
                "id": "hjasghdakjshdkjl",
                "userId": "hgfhzgvcx65c4xc",
                "vehicleIds": ["id1", "id2"],
                "comparisonMatrixName": "Name3",
                "createdDate": "1/1/1"
            }
        ]},
    };

    it("should render Matrix Metadata", async () => {

        axiosMock.get.mockResolvedValueOnce(mockData);

        renderWithReduxAndRouter(
            <ComparisonMatrixMetadata />
        );

        await waitFor(() => {
            expect(screen.getByText(/Name1/i)).toBeInTheDocument();
        });
    });


    it("should show no saved comparisons", async () => {

        axiosMock.get.mockResolvedValueOnce({ data:{data:[]}});

        renderWithReduxAndRouter(
            <ComparisonMatrixMetadata />
        );

        await waitFor(() => {
            expect(screen.getAllByText(/No saved comparisons found/i)).toBeInTheDocument;
        });
    });


    it("should delete a comparison on click", async () => {

        axiosMock.get.mockResolvedValueOnce(mockData);

        renderWithReduxAndRouter(
            <ComparisonMatrixMetadata />
        );

        let length = 0;
        await waitFor(() => {
            expect(screen.getByTestId(/btn-delete0/i)).toBeInTheDocument();
            const deleteBtn = screen.getByTestId('btn-delete0');
            length = screen.getAllByTestId('row').length;
            fireEvent.click(deleteBtn);
            const modalDeleteBtn = screen.getByTestId('modalDeleteBtn');
            fireEvent.click(modalDeleteBtn);
        });

        expect(screen.getAllByTestId('row')).toHaveLength(length - 1);
    });

    it("should have a download button and click", async () => {

        axiosMock.get.mockResolvedValueOnce(mockData);
        
        renderWithReduxAndRouter(
            <ComparisonMatrixMetadata />
            );
            
        axiosMock.get.mockResolvedValueOnce({data: ""});
        window.webkitURL.createObjectURL = jest.fn(() => '');
        await waitFor(() => {
            expect(screen.getByTestId(/btn-download0/i)).toBeInTheDocument();
            const downloadBtn = screen.getByTestId('btn-download0');
            fireEvent.click(downloadBtn);
        });
    });

    it("should click on name", async () => {

        axiosMock.get.mockResolvedValueOnce(mockData);
        
        renderWithReduxAndRouter(
            <ComparisonMatrixMetadata />
            );

        await waitFor(() => {
            expect(screen.getByTestId(/index0/i)).toBeInTheDocument();
            const btn = screen.getByTestId('index0');
            fireEvent.click(btn);
        });
        await waitFor(() => {
            expect(screen.getByTestId(/name0/i)).toBeInTheDocument();
            const btn = screen.getByTestId('name0');
            fireEvent.click(btn);
        });
        await waitFor(() => {
            expect(screen.getByTestId(/date0/i)).toBeInTheDocument();
            const btn = screen.getByTestId('date0');
            fireEvent.click(btn);
        });
        await waitFor(() => {
            expect(screen.getByTestId(/size0/i)).toBeInTheDocument();
            const btn = screen.getByTestId('size0');
            fireEvent.click(btn);
        });

    });

});
