import { cleanup, screen, fireEvent, render } from '@testing-library/react';
import Main from './Main';


describe('Main test', () => {
  afterEach(cleanup);

  xit('should render Main', () => {
    render(<Main />);
    expect(screen.getByText(/welcome/i)).toBeInTheDocument();
  });

  // it('should navigate to About us page', () => {

  //   const { getByTestId } = render(<Main />);
  //   fireEvent.click(getByTestId('about-us-link'));

  //   expect(screen.getByText(/about us page/i)).toBeInTheDocument();
  // });

  xit('should navigate to Homepage page', async () => {
    const { getByTestId } = render(<Main />);
    fireEvent.click(getByTestId('home-link'));
    expect(screen.getByText(/welcome/i)).toBeInTheDocument();
  });
});
