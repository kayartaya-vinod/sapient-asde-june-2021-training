import data from "../constants/attributes.json";

export const textFields = data.filter(
  (attribute) => attribute.type === "text" && attribute.name !== "pictureUrls"
);

export const pictureUrlsFields = data.filter(
  (attribute) => attribute.name === "pictureUrls"
);

export const numberFields = data.filter(
  (attribute) => attribute.type === "number"
);

export const checkBoxButtonFields = data.filter(
  (attribute) => attribute.type === "checkbox"
);

export const radioButtonFields = data.filter(
  (attribute) => attribute.type === "radio"
);
