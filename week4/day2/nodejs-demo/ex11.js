const axios = require('axios');

// use "./" prefix only for your own modules
// other modules --> core modules, part of node js binary
// --> modules installed via dependencies, found in "node_modules" folder

const searchText = 'iron';
const apikey = 'aa9e49f';
const url = `http://www.omdbapi.com/?s=${searchText}&apikey=${apikey}`;

// http://www.omdbapi.com/?s=iron&apikey=aa9e49f
console.log(`getting info about movies with ${searchText} in their titles...`);
axios
    .get(url)
    .then((resp) => resp.data)
    .then((data) => data.Search)
    .then((arr) => arr.map((movie) => movie.Title))
    .then(console.log)
    .catch((err) => console.log(err.response.data.Error));

console.log('Done.');
