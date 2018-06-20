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
    createCollectionList();
};

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
    modal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target === modal) {
        modal.style.display = "none";

        // let colName = document.getElementsByName("Collection name")[0].value;
        // let colDesc = document.getElementsByName("colDescription")[0].value;
        //
        // colName.innerText = "";
        // colDesc.innerHTML = "";
    }
};

window.onload = function () {
    console.log(window.location.href);

    var url = new URL(window.location.href);
    console.log(url.searchParams.get("mbid"));
    let mbid = url.searchParams.get("mbid");

    xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8081/aboutalbum?mbid="+encodeURIComponent(mbid));

    // console.log(xhr.response);

    xhr.addEventListener("load", function loadCallback() {
        switch (xhr.status) {
            case 200:
                // console.log("Success" + xhr.response);

                // console.log(xhr.response);
                let r = JSON.parse(xhr.response);

                let name = document.getElementById("name");
                name.innerHTML=r.name;
                let artist = document.getElementById("artist");
                artist.innerHTML = r.artist;
                let image = document.getElementById("imag");
                console.log("IMAGE "+r.image[Object.keys(r.image)[0]]);
                image.setAttribute("src",r.image[Object.keys(r.image)[0]]);

                let description = document.getElementById("desc");
                description.innerHTML=("Published: "+r.wiki[Object.keys(r.wiki)[0]]+"<br>"+"Summary: "+r.wiki[Object.keys(r.wiki)[1]]+
                    "<br>"+"Content: "+r.wiki[Object.keys(r.wiki)[2]]
                );

                let trackList = document.getElementById("tl");
                for(var i =0;i<r.tracks.length;i++){

                    console.log(r.tracks[Object.keys(r.tracks)[i]].name);
                    var newLI = document.createElement("li");
                    newLI.classList.add("track");
                    newLI.innerHTML = r.tracks[Object.keys(r.tracks)[i]].name;
                    trackList.appendChild(newLI);
                }

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
function addToCollection(id) {

    try{

        let collectionID = id.getElementsByTagName("span")[0].innerHTML;

        currentAlbumInfo["colID"] = collectionID;
        console.log(currentAlbumInfo);


    }catch(ex){console.log(ex)}




    let xhr = new XMLHttpRequest();
    xhr.open("PUT","http://localhost:8081/collections");
    xhr.addEventListener("load", function loadCallback() {
        switch (xhr.status) {
            case 200:


                console.log("PUT response"+xhr.response);


                break;
            case 404:
                console.log("Oups! Not found");
                break;
        }

    });
    let jsonStruct = {
        title: currentAlbumInfo["title"],
        duration: currentAlbumInfo["duration"],
        artists: currentAlbumInfo["artists"],
        region: currentAlbumInfo["region"],
        age: String(parseInt(Math.random()*100)),
        album: currentAlbumInfo["album"],
        usageGrade: currentAlbumInfo["usageGrade"],
        genre: currentAlbumInfo["genre"],
        releaseDate: currentAlbumInfo["releaseDate"],
        size: "120",
        isColored:false,
        isStereo:true,
        isSpecialEdition:false,
        rarity:"rare",
        colID : currentAlbumInfo["colID"]
    };
    console.log("json struct"+JSON.stringify(jsonStruct));

    xhr.send(JSON.stringify(jsonStruct));
    modal.style.display = "none";
}
function addUserCollections(image, title, description, id) {

    console.log("Image: " + image + " title: " + title + " description: " + description + " id: " + id)

    let colContainer = document.getElementById("collectionContainer"); //root

    let divContainer = document.createElement("div"); //item container
    divContainer.classList.add("collectionItem");

    let cid = document.createElement("span");
    cid.innerHTML=id;

    let link = document.createElement("a");
    link.setAttribute("href","#");
    link.setAttribute("onClick","addToCollection(this)");

    let imagee = document.createElement("img"); //image source
    imagee.src=image;

    let textContainer = document.createElement("div"); //text container
    textContainer.classList.add("collectionText");

    let tit = document.createElement("h4"); //title
    tit.innerHTML=title;

    let desc = document.createElement("p"); //description
    desc.innerHTML=description;

    textContainer.appendChild(tit);
    textContainer.appendChild(desc);

    divContainer.appendChild(cid)
    divContainer.appendChild(imagee);
    divContainer.appendChild(textContainer);
    link.appendChild(divContainer);
    colContainer.appendChild(link);


    // divContainer.appendChild(tit);
    // divContainer.appendChild(desc);
    // colContainer.appendChild(imagee);
    // colContainer.appendChild(divContainer);

}
function createCollectionList() {
    console.log("createCollectionList");

    let colContainer = document.getElementById("collectionContainer");
    colContainer.innerHTML="";

    let xhr = new XMLHttpRequest();

    xhr.open("GET", "http://localhost:8081/collections");
    console.log("GET: " + xhr.response);
    xhr.addEventListener("load", function loadCallback() {
        switch (xhr.status) {
            case 200:
                console.log("response: "+xhr.response)
                let responeArray = JSON.parse(xhr.response)

                for (var i = 0; i < responeArray.length; ++i) {
                    let isVinyl = responeArray[i].isVinyl;
                    if (isVinyl) {
                        var title = document.getElementById("name").innerHTML;
                        var duration = 0;
                        var artists =  document.getElementById("artist").innerHTML;
                        var region = "null";
                        var age = "null";
                        var album =document.getElementById("name").innerHTML;
                        var usageGrade = "null";
                        var genre = "null";
                        var releaseDate = "null";

                        let jsonStruct = {
                            title: title,
                            duration: duration,
                            artists: artists,
                            region: region,
                            size:"120",
                            age: age,
                            album: album,
                            usageGrade: usageGrade,
                            genre: genre,
                            releaseDate: releaseDate,
                            colID : null
                        };
                        currentAlbumInfo = jsonStruct;
                        console.log("creating vony;");
                        addUserCollections("../../Images/vinyl.png", responeArray[i].title, responeArray[i].description, responeArray[i].id);




                    }
                    else {
                        //console.log("P element"+ selectedElment.getElementsByTagName("p")[0].innerHTML);
                        var title = document.getElementById("name").innerHTML;
                        var duration = 0;
                        var artists =   document.getElementById("artist").innerHTML;
                        var region = "null";
                        var age = "null";
                        var album =document.getElementById("name").innerHTML;
                        var usageGrade = "null";
                        var genre = "null";
                        var releaseDate = "null";

                        let jsonStruct = {
                            title: title,
                            duration: duration,
                            artists: artists,
                            region: region,
                            age: age,
                            album: album,
                            usageGrade: usageGrade,
                            genre: genre,
                            releaseDate: releaseDate,
                            colID : null
                        };
                        currentAlbumInfo = jsonStruct;
                        console.log("creating cassete;");
                        addUserCollections("../../Images/cassette.jpg", responeArray[i].title, responeArray[i].description, responeArray[i].id);

                    }
                }
                console.log("Success GET" + xhr.response);


                break;
            case 404:
                console.log("Oups! Not found");
                break;
        }

    });
    xhr.send(null);
}