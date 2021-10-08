import { useSelector } from "react-redux";
import {ROLE_CUSTOMER} from '../../constants';

export default function IfLoggedIn ({ children, role = {ROLE_CUSTOMER} }) {
  const { isLoggedIn, user } = useSelector((store) => store.authReducer);

  if(isLoggedIn && user.role===role)
  {
    return children;
  }
  
  return <></>;
}
