import React from 'react';
import FilteredList from './FilteredList';
import AdvanceSideFilter from './AdvanceSideFilter';
import CompareButton from "../AddToCompare/CompareButton";

/**
 * @author Suraj kumar suraj.kumar3@publicissapient.com
 */
function AdvanceSearch () {

    return (
        <div className='container'>
            <CompareButton/>
           
            <div className='text-left'>
                <h2> Advanced Filter Search </h2>
            </div>
            <hr />
            <div className='row'>
                <div className='col-md-3 col-xs-12'>
                    <div className="border border-secondary">
                        <AdvanceSideFilter />
                    </div>
                </div>
                <div className='col-md-9 col-xs-12'>
                    <FilteredList />
                </div>
            </div>
        </div>
    );
}

export default AdvanceSearch;
