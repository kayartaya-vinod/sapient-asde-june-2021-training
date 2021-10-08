import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import NewContactForm from './NewContactForm';

describe('NewContactForm tests', () => {
    it('should render NewContactForm', () => {
        render(<NewContactForm />);
        const el = screen.getByText(/Add new contact/i);

        expect(el).toBeInTheDocument();
        expect(el.tagName).toBe('H3');
    });

    it('should call addContact function', async () => {
        const ac = jest.fn();
        render(<NewContactForm addContact={ac} />);

        // ask jest to type the value 'Vinod' in the textbox with data-testid='name'
        fireEvent.change(screen.getByTestId('name'), {
            target: { value: 'Vinod' },
        });
        fireEvent.change(screen.getByTestId('email'), {
            target: { value: 'vinod@vinod.co' },
        });
        fireEvent.change(screen.getByTestId('phone'), {
            target: { value: '9731424784' },
        });
        fireEvent.change(screen.getByTestId('city'), {
            target: { value: 'Bangalore' },
        });
        fireEvent.change(screen.getByTestId('state'), {
            target: { value: 'Karnataka' },
        });

        fireEvent.click(screen.getByTestId('btn-add'));

        await waitFor(() =>
            expect(ac).toHaveBeenCalledWith({
                name: 'Vinod',
                email: 'vinod@vinod.co',
                phone: '9731424784',
                city: 'Bangalore',
                state: 'Karnataka',
                gender: 'Male',
            })
        );
    });
});
