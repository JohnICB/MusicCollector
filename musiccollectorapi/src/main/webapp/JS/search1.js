let del = 0;



var selectedElment = null;
$(function() {

    var $contextMenu = $("#contextMenu");

    $("body").on("contextmenu", "ul img", function(e) {
        selectedElment = e.target.parentElement.parentElement;
        console.log(selectedElment);
        $contextMenu.css({
            display: "block",
            left: e.pageX,
            top: e.pageY
        });
        return false;
    });

    $('html').click(function() {
        $contextMenu.hide();
    });

});


function searchapi() {

    let searchValue = document.getElementsByName("search")[0].value;
    let searchType = document.getElementsByClassName("search-art")[0].value;

    console.log(searchValue);
    console.log(searchType);

    xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8081/searchapi?searchType=" +
        encodeURIComponent(searchType) +"&searchContent=" + encodeURIComponent(searchValue));

    // console.log(xhr.response);

    xhr.addEventListener("load", function loadCallback() {
        switch (xhr.status) {
            case 200:
                // console.log("Success" + xhr.response);

                // console.log(xhr.response);
                let r = JSON.parse(xhr.response);
                console.log(r.artist);

                del = 1;
                for (let i = 0; i < r.artist.length; i++)
                {
                    addElement(r.artist[i]);

                    del = 0;
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

    function addElement(element) {

        console.log("happening");

        let ul = document.getElementsByClassName("product-list")[0];
        let newLi = document.createElement("li");
        let a = document.createElement("a");
        let name = document.createElement("h3");
        let img = document.createElement("img");
        let desc = document.createElement("p");

        if (del === 1)
        {
            ul.innerHTML = "";
        }
        

        newLi.classList.add("product");
        a.setAttribute("href", "");
        name.appendChild(document.createTextNode(element.name));
        img.setAttribute("class", "vyn");
        img.setAttribute("src", element.image[Object.keys(element.image)[0]]);
        desc.appendChild(document.createTextNode(element.artist))

        newLi.appendChild(a);
        newLi.appendChild(name);
        newLi.appendChild(img);
        newLi.appendChild(desc);

        ul.appendChild(newLi);

        console.log(newLi);

       // console.log(element.image[Object.keys(element.image)[0]]);
    }

    // if (searchType === "Albums")



    xhr.addEventListener("error", function errorCallback() {
        console.log("Network error");
    });

    xhr.send(null);

}

function addUserCollections(image, title, description,id) {

    console.log("Image: "+image + " title: "+title + " description: " +description +" id: " +id)

}


function createCollectionList(){


    let xhr = new XMLHttpRequest();

    xhr.open("GET", "http://localhost:8081/collections");
    console.log("GET: "+xhr.response);
    xhr.addEventListener("load", function loadCallback() {
        switch (xhr.status) {
            case 200:
                let responeArray = JSON.parse(xhr.response)

                for(var i =0; i<responeArray.length;++i){
                    let isVinyl = responeArray[i].isVinyl;
                    if(isVinyl)
                        addUserCollections("../../Images/vinyl.png",responeArray[i].title,responeArray[i].description,responeArray[i].id);

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



// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();
// Get the modal
var modal = document.getElementById('myModal');
// Get the button that opens the modal
var btn = document.getElementById("myBtn");
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
btn.onclick = function() {
    modal.style.display = "block";
};

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target === modal) {
        modal.style.display = "none";

        let colName = document.getElementsByName("Collection name")[0].value;
        let colDesc = document.getElementsByName("colDescription")[0].value;

        colName.innerText = "";
        colDesc.innerHTML = "";
    }
};
