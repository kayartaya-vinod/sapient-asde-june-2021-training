import { cleanup, screen} from "@testing-library/react";
import AdvanceSearch from "./AdvanceSearch";
import { renderWithReduxAndRouter } from "../../utils/testUtils";

describe('AdvanceSearch test', ()=>{
    afterEach(cleanup);
it('should render AdvanceSearch', () => {
   renderWithReduxAndRouter(<AdvanceSearch />);
    expect(screen.getByText(/advanced filter search/i)).toBeInTheDocument();
    expect(screen.getByText(/select filters/i)).toBeInTheDocument();
  });
})