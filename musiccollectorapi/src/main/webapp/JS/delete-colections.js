
var lastSelected =null;
var lastIndex = null;
var lastElement = null;

$(function() {

    var $contextMenu = $("#contextMenu");
    
    $("body").on("contextmenu", "ul img", function(e) {
        lastElement = e.target.parentElement.parentElement;
        lastSelected = e.target.parentElement.href;
        //console.log(e.target.parentElement.parentElement.innerHTML="");
        lastIndex = lastSelected.substring(lastSelected.indexOf("id=")+3,lastSelected.length);
        console.log(lastIndex);
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

function deleteCollection() {


    let xhr = new XMLHttpRequest();
    xhr.open("DELETE", "http://localhost:8081/collections");
    let colID ={colID:parseInt(lastIndex)};
    console.log(JSON.stringify(colID));
    xhr.send(JSON.stringify(colID));
    xhr.addEventListener("load", function loadCallback() {
        switch (xhr.status) {
            case 200:
                if(xhr.response==="succes")
                    lastElement.innerHTML="";
                console.log(xhr.response);
                break;
            case 404:
                console.log("Oups! Not found");
                break;
        }

    });

}

