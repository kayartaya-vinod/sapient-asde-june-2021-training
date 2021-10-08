import { cleanup, screen, waitFor } from '@testing-library/react';
import { renderWithReduxAndRouter } from "../../utils/testUtils";
import Home from './Home';

describe('Home test', () => {
    afterEach(cleanup);

    xit('should render Home', async () => {
        renderWithReduxAndRouter(<Home />);
        await waitFor(() => {
            expect(screen.getByTestId(/home-heading/i)).toBeInTheDocument();
        });
    });
});
