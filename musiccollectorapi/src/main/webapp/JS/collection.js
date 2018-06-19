function getIDparameter() {
    var url_string = window.location.href;
    var url = new URL(url_string);
    return url.searchParams.get("id");
}

function createElements(jsonArray) {

    let ul = document.getElementsByClassName("product-list")[0];
    for (let i = 0; i < jsonArray.length; ++i)
    {

        let element = document.createElement("li");
        let link = document.createElement("a");
        let name = document.createElement("h3");
        let description = document.createElement("p");
        let img = document.createElement("img");

        element.classList.add("product");

        name.innerText = jsonArray[i].title;
        description.innerText = jsonArray[i].artists;
        img.classList.add("vyn");


        if (jsonArray[i].hasOwnProperty("isColored")) {
            img.setAttribute("src", "../../Images/vinyl.png");
            link.setAttribute("href", "http://localhost:8081/vinyls?id=" + encodeURIComponent(jsonArray[i].id));
        }
        else {
            img.setAttribute("src", "../../Images/cassette.jpg");
            link.setAttribute("href", "http://localhost:8081/cassettes?id=" + encodeURIComponent(jsonArray[i].id));
        }

        link.appendChild(name);
        link.appendChild(img);
        link.appendChild(description);

        element.appendChild(link);
            ul.appendChild(element);
    }
}


function getVinyls() {
    console.log("loaded");
    xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8081/collectionsService?id="
        + encodeURIComponent(getIDparameter()));

    xhr.addEventListener("load", function loadCallback() {
        switch (xhr.status) {
            case 200:
                console.log("Success " + xhr.response);
                createElements(JSON.parse(xhr.response));
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
}

window.onload = getVinyls();