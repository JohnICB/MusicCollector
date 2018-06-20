let xhr = new XMLHttpRequest();
let rows = "10";
xhr.open("GET", "http://localhost:8081/eventsService?rows=" + encodeURIComponent(rows));
console.log(encodeURIComponent(rows));

function createEvents(json) {
    // <div class="wrapper">
    //     <h2 class="date"> MAR 21 </h2>
    //     <h2 class="location">ST JORDI - CLUB BARCELONA, SPAIN</h2>
    //     <a href="#" class="ticket">TICKETS</a>
    // </div>

    console.log(json);

    let events = json.events;
    console.log(events);

    let section = document.getElementsByClassName("middle-section")[0];

for (let i = 0; i < events.length; ++i)
{

    let div = document.createElement("div");
    div.classList.add("wrapper");

    let date = document.createElement("h2");
    let location = document.createElement("h2");

    date.classList.add("date");
    location.classList.add("location");

    let a = document.createElement("a");
    a.setAttribute("href", events[i].URL);
    a.classList.add("ticket");
    a.innerText = "TICKETS";

    date.innerText = events[i].start_date;
    location.innerText = events[i].title;

    div.appendChild(date);
    div.appendChild(location);
    div.appendChild(a);

    section.appendChild(div);

}


}

xhr.addEventListener("load", function loadCallback() {
    switch (xhr.status) {
        case 200:
            console.log("Success " + xhr.response);
            createEvents(JSON.parse(xhr.response));
            break;
        case 404:
            console.log("Oups! Not found");
            break;
    }

});

xhr.addEventListener("error", function errorCallback() {
    console.log("Network error");
});


xhr.send(null);

