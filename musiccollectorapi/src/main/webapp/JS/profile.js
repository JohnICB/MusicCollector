function openNav() {
    document.getElementById("mySidenav").style.width = "25%";

}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";

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


window.onload = getCollections;


function createColElement(lastID, nameCol, desc, isVynil) {

    let ul = document.getElementsByClassName("product-list")[0];
    let element = document.createElement("li");
    let link = document.createElement("a");
    let name = document.createElement("h3");
    let description = document.createElement("p");
    let img = document.createElement("img");


    element.classList.add("product");


    name.innerText = nameCol;
    description.innerText = desc;
    img.classList.add("vyn");

    console.log(isVynil + " la add");

    if (isVynil) {
        img.setAttribute("src", "../../Images/vinyl.png");
        link.setAttribute("href", "http://localhost:8081/collection?id=" + encodeURIComponent(lastID));
    }
    else {
        img.setAttribute("src", "../../Images/cassette.jpg");
        link.setAttribute("href", "http://localhost:8081/collection?id=" + encodeURIComponent(lastID));
    }


    console.log(desc);

    link.appendChild(name);
    link.appendChild(img);
    link.appendChild(description);


    element.appendChild(link);

    ul.appendChild(element);
}

function insertCollection(colName, isVynil, colDesc) {

    let xhr = new XMLHttpRequest();
    let lastID = -1;

    xhr.open("POST", "http://localhost:8081/collections");

    xhr.addEventListener("load", function loadCallback() {
        switch (xhr.status) {
            case 200:
                console.log("Success " + xhr.response);
                lastID = xhr.response;
                id = JSON.parse(lastID);
                createColElement(id.id, colName, colDesc, isVynil);
                break;
            case 404:
                console.log("Oups! Not found");
                break;
        }

    });

    xhr.addEventListener("error", function errorCallback() {
        console.log("Network error");
    });

    let payload = {
        title: colName,
        isVinyl: isVynil,
        description: colDesc
    };

    xhr.send(JSON.stringify(payload));

}

function clearInputs() {
    let form = document.getElementById("formObj");
    form.reset();
}

function getCollections() {

    let xhr = new XMLHttpRequest();

    xhr.open("GET", "http://localhost:8081/collections");

    xhr.addEventListener("load", function loadCallback() {
        switch (xhr.status) {
            case 200:
                console.log("Success GET" + xhr.response);
                let colStringArray = xhr.response;
                let colArray = JSON.parse(colStringArray);

                for (let i = 0; i < colArray.length; i++) {
                    createColElement(colArray[i].id, colArray[i].title, colArray[i].description, colArray[i].isVinyl);
                }
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

function addCollection() {
    modal.style.display = "none"; //hide modal

    let colName = document.getElementsByName("Collection name")[0].value;
    let colDesc = document.getElementsByName("colDescription")[0].value;
    let isVynil = document.getElementsByName("colType")[0].value;

    isVynil = isVynil === "Vinyls";

    insertCollection(colName, isVynil, colDesc);
    // createColElement(123, colName, colDesc, isVynil);
    clearInputs();
}




