/**
# @Author Yogamber Singh Negi yogamber.negi@publicissapient.com

@Author Hrishant Raj hrishant.raj@publicissapient.com  //latest build

@Author Aditya Gheewala aditya.gheewala@publicissapient.com [fetchSearchAccessories]
# */
import axios from "axios";
import { SEARCH, SEARCH_ERROR, SEARCH_ACCESSORIES, SEARCH_ACCESSORIES_1, SEARCH_1 } from "../../actionTypes";
import { vehiclesUrl } from "../../../constants";

export const fetchSearch = async (name, page) => {
    try {
        const { data } = await axios.get(
            `${vehiclesUrl}search?q=${name}&page=${page}`
        );
        if (page === 1) return { type: SEARCH_1, payload: data };
        return { type: SEARCH, payload: data };
    } catch (error) {
        return { type: SEARCH_ERROR, payload: [] };
    }
};

export const fetchSearchAccessories = async (name, page) => {
    try {
        const { data } = await axios.get(
            `${vehiclesUrl}accessories/search?q=${name}&page=${page}`
        );
        if (page === 1) return { type: SEARCH_ACCESSORIES_1, payload: data.data };
        return { type: SEARCH_ACCESSORIES, payload: data.data };
    } catch (error) {
        return { type: SEARCH_ERROR, payload: [] };
    }
};
