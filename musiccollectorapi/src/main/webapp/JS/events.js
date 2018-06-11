let xhr = new XMLHttpRequest();
let rows = "10";
xhr.open("GET", "http://localhost:8081/eventsService?rows=" + encodeURIComponent(rows));
console.log(encodeURIComponent(rows));
xhr.send(null);