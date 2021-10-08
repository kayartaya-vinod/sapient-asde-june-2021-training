/**
@Author Sumitesh Naithani sumitesh.naithani@publicissapient.com
@Author Shubham Chaudhary shubham.chaudhary@publicissapient.com
*/
import { useEffect } from "react";
import { useLocation } from "react-router";

const ScrollToTop = (props) => {
  const location = useLocation();
  useEffect(() => {
    window.scrollTo(0, 0);
  }, [location]);

  return <>{props.children}</>;
};

export default ScrollToTop;
