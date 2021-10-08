import { uploadFile } from "./uploadCSVactionCreator";
import axiosMock from "axios";
import {
    UPLOAD_CSV,
    UPLOAD_CSV_ERROR
} from "../../actionTypes";

jest.mock("axios");

describe("UploadCsvActionCreator", () => {
  it("should return success for UploadCSV", async () => {
    axiosMock.post.mockResolvedValueOnce({
      data: {
        data: {
          success: true,
        },
      },
    });
    let file;
    file = new File(["(⌐□_□)"], "chucknorris.png", { type: "image/png" });
    expect(await uploadFile(file)).toEqual({
      type: UPLOAD_CSV,
      payload: { data:{success: true} },
    });
  });

  it("should return error for UploadCSV", async () => {

    axiosMock.post.mockRejectedValue({
      response: {
        data: "format is wrong",
        success: true,
      },
    });

    expect(await uploadFile()).toEqual({
      type: UPLOAD_CSV_ERROR,
      payload: "format is wrong",
    });
  });

  
});
