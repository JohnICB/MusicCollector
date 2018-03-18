$(document).ready(function(){
    $("product").mouseover(function(){
        $(this).css("opacity","0.3");
    },function(){
        $(this).css("opacity","1");
    });
});
function toggleFilter(){
    var el =document.getElementById("fMenu");
    el.classList.toggle("dropped");
    var sec = document.getElementsByClassName("section");
    for(i=0;i<sec.length;i++){
        sec[i].classList.toggle("dropped");
    }
    var btn = document.getElementById("fImg");
    var sr = btn.src;
    var ind =sr.indexOf("angle-double-up.svg");
    console.log(ind);
    if(ind<=0){
        el.style.animation="";
        for(i=0;i<sec.length;i++){
            sec[i].style.animation="";
        }
        btn.src="../Images/angle-double-up.svg";
        console.log(btn.src);
    }else{
        el.style.animation="filter-colapse 0.8s normal forwards";
        
        for(i=0;i<sec.length;i++){
            sec[i].style.animation="filter-colapse 0.8s normal forwards";
        }

        
        btn.src="../Images/angle-double-down.svg";
    }
}
