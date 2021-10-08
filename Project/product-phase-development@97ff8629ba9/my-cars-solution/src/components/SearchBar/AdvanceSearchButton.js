import React from 'react'
import {Link} from 'react-router-dom';
export default function AdvanceSearchButton() {
    return (
        <div className="ms-auto">
                <button className="btn btn-info" data-testid='btn-adv-srch' type="submit" >
                    <Link to='/advance-search'   style={{ textDecoration: 'none',color: 'white' }}>
                    Advanced Search
                    </Link>
                </button>
            </div>
    )
}

