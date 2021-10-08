/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import React from "react";
import { useHistory } from "react-router";
import { useDispatch } from 'react-redux';
import { addDefaultSrc, truncate } from "../../utils/utils";
import { BsTrash } from "react-icons/bs";
import { selectDealerAccessory } from "../../redux/actionCreators/dealerAccessoriesActionCreator/dealerAccessoriesActionCreator";

const DealerAccessoriesListItem = ({ accessory }) => {

  const history = useHistory();

  const dispatch = useDispatch();

  const handleClick = (id) => {
    dispatch(selectDealerAccessory(id));
  };

  return (
    <div className="list-group-item">
      <div className="row g-0">
        <div className="col-11">
          <div className="d-flex w-100 justify-content-between">
            <h5
              className="mb-1 me-auto"
              style={{ cursor: "pointer" }}
              data-testid="accessory-link"
              onClick={() => {
                history.push(`/vehicle-accessory/${accessory.id}`);
              }}
            >{`${accessory.name}`}</h5>
          </div>
          <div className="d-flex flex-column" data-testid="accessory-details">
            <div
              style={{ cursor: "pointer" }}
              onClick={() => {
                history.push(`/vehicle-accessory/${accessory.id}`);
              }}
              data-testid="accessory-link"
            >
              <p className="mb-1">{truncate(accessory.description, 90)}</p>
              <small>{`Price: ${accessory.price} INR`}</small>
            </div>
            <div>
              <button
                type="button"
                className="btn btn-link btn-sm text-start p-0 mx-0 mt-2 mb-0 pe-4"
                data-testid="accessory-edit-link"
                onClick={() => {
                  history.push(`/vehicle-accessory/edit/${accessory.id}`);
                }}
              >
                Edit Details
              </button>
              <button className="btn text-danger pb-0" data-bs-toggle="modal" data-bs-target="#alertModal" data-testid="delete-btn"
                onClick={() => handleClick(accessory.id)}>
                <BsTrash />
              </button>
            </div>
          </div>
        </div>
        <div
          className="col-1 my-auto"
          style={{ cursor: "pointer" }}
          onClick={() => {
            history.push(`/vehicle-accessory/${accessory.id}`);
          }}
          data-testid="accessory-link"
        >
          {accessory.pictureUrls !== null && (
            <img
              src={accessory.pictureUrls[0]}
              className="img-thumbnail p-0"
              alt={accessory.name}
              style={{ width: "100px" }}
              data-testid="accessory-img"
              onError={addDefaultSrc}
            />
          )}
        </div>
      </div>
    </div>
  );
};

export default DealerAccessoriesListItem;
