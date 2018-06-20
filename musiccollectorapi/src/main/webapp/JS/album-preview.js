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