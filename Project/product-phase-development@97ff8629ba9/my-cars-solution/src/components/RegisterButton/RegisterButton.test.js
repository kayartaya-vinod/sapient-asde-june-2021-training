import { cleanup, screen, fireEvent} from '@testing-library/react';

import { renderWithReduxAndRouter } from '../../utils/testUtils';
import RegisterButton from './RegisterButton';

jest.mock('axios');

describe('Register Button test', () => {
    afterEach(cleanup);   
   
    it('should render Main', () => {
    renderWithReduxAndRouter(<RegisterButton/>);
    expect(screen.getByText(/Register/i)).toBeInTheDocument();
  });

    it("should navigate to Registration page", () => {
    const { getByTestId } = renderWithReduxAndRouter(<RegisterButton />);
    fireEvent.click(getByTestId("register-button-link"));

    expect(screen.getByText(/register/i)).toBeInTheDocument();
    });

});
