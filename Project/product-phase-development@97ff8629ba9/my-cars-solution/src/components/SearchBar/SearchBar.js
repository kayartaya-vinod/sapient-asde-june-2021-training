/**
@Author Yogamber Singh Negi yogamber.negi@publicissapient.com

@Author Hrishant Raj hrishant.raj@publicissapient.com  //latest build

 @Author Aditya Gheewala aditya.gheewala@publicissapient.com [select option, focus on change]
# */
import React, { useState, useRef } from "react";
import { useHistory } from "react-router-dom";
import { FaSearchPlus } from "react-icons/fa";
import sanitize from "mongo-sanitize";

export default function SearchBar ({ searchText, type }) {
  const history = useHistory();
  const [search, setSearch] = useState(searchText ? searchText : "");
  const [searchType, setSearchType] = useState(type ? type : "vehicles");

  const searchRef = useRef();

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (search) {
      searchType === "vehicles" ? history.push("/search?q=" + search) : history.push("/accessories/search?q=" + search);
    }
  };

  const handleChange = ({ target }) => {
    setSearch(sanitize(target.value));
  };

  const handleSearchTypeChange = (event) => {
    setSearchType(event.target.value);
    searchRef.current.focus();
  };


  return (
    <>
      <form className="d-flex ms-auto border-bottom border-primary border-2 p-1 r-1 rounded-bottom" onSubmit={handleSubmit}>
        <select className="btn custom-select btn-primary me-1" value={searchType} onChange={handleSearchTypeChange} data-testid="select">
          <option value="vehicles" data-testid="selectOptionV">Vehicles</option>
          <option value="accessories" data-testid="selectOptionA">Accessories</option>
        </select>
        <input
          ref={searchRef}
          className="form-control border-0 shadow-none"
          type="search"
          placeholder={"Search for " + searchType}
          value={search}
          onChange={handleChange}
          aria-label="Search"
          data-testid="search"
        />

        <button
          className="btn btn-primary"
          type="button"
          data-testid="btn"
          title="Advance Search"
          onClick={() => history.push("/advanced-search")}
        >
          <FaSearchPlus />
        </button>
      </form>
    </>
  );
}
