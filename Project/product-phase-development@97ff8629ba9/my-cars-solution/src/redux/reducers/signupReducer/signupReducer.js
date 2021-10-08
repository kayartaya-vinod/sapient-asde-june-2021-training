import { SIGNUP, SIGNUP_FAILED} from "../../actionTypes";

const signupReducer=(state={},action={})=>{
    switch(action.type){

        case SIGNUP:{
            return {...action.payload};
        }
        case SIGNUP_FAILED:{
            return {...action.payload};
        }

        default:
            return state;

    }

}

export default signupReducer;