import React, { useState, useEffect } from "react";
import Select from "react-select";
import { useDispatch } from "react-redux";
import { BsFillPlusCircleFill } from "react-icons/bs";

import { getAllBrands } from "../../redux/actionCreators/addVehicleToCompareActionCreator/addVehicleToCompareActionCreator";
import { addToCompare } from "../../redux/actionCreators/vehicleCompareCreator/vehicleCompareCreator";
export default function AddVehicleToComparisonMatrix() {
  const dispatch = useDispatch();
  const [selectBrand, setSelectBrand] = useState("");
  const [forBrand, setForBrand] = useState([])
  useEffect(() => {
    async function fetchData() {
      const { payload } = dispatch(await getAllBrands());
      setForBrand(payload.data)
    }
    fetchData();
  }, [dispatch])
  const [models, setModels] = useState([])

  useEffect(() => {
    async function getData() {
      if (selectBrand !== "") {
        setModels([])
        const { payload } = dispatch(await getAllBrands(selectBrand));
        setModels(payload.data)
      }
    }
    getData();
  }, [selectBrand, dispatch])
  const handleChange = brand => {
    setSelectBrand(brand.label)

  }

  const handleModelChange = model => {
    dispatch(addToCompare(model.value)); setSelectBrand(""); setToggle(true);

  }
  const [toggle, setToggle] = useState(true);
  const toRender = toggle ? (
    <div className="text-center" onClick={() => setToggle(false)} data-testid="addbutton" >
      <BsFillPlusCircleFill color="lightblue" size="5em" />
    </div>
  ) : (
    <div className="container text-center">
      <Select
        isLoading={forBrand.length === 0 ? true : false}
        options={forBrand}
        onChange={handleChange}
        placeholder="Select Brand"
      >
      </Select>
      <br />
      <Select
        isLoading={models.length === 0 ? true : false}
        options={models}
        placeholder="Select Model"
        onChange={handleModelChange}
        isDisabled={selectBrand === "" ? true : false}
      >
      </Select>
    </div>
  );
  return { ...toRender }

}
