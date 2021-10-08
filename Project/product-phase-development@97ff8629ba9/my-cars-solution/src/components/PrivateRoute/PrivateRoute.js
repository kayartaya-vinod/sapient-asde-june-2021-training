import { Redirect, Route } from "react-router";
import { ROLE_CUSTOMER } from '../../constants';
import { useSelector } from "react-redux";

export default function PrivateRoute ({ component, role = ROLE_CUSTOMER, ...rest }) {
  const { isLoggedIn, user } = useSelector((store) => store.authReducer);
  if( isLoggedIn && user.role===role) return (
    <div data-testid="private-route">
      <Route {...rest} render={component} />
    </div>
  );

  if(isLoggedIn && user.role===ROLE_CUSTOMER)
    return  (
      <div data-testid="redirect-route">
        Login as a dealer
      <Redirect to="/login" />
    </div>
  );

  return (
    <div data-testid="redirect-route">
      <Redirect to="/login" />
    </div>
  );
}
