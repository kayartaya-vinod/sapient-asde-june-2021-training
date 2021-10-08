/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
import { uploadFile } from "./uploadJsonActionCreator";
import axiosMock from "axios";
import {
    UPLOAD_JSON,
    UPLOAD_JSON_ERROR
} from "../../actionTypes";

jest.mock("axios");

describe("UploadJsonActionCreator", () => {
  it("should return success for UploadJson", async () => {
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
      type: UPLOAD_JSON,
      payload: { data:{success: true} },
    });
  });

  it("should return error for UploadJson", async () => {

    axiosMock.post.mockRejectedValueOnce({
      response: {
        data:{
          message: "format is wrong",
        },
        success: true,
      },
    });

    expect(await uploadFile()).toEqual({
      type: UPLOAD_JSON_ERROR,
      payload: {
        message: "format is wrong"
      },
    });
  });
  it("should return network error for UploadJson", async () => {

    axiosMock.post.mockRejectedValueOnce({
      message:"Network Error"
    });

    expect(await uploadFile()).toEqual({
      type: UPLOAD_JSON_ERROR,
      payload: 
       "Network Error",
      
    });
  });
  
});
