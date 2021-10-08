import { GET_SPONSORED_VEHICLE } from "../../actionTypes";

/**
 * 
 * author: vikash
 */

const initialState = [
  {
    id: "aksdsdad45da5a1sd2",
    brand: "Ferrari",
    vehicleType: "SUV",
    year: 2001,
    model: "F8",
    pictureUrls: [
      "https://mycarsolutions.net/public/assets/images/sponsoredVehicleImage/sp_6114d0333bf88f5822eeca6e.jpg",
      "https://dummyimage.com/114x100.png/dddddd/000000",
      "https://dummyimage.com/241x100.png/dddddd/000000",
    ],
    description: "This is a car.",
    color: "Purple",
    price: "$6565.01",
  },
];
const sponsoredVehicleReducer = (state = initialState, action = {}) => {
  if (action.type === GET_SPONSORED_VEHICLE) {

    return [...action.payload];

  }
  return state;
};
export default sponsoredVehicleReducer;
