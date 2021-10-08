import { useSelector } from "react-redux";

export default function IfNotLoggedIn({ children }) {
  const { isLoggedIn } = useSelector((store) => store.authReducer);
  return isLoggedIn ? <></> : children;
}
