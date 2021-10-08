import React from 'react';

export default function ProductCard({ product }) {
    return (
        <div className='card'>
            <img
                src={product.picture}
                className='card-img-top'
                alt={product.name}
            />
            <div className='card-body'>
                <h5 className='card-title'>{product.description}</h5>
                <p className='card-text'>{product.quantity_per_unit}</p>
                <p>
                    Rs.{product.unit_price}/- ({product.discount}% OFF)
                </p>
            </div>
        </div>
    );
}
// from a module, you may export 0 or more named members
// but only one default exported member
