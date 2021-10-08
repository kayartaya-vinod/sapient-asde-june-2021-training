const axios = require('axios');

// async functions are functions that return a Promise
// return value from async functions are nothing but promise resolution value
async function fetchMovies(searchText) {
    const apikey = 'aa9e49f';
    const url = `http://www.omdbapi.com/?s=${searchText}&apikey=${apikey}`;

    try {
        const resp = await axios.get(url);
        const data = resp.data;
        const arr = data.Search;
        return arr.map((m) => m.Title); // promise resolution
    } catch (err) {
        throw Error(err.response.data.Error);
    }
}

async function tester() {
    console.log('start of tester()');
    const movieTitles = await fetchMovies('matrix');
    console.log(movieTitles);
    console.log('end of tester()');
}

tester();
