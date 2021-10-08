/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
import { waitFor, fireEvent,screen,cleanup } from '@testing-library/react';
import { renderWithRouter } from '../../utils/testUtils';
import LoginPage from './LoginPage';
import { LOGIN_SUCCESS } from '../../redux/actionTypes';

const mockDispatch = jest.fn();
const mockSelector = jest.fn();
const mockLogin = jest.fn().mockReturnValue({
    type: LOGIN_SUCCESS,
    payload: {
        isLoggedIn: true,
        token:null,
        user: null,
    },
});
const mockHistoryPush = jest.fn();
const mockStore = jest.fn();
jest.mock('react-redux', () => ({
    useDispatch: () => mockDispatch,
    useSelector: () => mockSelector,
}));
jest.mock('react-router-dom', () => ({
    ...jest.requireActual('react-router-dom'),
    useHistory: () => ({
        push: mockHistoryPush,
    }),
}));
jest.mock('../../redux/actionCreators/authActionCreator/authActionCreator',()=>({
    login: ()=>mockLogin,
}))
jest.mock("axios");
describe('LoginPage tests', () => {
    const originalConsole = console.error;
    beforeEach(()=>{
        console.error = jest.fn();
    })
    afterEach(()=>{
        console.error = originalConsole;
    })
    afterEach(cleanup);
    it('should dispatch login success and should redirect to dashboard', async () => {
        renderWithRouter(<LoginPage />);
        fireEvent.change(screen.getByTestId('email'), {
            target: { value: 'vinod@vinod.co' },
        });
        fireEvent.change(screen.getByTestId('password'), {
            target: { value: 'topsecret' },
        });
        fireEvent.submit(screen.getByTestId('formSubmit'));

    });
    it('should render Login Page', () => {
        renderWithRouter(<LoginPage />);
        const el = screen.getByText(/Existing users, login here !/i);

        expect(el).toBeInTheDocument();
    });
    it('should render Login button', () => {
        renderWithRouter(<LoginPage />);
        fireEvent.click(screen.getByTestId('btn-add'));
    });
    it('should render email', () => {
        renderWithRouter(<LoginPage />);
        const el = screen.getByText(/email/i);
        expect(el).toBeInTheDocument();
    });
    it('should render password', () => {
        renderWithRouter(<LoginPage />);
        expect(screen.getAllByText(/password/i)).toHaveLength(2);
    });
    it('should render Forgotpassword', () => {
        renderWithRouter(<LoginPage />);
        const el = screen.getByText(/Forgot password?/i);
        expect(el).toBeInTheDocument();
    });
    it('should give password cannot be empty error', () => {
        renderWithRouter(<LoginPage />);
        fireEvent.change(screen.getByTestId('email'), {
            target: { value: 'naruto@gmail.com' },
        });
        fireEvent.change(screen.getByTestId('email'), {
            target: { value: '' },
        });
        fireEvent.change(screen.getByTestId('password'), {
            target: { value: null },
        });
        fireEvent.click(screen.getByTestId('btn-add'));
        waitFor(() => {
            const el = screen.getByText(/password cannot be empty/i);
            expect(el).toBeInTheDocument();
        })

    });
    it('should give email cannot be empty error', () => {
        renderWithRouter(<LoginPage />);
        fireEvent.change(screen.getByTestId('password'), {
            target: { value: 'naruto' },
        });
        fireEvent.change(screen.getByTestId('password'), {
            target: { value: '' },
        });
        fireEvent.change(screen.getByTestId('email'), {
            target: { value: null },
        });
        fireEvent.click(screen.getByTestId('btn-add'));
        waitFor(() => {
            const el = screen.getByText(/email cannot be empty/i);
            expect(el).toBeInTheDocument();
        })

    });
    it('should give "either email or password is incorrect"', () => {
        renderWithRouter(<LoginPage />);
        fireEvent.change(screen.getByTestId('email'), {
            target: { value: 'sasuke@gmail.com' },
        });
        fireEvent.change(screen.getByTestId('password'), {
            target: { value: 'uchiha' },
        });
        fireEvent.click(screen.getByTestId('btn-add'));
        waitFor(() => {
            const el = screen.getByText(/either email or password is incorrect/i);
            expect(el).toBeInTheDocument();
        })

    });
    it('should not dispatch for wrong email and password ', async () => {
        renderWithRouter(<LoginPage />);
        fireEvent.change(screen.getByTestId('email'), {
            target: { value: 'sasuke@gmail.com' },
        });
        fireEvent.change(screen.getByTestId('password'), {
            target: { value: 'uchiha' },
        });
        fireEvent.submit(screen.getByTestId('formSubmit'));
        await waitFor(() => {
            expect(mockDispatch).toHaveBeenCalledTimes(0);
        })
    });
    
});