/**
@Author Sumitesh Naithani sumitesh.naithani@publicissapient.com
*/
import data from "../constants/accessoryAttributes.json";

export const textFields = data.filter(
  (attribute) => attribute.type === "text" && attribute.name !== "pictureUrls"
);

export const pictureUrlsFields = data.filter(
  (attribute) => attribute.name === "pictureUrls"
);

export const numberFields = data.filter(
  (attribute) => attribute.type === "number"
);
