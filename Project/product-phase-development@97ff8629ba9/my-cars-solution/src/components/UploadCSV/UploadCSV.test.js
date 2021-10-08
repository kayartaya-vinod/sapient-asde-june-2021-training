import { cleanup, fireEvent, render, screen, waitFor } from "@testing-library/react";
import UploadCSV from "./UploadCSV";
import axiosMock from 'axios';

jest.mock('axios');

describe("Upload file tests", () => {
    let file;
    afterEach(() => {
        cleanup();
    });
    it("button click", async () => {
      const { findByTestId } = render(<UploadCSV />);
      fireEvent.click(await findByTestId("submitButton"));
      await waitFor(() => {
        expect(
          screen.getByText(/Select a file before uploading/i)
        ).toBeTruthy();
      });
    });

    it("select file", async () => {
        file = new File(["(⌐□_□)"], "chucknorris.png", { type: "image/png" });
    // render the component
    const { findAllByText,getByTestId } = render(<UploadCSV />);

    // get the upload button
    let uploader = getByTestId("uploadFile");

    // simulate ulpoad event and wait until finish
    await waitFor(() =>
        fireEvent.change(uploader, {
        target: { files: [file] },
        })
        );

    // check if the file is there
        expect(await findAllByText("chucknorris.png")).toBeTruthy();
    });


 
    it("successfully submit file for selected file", async () => {
      jest.fn().mockReturnValue("File uploaded successfully");
      file = new File(["(⌐□_□)"], "chucknorris.png", { type: "image/png" });
      // render the component
      const { getByTestId, findAllByText } = render(<UploadCSV />);

      // get the upload button
      let uploader = getByTestId("uploadFile");

      // simulate ulpoad event and wait until finish
      await waitFor(() =>
        fireEvent.change(uploader, {
          target: { files: [file] },
        })
      );
      var data = {
          "date-time": "55.55.55",
          "document-uploaded": "55",
          "message": "uploaded successfully",
          "Success": true,
          "upload-ratio": "100%"
      };
    
    axiosMock.post.mockResolvedValueOnce({
      data: data
    });

        fireEvent.click(getByTestId("submitButton"));
        await waitFor(() => { expect(screen.getByText(/uploaded successfully/i)).toBeTruthy();});
        
    });

    it("success false submit file for selected file", async () => {
      jest.fn().mockReturnValue("File uploaded successfully");
      file = new File(["(⌐□_□)"], "chucknorris.png", { type: "image/png" });
      // render the component
      const { getByTestId, findAllByText } = render(<UploadCSV />);

      // get the upload button
      let uploader = getByTestId("uploadFile");

      // simulate ulpoad event and wait until finish
      await waitFor(() =>
        fireEvent.change(uploader, {
          target: { files: [file] },
        })
      );
      var data = {
          "date-time": "55.55.55",
          "document-uploaded": "55",
          "message": "uploaded successfully",
          "Success": false,
          "upload-ratio": "100%"
      };
    
    axiosMock.post.mockResolvedValueOnce({
      data: data
    });

        fireEvent.click(getByTestId("submitButton"));
        await waitFor(() => { expect(screen.getByText(/uploaded successfully/i)).toBeTruthy();});
        
    });



    it("submit file for selected file", async () => {
      jest.fn().mockReturnValue("File uploaded successfully");
      file = new File(["(⌐□_□)"], "chucknorris.png", { type: "image/png" });
      // render the component
      const { getByTestId, findByTestId } = render(<UploadCSV />);

      // get the upload button
      let uploader = getByTestId("uploadFile");

      // simulate ulpoad event and wait until finish
      await waitFor(() =>
        fireEvent.change(uploader, {
          target: { files: [file] },
        })
      );

    
        axiosMock.post.mockResolvedValueOnce({
          data: "file upload failed"
        });

    fireEvent.click(getByTestId("submitButton"));
        await waitFor(() => {
          expect(screen.getByText(/file upload failed/i)).toBeTruthy();
        });
        
    });

    it("close button for message box for submit failed", async () => {
      jest.fn().mockReturnValue("File uploaded successfully");
      file = new File(["(⌐□_□)"], "chucknorris.png", { type: "image/png" });
      // render the component
      const { getByTestId, findAllByText } = render(<UploadCSV />);

      // get the upload button
      let uploader = getByTestId("uploadFile");

      // simulate ulpoad event and wait until finish
      await waitFor(() =>
        fireEvent.change(uploader, {
          target: { files: [file] },
        })
      );

      axiosMock.post.mockResolvedValueOnce({
        data: "file upload failed",
      });

        await fireEvent.click(getByTestId("submitButton"));
        await waitFor(async () => {
            fireEvent.click(getByTestId("close-button"));
        });
           expect(await findAllByText("Select a CSV file")).toBeTruthy();
        
    });


    it("close button for message box for successful submit", async () => {
      jest.fn().mockReturnValue("File uploaded successfully");
      file = new File(["(⌐□_□)"], "chucknorris.png", { type: "image/png" });
      // render the component
      const { getByTestId, findAllByText } = render(<UploadCSV />);

      // get the upload button
      let uploader = getByTestId("uploadFile");

      // simulate ulpoad event and wait until finish
      await waitFor(() =>
        fireEvent.change(uploader, {
          target: { files: [file] },
        })
      );

      var data = {
        "date-time": "55.55.55",
        "document-uploaded": "55",
        message: "uploaded successfully",
        Success: "true",
        "upload-ratio": "100%",
      };

      axiosMock.post.mockResolvedValueOnce({
        data: data,
      });

        await fireEvent.click(getByTestId("submitButton"));
        await waitFor(async () => {
            fireEvent.click(getByTestId("close-button"));
        });
           expect(await findAllByText("Select a CSV file")).toBeTruthy();
    });
    
});
