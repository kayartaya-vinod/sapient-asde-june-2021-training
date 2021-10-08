/**
@Author <Hrishant> <hrishant.raj@publicissapient.com>
*/

import React from 'react';
import { Link } from 'react-router-dom';
import { addDefaultSrc } from '../../utils/utils';

function CarCard({car}) {
    return (
        <div>
            <div className='row'>
                <div className='col-md-4'>
                    <Link
                        to={'/cars/' + car.id}
                    >
                        <img src={car.picturesUrl[0]} alt= {car.model}
                            className = 'card-img'
                            style= {{width: '150px', height: '150px'}}
                            onError={addDefaultSrc}
                        />
                    </Link>
                </div>
                <div className='col-md-7'>
                    <Link
                        to={'/cars/' + car.id}   
                        style={{ textDecoration: 'none' }}
                    >
                        <h4>
                            {car.brand} &middot; {car.model}
                        </h4>
                    </Link>
                    <h5>
                        Price: {car.price} <br/>
                        Vehicle-Type: {car.vehicle_type}
                    </h5>
                </div>
                
            </div>
        </div>
    );
}

export default CarCard;

