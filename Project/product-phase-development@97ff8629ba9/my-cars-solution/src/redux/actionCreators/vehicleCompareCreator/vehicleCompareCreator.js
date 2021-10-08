
/**
 * @author Abhishek Rajgaria, abhishek.rajgaria@publicissapient.com 
 */

/**
 * @author Pritam Patel, pritam.patel@publicisspaient.com
 */


import { ADD_TO_COMPARE,  REMOVE_FROM_COMPARE,  } from '../../actionTypes';


export const addToCompare = (vehicleId)=>{
    return {type: ADD_TO_COMPARE, payload: vehicleId};
};

export const removeFromCompare = (vehicleId) => {
  return { type: REMOVE_FROM_COMPARE, payload: vehicleId };
};

