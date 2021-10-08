/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
import { cleanup, fireEvent, render, screen, waitFor } from "@testing-library/react";
import UploadJson from "./UploadJson";
import axiosMock from "axios";
jest.mock("axios");

describe("Upload file tests", () => {
    let file;
    afterEach(cleanup);
    it("button click", async () => {
      const { findByTestId } = render(<UploadJson />);
      fireEvent.click(await findByTestId("submitButton"));
      await waitFor(() => {
        expect(
          screen.getByText(/Select a file before uploading/i)
        ).toBeTruthy();
      });
    });

    it("Upload file", async () => {
        file = new File(["(⌐□_□)"], "chucknorris.png", { type: "image/png" });
        const { findByText,getByTestId } = render(<UploadJson />);
        let uploader = getByTestId("uploadFile");
        await waitFor(() =>
            fireEvent.change(uploader, {
            target: { files: [file] },
            })
        );
        expect(await findByText("chucknorris.png")).toBeTruthy();
    });

    it("File uploaded successfully", async () => {
        jest.fn().mockReturnValue("File uploaded successfully");
        file = new File(["(⌐□_□)"], "chucknorris.png", { type: "image/png" });
        axiosMock.post.mockResolvedValueOnce({
                    message:"File uploaded successfully"
        });
        const { getByTestId,findByTestId } = render(<UploadJson />);
        let uploader =getByTestId("uploadFile");
        await waitFor(() =>
            fireEvent.change(uploader, {
            target: { files: [file] },
            })
        );
            fireEvent.click(getByTestId("submitButton"));
            expect(await findByTestId("msg")).not.toBeNull();
    });

    it("File upload Aborted", async () => {
        jest.fn().mockReturnValue("File uploaded successfully");
        file = new File(["(⌐□_□)"], "chucknorris.png", { type: "image/png" });
        axiosMock.post.mockRejectedValueOnce(
                 "File Upload Aborted"
        );
            const { getByTestId, findByTestId } = render(<UploadJson />);
            let uploader = getByTestId("uploadFile");
            await waitFor(() =>
                fireEvent.change(uploader, {
                    target: { files: [file] },
                })
            );
            fireEvent.click(getByTestId("submitButton"));
            expect(await findByTestId("msg")).not.toBeNull();
    });

    
});