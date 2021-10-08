// asynchronous method execution

function sleep(duration) {
    let start = Date.now();
    while (Date.now() - start <= duration);
}

function hello(name) {
    // sleep(10000)
    console.log('Hello, %s!', name || 'friend');
}

console.log('start of script');
// hello()
setTimeout(() => hello('Vinod'), 0);
console.log('end of script');
sleep(2000);
