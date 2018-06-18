
        $(function() {

var $contextMenu = $("#contextMenu");

$("body").on("contextmenu", "ul img", function(e) {
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
