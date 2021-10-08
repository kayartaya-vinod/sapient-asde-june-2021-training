import { fireEvent, render, screen } from '@testing-library/react';
import ContactCard from './ContactCard';

describe('ContactCard tests', () => {
    it('should render ContactCard', () => {
        const c = {
            name: 'Vinod Kumar',
            email: 'vinod@vinod.co',
            phone: '9731424784',
            gender: 'Male',
        };

        render(<ContactCard contact={c} />);

        expect(screen.getByText(/mr. vinod kumar/i)).toBeInTheDocument();
        expect(screen.getByText(/vinod@vinod.co/i)).toBeInTheDocument();
        expect(screen.getByText(/9731424784/)).toBeInTheDocument();
    });

    it('should call deleteContact when x is clicked', () => {
        const dc = jest.fn(); // mocking a function
        const c = { id: 'asdf' };

        render(<ContactCard contact={c} deleteContact={dc} />);

        const btn = screen.getByTestId('btn-delete');
        fireEvent.click(btn);

        expect(dc).toHaveBeenCalledTimes(1);
        expect(dc).toHaveBeenCalledWith(c.id);
    });
});
