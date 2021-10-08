import { cleanup, screen, fireEvent} from '@testing-library/react';

import { renderWithReduxAndRouter } from '../../utils/testUtils';
import ForgetPassword from './ForgetPassword';

jest.mock('axios');

describe('Forget Password link test', () => {
    afterEach(cleanup);   
   
    it('should render Main', () => {
    renderWithReduxAndRouter(<ForgetPassword/>);
    expect(screen.getByText(/Forgot password?/i)).toBeInTheDocument();
  });

    it("should navigate to Password reset page", () => {
    const { getByTestId } = renderWithReduxAndRouter(<ForgetPassword/>);
    fireEvent.click(getByTestId("forget-password-link"));

    expect(screen.getByText(/Forgot password?/i)).toBeInTheDocument();
    });

});