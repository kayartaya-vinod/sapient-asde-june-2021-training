import { cleanup, screen,fireEvent} from '@testing-library/react';
import { renderWithReduxAndRouter } from '../../utils/testUtils';
import AdvanceSearchButton from "./AdvanceSearchButton";
describe('Search Bar test', () => {
  afterEach(cleanup);

  it('should render Adv-search button', () => {
    renderWithReduxAndRouter(<AdvanceSearchButton />);
    expect(screen.getByText(/advanced search/i)).toBeInTheDocument();
  });

  it("should navigate to Advanced Search page", () => {
    const { getByTestId } = renderWithReduxAndRouter(<AdvanceSearchButton />);
    fireEvent.click(getByTestId("btn-adv-srch"));

    expect(screen.getByText(/advanced search/i)).toBeInTheDocument();
    });
});

