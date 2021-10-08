/**
# @Author Yogamber Singh Negi yogamber.negi@publicissapient.com

@Author Hrishant Raj hrishant.raj@publicissapient.com  //latest build

 @Author Aditya Gheewala aditya.gheewala@publicissapient.com [select option test]
# */
import { fireEvent, screen, cleanup, waitFor } from "@testing-library/react";
import { renderWithRouter } from "../../utils/testUtils";
import SearchBar from "./SearchBar";
import { createMemoryHistory } from "history";
import "@testing-library/jest-dom/extend-expect";

const mockDispatch = jest.fn();
const history = createMemoryHistory();

jest.mock("react-redux", () => ({
  useDispatch: () => mockDispatch,
}));

jest.mock("axios");
describe("SearchBar Testing", () => {
  afterEach(cleanup);

  it("should render Search", async () => {
    renderWithRouter(<SearchBar />);
    const el = screen.getByTestId(/search/i);

    expect(el).toBeInTheDocument();
  });

  it("should render change input filed on input change", async () => {
    renderWithRouter(<SearchBar />);
    const el = screen.getByTestId(/search/i);
    fireEvent.change(await el, { target: { value: "Hello" } });
    expect(await el).toHaveValue("Hello");
  });
  
  it("should change the url to connect path", async () => {
    const handleSubmit = jest.fn();
    renderWithRouter(
      <SearchBar handleSubmit={handleSubmit} />
    );
    const el = screen.getByTestId(/search/i);
    fireEvent.change(await el, { target: { value: "Hello" } });


    fireEvent.submit(el);

    expect(window.location.href).toEqual("http://localhost/");
  });

  it("should click advance search button and move to advance search page", async () => {
    renderWithRouter(<SearchBar />);

    const btn = screen.getByTestId(/btn/i);
    fireEvent.click(btn);
    expect(history.location.pathname).toBe("/");
  });

  it("should change select option value", async () => {
    renderWithRouter(<SearchBar />);

    const select = screen.getAllByTestId(/select/i)[0];
    await waitFor(() => {
      fireEvent.change(select, { target: { value: "accessories" } });
    });
  });


  it("should render Search with initial value", async () => {
    renderWithRouter(<SearchBar searchText={"text"} type={"accessories"}/>);
    const el = screen.getByTestId(/search/i);

    expect(el).toBeInTheDocument();

    const select = screen.getAllByTestId(/select/i)[0];
    expect(el.value).toEqual("text");
    expect(select.value).toEqual("accessories");
  });
});
