let del = 0;

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

