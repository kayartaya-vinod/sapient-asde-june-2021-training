/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { useLocation } from "react-router-dom";
import { deleteAccessory, getDealerAccessories, resetDealerAccessories } from "../../redux/actionCreators/dealerAccessoriesActionCreator/dealerAccessoriesActionCreator";
import PleaseWait from "../PleaseWait/PleaseWait";
import PreviousNext from "../PreviousNext/PreviousNext";
import DealerAccessoriesListItem from "./DealerAccessoriesListItem";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


function useQuery () {
  return new URLSearchParams(useLocation().search);
}

export default function DealerAccessories () {
  const { accessories, isFirst, isLast, idToDelete } = useSelector((store) => store.dealerAccessoriesReducer);
  const [pleaseWait, setPleaseWait] = useState(false);
  const dispatch = useDispatch();
  const page = useQuery().get("page") || 1;
  const pathname = "/dealer/dashboard/dealer-accessories/";

  useEffect(() => {
    (async function () {
      setPleaseWait(true);

      dispatch(await getDealerAccessories(page));
      setPleaseWait(false);
    })();

    return (async function () {
      dispatch(resetDealerAccessories());
    });
  }, [dispatch, page]);

  const handleDelete = async () => {
    const resp = dispatch(await deleteAccessory(idToDelete));
    if (resp.type === 'NO_DISPATCH_ACTION') {
      toast.error(resp.payload, {
        position: "bottom-right",
        autoClose: 3500,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });
    }
  };


  const accessoriesList = accessories.map(a => (
    <DealerAccessoriesListItem key={a.id} accessory={a} />
  ));

  const displayMessage = pleaseWait ? <PleaseWait /> : <div className="container"><h2>No Accessories Found</h2></div>;

  return (
    <>
      <h2>My Accessories</h2>
      <hr />


      {accessoriesList.length === 0 && displayMessage}

      <div className="list-group">
        {accessoriesList.length > 0 && accessoriesList}
      </div>

      <div className="modal fade" id="alertModal" tabIndex="-1" aria-labelledby="alert" aria-hidden="true">
        <div className="modal-dialog modal-dialog-centered" data-testid="alert-modal">
          <div className="modal-content">
            <div className="modal-body">
              Are you sure you want to delete this accessory?
            </div>
            <div className="modal-footer">
              <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button type="button" className="btn btn-danger" onClick={() => handleDelete()} data-bs-dismiss="modal">Delete</button>
            </div>
          </div>
        </div>
      </div>

      {accessories.length > 0 && <PreviousNext isFirst={isFirst} isLast={isLast} page={page} pathname={pathname} />}
    </>
  );
}
