import React from 'react';
import * as qs from 'query-string';

export default function Info({ location }) {
    const params = qs.parse(location.search);
    return (
        <div>
            <h3>
                Information about {params.animal} from{' '}
                {params.country || 'anywhere'}
            </h3>

            <hr />
            <pre>{sessionStorage.getItem('info')}</pre>
        </div>
    );
}
