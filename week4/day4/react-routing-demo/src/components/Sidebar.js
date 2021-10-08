import React from 'react';
import { Link } from 'react-router-dom';

export default function Sidebar() {
    const storeInfo = (info) => {
        sessionStorage.setItem('info', JSON.stringify(info));
    };

    return (
        <div>
            <ul className='list-group'>
                <li className='list-group-item'>
                    <Link to='/'>Home</Link>
                </li>
                <li className='list-group-item'>
                    <Link to='/counter'>Counter app</Link>
                </li>
                <li className='list-group-item'>
                    <Link to='/multiplication-table/789'>Multilication table</Link>
                </li>
                <li className='list-group-item'>
                    <Link to='/about-us'>About us</Link>
                </li>
                <li className='list-group-item'>
                    <Link
                        to='/info?animal=tiger&country=India'
                        onClick={() =>
                            storeInfo({
                                name: 'tiger',
                                country: 'India',
                                state: 'West Bengal',
                            })
                        }
                    >
                        Information about tiger
                    </Link>
                </li>
                <li className='list-group-item'>
                    <Link
                        to='/info?animal=lion'
                        onClick={() =>
                            storeInfo({
                                name: 'lion',
                                country: 'Africa',
                                state: 'N/a',
                            })
                        }
                    >
                        Information about lion
                    </Link>
                </li>
            </ul>
        </div>
    );
}
