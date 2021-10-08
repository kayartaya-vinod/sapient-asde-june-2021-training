const axios = require('axios');

console.log('start of script');
axios
    .get('http://localhost:8080/api/employees')
    .then((resp) => resp.data)
    .then((data) => data[2])
    .then((emp1) => `${emp1.name} earns ${emp1.salary}`)
    .then((str) => console.log(str))
    .catch(function () {
        console.log(arguments);
    });
console.log('end of script');
