/**
@Author <Hrishant> <hrishant.raj@publicissapient.com>
*/

import { render, screen } from '@testing-library/react';
import { renderWithRouter } from '../../utils/testUtils';
import CarCard from './CarCard';

describe('CarCard tests', () => {
    it('should render CarCard', () => {
        const c = {
            picturesUrl: ['abc.jpeg'],
            model: 'Huracan',
            brand: 'Lamborgini',
            vehicle_type: 'Coupe',
            price: '23000000',
        };

        renderWithRouter(<CarCard car={c} />);

        expect(screen.getByText(/Huracan/i)).toBeInTheDocument();
        expect(screen.getByText(/Lamborgini/i)).toBeInTheDocument();
        expect(screen.getByText(/Coupe/)).toBeInTheDocument();
        expect(screen.getByText(/23000000/)).toBeInTheDocument();
    });

    
});
