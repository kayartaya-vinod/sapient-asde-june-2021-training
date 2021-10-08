import {
    ADD_REMOVE_BRAND,
    ADD_REMOVE_FUEL_TYPE,
    ADD_REMOVE_VEHICLE_TYPE,
    ADD_REMOVE_VEHICLE_COLOR,
    ADD_REMOVE_AIRBAG_COUNT,
    UPDATE_PRICE,
    UPDATE_YEAR
} from '../../actionTypes';

const initialState = {
    brand: [],
    price: '',
    vehicleType: [],
    fuelType: [],
    color: [],
    airBagCount: [],
    year: '',
};

function advancedSearchReducer(state = initialState, action = {}) {
    switch (action.type) {
        case ADD_REMOVE_BRAND: {
            const { brand } = { ...state };
            const index = brand.indexOf(action.payload);
            if (index === -1) {
                return { ...state, brand: [...state.brand, action.payload] };
            } else {
                brand.splice(index, 1);
                return { ...state, brand };
            }
        }
        case ADD_REMOVE_VEHICLE_TYPE: {
            const { vehicleType } = { ...state };
            const index = vehicleType.indexOf(action.payload);
            if (index === -1) {
                return {
                    ...state,
                    vehicleType: [...state.vehicleType, action.payload],
                };
            } else {
                vehicleType.splice(index, 1);
                return { ...state, vehicleType };
            }
        }
        case ADD_REMOVE_FUEL_TYPE: {
            const { fuelType } = { ...state };
            const index = fuelType.indexOf(action.payload);
            if (index === -1) {
                return {
                    ...state,
                    fuelType: [...state.fuelType, action.payload],
                };
            } else {
                fuelType.splice(index, 1);
                return { ...state, fuelType };
            }
        }
        case ADD_REMOVE_VEHICLE_COLOR: {
            const { color } = { ...state };
            const index = color.indexOf(action.payload);
            if (index === -1) {
                return { ...state, color: [...state.color, action.payload] };
            } else {
                color.splice(index, 1);
                return { ...state, color };
            }
        }
        case ADD_REMOVE_AIRBAG_COUNT: {
            const { airBagCount } = { ...state };
            const index = airBagCount.indexOf(action.payload);
            if (index === -1) {
                return {
                    ...state,
                    airBagCount: [...state.airBagCount, action.payload],
                };
            } else {
                airBagCount.splice(index, 1);
                return { ...state, airBagCount };
            }
        }
        case UPDATE_PRICE: {
            return { ...state, price: action.payload };
        }
        case UPDATE_YEAR:{
          return {...state, year: action.payload };
        }
        default:
            return state;
    }
}

export default advancedSearchReducer;
