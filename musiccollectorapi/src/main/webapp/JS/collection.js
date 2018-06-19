window.onload = getVinyls();

function getIDparameter() {
    let url_string = window.location.href;
    let url = new URL(url_string);
    return url.searchParams.get("id");
}
function getIDfromLI(liElement) {
    let a = liElement.childNodes[0];
    let url = new URL(a.getAttribute("href"));
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
function deleteElementFromCollection(liElement) {

    let colID = getIDparameter();
    let elemtID = getIDfromLI(liElement);

    let xhr = new XMLHttpRequest();
    xhr.open("DELETE", "http://localhost:8081/collectionsService");

    xhr.addEventListener("load", function loadCallback() {
        switch (xhr.status) {
            case 200:
                console.log("Success " + xhr.response);
                //TODO: Sterge elementul din pagina
                break;
            case 404:
                console.log("Oups! Not found");
                break;
        }

    });
    liElement.innerHTML = "";
    xhr.addEventListener("error", function errorCallback() {
        console.log("Network error");
    });

    let payload = {
        colID: colID,
        musicID: elemtID
    };
    xhr.send(JSON.stringify(payload));
    
}
function openCity(evt, button) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(button).style.display = "block";
    evt.currentTarget.className += " active";
}

// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();
// Get the modal
var modal = document.getElementById('myModal');
// Get the button that opens the modal
var btn = document.getElementById("myBtn");
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
btn.onclick = function () {
    modal.style.display = "block";
};

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
    modal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target === modal) {
        modal.style.display = "none";

        let colName = document.getElementsByName("Collection name")[0].value;
        let colDesc = document.getElementsByName("colDescription")[0].value;

        colName.innerText = "";
        colDesc.innerHTML = "";
    }
};