import React from "react";
import { Checkbox } from "antd";
import { useDispatch, useSelector } from "react-redux";
import { toast } from "react-toastify";
import {
  addToCompare,
  removeFromCompare,
} from "../../redux/actionCreators/vehicleCompareCreator/vehicleCompareCreator";
 import "react-toastify/dist/ReactToastify.css";
 
 toast.configure();


export default function AddToCompare({ vehicleId }) {
  const compareVehcielIdList = useSelector((store) => store.vehicleCompareListReducer);
  const dispatch = useDispatch();

  const addOrRemove = ( currState) => {
    if (currState) {
      if (compareVehcielIdList.length === 4) {
        toast.info("Max 4 vehicles are allowed in comparison!", {
          position: toast.POSITION.BOTTOM_LEFT,
          autoClose: 2000,
        });
      }
      dispatch(addToCompare(vehicleId));
    } else {
      dispatch(removeFromCompare(vehicleId));
    }
  };
  return (
    <>
      <div>
        <span>
          <label>
            <Checkbox
              onChange={(e) => addOrRemove(e.target.checked)}
              type="checkbox"
              data-testid={`addToCompare${vehicleId}`}
              checked={
                compareVehcielIdList.indexOf(vehicleId) === -1 ? false : true
              }
            />
            &nbsp; Add to Compare
          </label>
        </span>
      </div>
    </>
  );
}
