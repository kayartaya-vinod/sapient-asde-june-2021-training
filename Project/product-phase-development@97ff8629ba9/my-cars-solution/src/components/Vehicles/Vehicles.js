/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchVehicles } from '../../redux/actionCreators/vehiclesActionCreator/vehiclesActionCreator';
import VehicleGrid from '../VehicleGrid/VehicleGrid';

export default function Vehicles () {
    const [page, setPage] = useState(1);
    const { vehicles, error, totalPages } = useSelector(
        (store) => store.vehiclesReducer
    );
    const dispatch = useDispatch();

    useEffect(() => {
        (async function () {
            dispatch(await fetchVehicles(page));
        })();
    }, [dispatch, page]);

    const handleClick = () => {
        if (page < totalPages) setPage(page + 1);
    };

    return error ? (
        <h3 data-testid='error'>{error.error}</h3>
    ) : (
        <>
            <VehicleGrid vehicles={vehicles} />

            {/* <div className="row">{vehicleList}</div> */}
            {/* TODO: put this in middle */}
            <div data-testid='load-more' className="d-flex mt-3">
                {page < totalPages && (
                    <button
                        className='btn btn-primary mx-auto'
                        data-testid='load-more-btn'
                        onClick={handleClick}
                        style={{ marginTop: '5px' }}
                    >
                        Load More
                    </button>
                )}
            </div>
        </>
    );
}
