import React from 'react';

import ProductCard from '../ProductCard/ProductCard';
import products from '../../data';

export default function ProductList() {
    // convert an array of simple javascript objects into
    // an array of jsx elements
    /*
    [
        <div><ProductCard product={p1} /></div>,
        <div><ProductCard product={p2} /></div>,
        <div><ProductCard product={p3} /></div>,
        <div><ProductCard product={p4} /></div>,
        <div><ProductCard product={p5} /></div>,
    ]
    */
    const productCardArray = products.map((p) => (
        <div className='col-lg-3 col-md-4 col-sm-6 col-xs-12'>
            <ProductCard product={p} />
        </div>
    ));

    return <div className='row'>{productCardArray}</div>;
}
