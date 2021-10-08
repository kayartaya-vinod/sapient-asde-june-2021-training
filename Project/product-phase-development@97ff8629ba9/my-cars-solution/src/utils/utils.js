import fallbackImg from "./fallback-image.png";

export const passwordStrengthCheck = (password) => {
  // Minimum length should be 8
  // Atleast 1 lower case character
  // Atleast 1 upper case character
  // Atleast 1 special character
  // Atleast 1 digit

  const regEx = /^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
  if (regEx.test(password)) {
    return true;
  }
  return false;
};

//  capitalize first letter of any string
export const capitalizeFirstLetter = (string) => {
  if (string) {
    return string[0].toUpperCase() + string.slice(1);
  }
};

export const splitCamelCase = (string) => {
  return string.replace(/([a-z])([A-Z])/g, "$1 $2");
};

export const truncate = (text = "", newLength = 20) => {
  if (text.length <= newLength) {
    return text;
  }
  return text.substring(0, newLength) + "...";
};

export const addDefaultSrc = (ev) => {

  ev.target.src = fallbackImg;
};