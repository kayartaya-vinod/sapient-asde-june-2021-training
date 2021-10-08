/**
@Author Manvendra Singh
*/
import React from 'react';
import { useHistory } from 'react-router';
import { addDefaultSrc, truncate } from '../../utils/utils';
import "./AccessoryCard.css";
function AccessoryCard ({ accessory = {} }) {
    //Card will recieve the accessory as the prop and it will be rendered 
    //accessory has name ,pictureURLs , description and price and other features too .
    //Showing only price description name image only in the card .
    const history = useHistory();
    return (
        <div className='card vehicle-card mx-auto'>
            <img
                onClick={() => history.push("/vehicle-accessory/" + accessory.id)}
                className='card-img-top'
                src={accessory.pictureUrls[0]}
                alt={accessory.name}
                data-testid="routing-image"
                onError={addDefaultSrc}
            />
            <div className='card-body'>
                <h5 className='card-title text-centre fw-bold' >{accessory.name} </h5>
                <p className='card-text text-centre '>{truncate(accessory.description)}</p>
                <p className='card-text text-centre'>
                    <small className='text-muted'>
                        Rs {parseFloat(accessory.price).toFixed(2)}
                    </small>
                </p>

            </div>

        </div>
    );
}

export default AccessoryCard;
