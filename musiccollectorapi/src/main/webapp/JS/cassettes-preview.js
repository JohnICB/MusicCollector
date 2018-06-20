function deleteElementFromCollection() {

    let ask = prompt("Are you sure? Enter yes to delete");
    console.log(ask);

    console.log(ask !== "yes"  + " " + ask !== "YES");

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