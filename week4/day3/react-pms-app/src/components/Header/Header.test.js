import { render, cleanup } from '@testing-library/react';
import Header from './Header';

describe('Testing Header component', () => {
    beforeEach(cleanup);

    it('should render Header with headerText', () => {
        const { getByText } = render(<Header headerText='Testing header' />);
        const el = getByText('Testing header');
        expect(el).toBeDefined();
        expect(el.tagName).toBe('H1');
    });
    it('should render Header without headerText', () => {
        const { getByText } = render(<Header />);
        const el = getByText('Product Management System');
        expect(el).toBeDefined();
        expect(el.tagName).toBe('H1');
    });
});

// "test": "react-script test --coverage"
// "sonar": "sonar-scanner"