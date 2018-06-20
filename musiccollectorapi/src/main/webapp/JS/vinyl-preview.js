window.onload = getInfo();

function getIDparameter() {
    let url_string = window.location.href;
    let url = new URL(url_string);
    return url.searchParams.get("id");
}

function updateInformation(data) {

let dsadasdasfasfx = {
        "id":1,
            "title":"titletest",
            "artists":"jhon si petrica",
            "region":"",
            "age":"new",
            "album":"",
            "size":"29",
            "isColored":false,
            "isStereo":false,
            "isSpecialEdition":false,
            "duration":"",
            "genre":"",
            "rarity":"",
            "releaseDate":""
    };

    let titlu = document.getElementById("titlu");
    titlu.innerText = data.title;
    let album = document.getElementById("album");
    album.innerText = data.album;
    let artists = document.getElementById("artists");
    artists.innerText = data.artists;
    let age = document.getElementById("age");
    age.innerText = data.age;
    let size = document.getElementById("size");
    size.innerText = data.size;
    let isCol = document.getElementById("isCol");
    isCol.innerText = data.isColored;
    let isSter = document.getElementById("isSter");
    isSter.innerText = data.isStereo;
    let isSpEd = document.getElementById("isSpEd");
    isSpEd.innerText = data.isSpecialEdition;
    let duration = document.getElementById("duration");
    duration.innerText = data.duration;
    let genre = document.getElementById("genre");
    genre.innerText = data.genre;
    let release = document.getElementById("release");
    release.innerText = data.releaseDate;
    let rarity = document.getElementById("rarity");
    rarity.innerText = data.rarity;
    let region = document.getElementById("region");
    region.innerText = data.region;
    
}

function getInfo() {

    xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8081/previewElement?id=" +
    encodeURIComponent(getIDparameter()) +"&isVinyl=" +
    encodeURIComponent("true"));

    xhr.addEventListener("load", function loadCallback() {
        switch (xhr.status) {
            case 200:
                updateInformation(JSON.parse(xhr.response));
                console.log(xhr.response);
                break;
            case 404:
                console.log("Oups! Not found");
                break;
            default:
                console.log("alta eroareas");
                break;
        }
    });

    xhr.send(null);

}

function deleteElementFromCollection() {

    let ask = prompt("Are you sure? Enter yes to delete");
   if (ask !== "yes") return;


    let url_string = window.location.href;
    let url = new URL(url_string);
    let colID = url.searchParams.get("colID");
    let musicID = url.searchParams.get("id");

    let xhr = new XMLHttpRequest();
    xhr.open("DELETE", "http://localhost:8081/collectionsService");

    xhr.addEventListener("load", function loadCallback() {
        switch (xhr.status) {
            case 200:
                console.log("Success " + xhr.response);
                window.location.href = "http://localhost:8081/collection?id=" + encodeURIComponent(colID);
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
        colID: colID,
        musicID: musicID
    };
    xhr.send(JSON.stringify(payload));

}