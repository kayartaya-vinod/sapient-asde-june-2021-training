import { render, screen } from '@testing-library/react';
import ContactList from './ContactList';

describe('ContactList tests', () => {
    it('should ContactList component', () => {
        render(<ContactList contacts={[]} />);
        const el = screen.getByTestId('list');
        expect(el).toBeDefined();
        expect(el.tagName).toBe('UL');
    });

    it('should render ContactList with given contacts', () => {
        const arr = [];
        arr.push({
            id: 'vndkmr',
            name: 'Vinod Kumar',
            email: 'vinod@vinod.co',
            phone: '9731424784',
        });
        render(<ContactList contacts={arr} />);
        expect(screen.getByText(/vinod kumar/i)).toBeInTheDocument();
        expect(screen.getByText(/vinod@vinod.co/i)).toBeInTheDocument();
        expect(screen.getByText(/9731424784/i)).toBeInTheDocument();
    });
});
