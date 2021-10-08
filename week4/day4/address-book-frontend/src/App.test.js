import { render, screen, waitFor } from '@testing-library/react';
import App from './App';
import axiosMock from 'axios';

jest.mock('axios');

describe('App component tests', () => {
    it('should render App', () => {
        render(<App />);

        const el1 = screen.getByText(/address book/i);
        const el2 = screen.getByText(/add new contact/i);

        expect(el1).toBeInTheDocument();
        expect(el2).toBeInTheDocument();
        expect(el1.tagName).toBe('H1');
        expect(el2.tagName).toBe('H3');
    });

    it('should display contacts', async () => {
        axiosMock.get.mockResolvedValueOnce({
            data: {
                content: [
                    {
                        id: 'vndkmr',
                        name: 'Vinod Kumar Kayartaya',
                        email: 'vinod@vinod.co',
                        phone: '9731424784',
                        gender: 'Male',
                    },
                ],
            },
        });
        render(<App />);
        await waitFor(() => {
            expect(screen.getByText(/mr. vinod kumar/i)).toBeInTheDocument();
            expect(screen.getByText(/vinod@vinod.co/i)).toBeInTheDocument();
            expect(screen.getByText(/9731424784/i)).toBeInTheDocument();
        });
    });
});
