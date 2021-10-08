import React, { useState, useEffect } from "react";
import { FaHeart } from "react-icons/fa";
import { useDispatch, useSelector } from "react-redux";
import { toast } from "react-toastify";
import {
  getFavoriteVehicles,
  updateFavorites,
} from "../../redux/actionCreators/favoriteVehicleActionCreator/favoriteVehicleActionCreator";
import "react-toastify/dist/ReactToastify.css";


toast.configure();

const colors = {
  red: "#ff0000",
  grey: "#d3d3d3",
};

const AddRemoveFavorite = ({ vehicle }) => {
  const { vehicles } = useSelector((store) => store.favoriteVehicleReducer);
  const [state, setState] = useState(0);

  const dispatch = useDispatch();

  useEffect(() => {
    if (vehicles.findIndex((v) => v.id === vehicle.id) !== -1) {
      setState(1);
    } else {
      setState(0);
    }
  }, [vehicle.id, vehicles]);

  const handleAddOrRemove = async () => {
    const action = dispatch(await updateFavorites(vehicle)).type;
    dispatch(await getFavoriteVehicles());
    if (action === "UPDATE_FAVORITES_ERROR") {
      toast.error("Something went wrong! Try later", {
        position: toast.POSITION.BOTTOM_LEFT,
        autoClose: 2000,
      });
    }
  };

  return (
    <>
      <FaHeart
        data-testid="heartIcon"
        size={20}
        color={state ? colors.red : colors.grey}
        onClick={handleAddOrRemove}
        style={{
          marginRight: 10,
          marginLeft: 30,
          marginTop: 2,
          cursor: "pointer",
        }}
      />
    </>
  );
};

export default AddRemoveFavorite;
