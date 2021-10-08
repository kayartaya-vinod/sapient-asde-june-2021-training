/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
import { screen, render, fireEvent } from "@testing-library/react";
import PictureUrlsField from "./PictureUrlsField";

describe("PictureUrlsField component test", () => {
  it("should render Input field", () => {
    render(<PictureUrlsField type="text" attributeName="testing" />);
    expect(screen.getByTestId(/fieldInput/i)).toBeInTheDocument();
    expect(screen.getByText(/Please click/i)).toBeInTheDocument();
  });

  it("should render error if input box is empty and add button is clicked", () => {
    const { getByTestId, getByText } = render(
      <PictureUrlsField type="text" attributeName="testing" />
    );
    const addBtn = getByTestId("addBtn");
    expect(addBtn).toBeInTheDocument();
    fireEvent.click(addBtn);
    expect(
      getByText(/Please fill all fields first to add more!/)
    ).toBeInTheDocument();
    setTimeout(() => {
      expect(
        getByText(/Please fill all fields first to add more!/)
      ).not.toBeInTheDocument();
    }, 2000);
  });

  it("should render add input box when add button is clicked", () => {
    const { getByTestId } = render(
      <PictureUrlsField type="text" attributeName="testing" />
    );
    const addBtn = getByTestId("addBtn");
    const firstInputBox = getByTestId("Testinput-0");
    expect(addBtn).toBeInTheDocument();
    fireEvent.change(firstInputBox, { target: { value: "testing" } });
    fireEvent.blur(firstInputBox);
    fireEvent.click(addBtn);
    const secondInputBox = getByTestId("Testinput-1");
    expect(secondInputBox).toBeInTheDocument();
  });

  it("should render delete input box when delete button is clicked", () => {
    const { getByTestId } = render(
      <PictureUrlsField type="text" attributeName="testing" />
    );
    const addBtn = getByTestId("addBtn");
    const firstInputBox = getByTestId("Testinput-0");
    expect(addBtn).toBeInTheDocument();
    fireEvent.change(firstInputBox, { target: { value: "testing" } });
    fireEvent.blur(firstInputBox);
    fireEvent.click(addBtn);
    const secondInputBox = getByTestId("Testinput-1");
    expect(secondInputBox).toBeInTheDocument();
    const deleteBtn = getByTestId("deleteBtn");
    fireEvent.click(deleteBtn);
    expect(secondInputBox).not.toBeInTheDocument();
  });

  it("should render success message submit button is clicked", () => {
    const testfn = (url) => {
      return;
    };
    const { getByTestId, getByText } = render(
      <PictureUrlsField type="text" attributeName="testing" onDone={testfn} />
    );
    const addBtn = getByTestId("addBtn");
    const firstInputBox = getByTestId("Testinput-0");
    expect(addBtn).toBeInTheDocument();
    fireEvent.change(firstInputBox, { target: { value: "testing" } });
    fireEvent.blur(firstInputBox);

    const doneBtn = getByTestId("doneBtn");
    expect(doneBtn).toBeInTheDocument();
    fireEvent.click(doneBtn);
    expect(getByText(/Saved Successfully/)).toBeInTheDocument();
  });

  it("should render error message submit button is clicked with empty input", () => {
    const testfn = (url) => {
      return;
    };
    const { getByTestId, getByText } = render(
      <PictureUrlsField type="text" attributeName="testing" onDone={testfn} />
    );
    const addBtn = getByTestId("addBtn");
    expect(addBtn).toBeInTheDocument();

    const doneBtn = getByTestId("doneBtn");
    expect(doneBtn).toBeInTheDocument();
    fireEvent.click(doneBtn);
    expect(
      getByText(/Please fill all fields or delete extra one/)
    ).toBeInTheDocument();
  });
});
