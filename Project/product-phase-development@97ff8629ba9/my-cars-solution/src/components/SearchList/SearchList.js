/**
@Author <Hrishant> <hrishant.raj@publicissapient.com>
*/

import React from 'react';
import CarCard from '../CarCard/CarCard'; 
import {useSelector} from 'react-redux';

function SearchList() {

    const data =useSelector((store)=>store.searchReducer);

    if(data){
        const list = data.map((c) => (
        <li className='list-group-item' key={c.model}>
            <CarCard car={c} />
        </li>
        
    ));
    return (
  
        <div style={{ maxHeight: '90vh', overflow: 'auto' }}>
            <ul data-testid='list' className='list-group'>
                {list}
            </ul>
        </div>
    )
    }
    return (
  
        <div style={{ maxHeight: '90vh', overflow: 'auto' }}>
            <ul data-testid='list' className='list-group'>
                {[]}
            </ul>
        </div>
    )
}

export default SearchList;
