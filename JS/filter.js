function toggleFilter(){
    var el =document.getElementById("fMenu");
    el.classList.toggle("dropped");
    var sec = document.getElementsByClassName("section");
    for(i=0;i<sec.length;i++){
        sec[i].classList.toggle("dropped");
    }
    var btn = document.getElementById("fImg");
    if(btn.src!="http://127.0.0.1:5500/img/angle-double-up.svg"){
        el.style.animation="";
        for(i=0;i<sec.length;i++){
            sec[i].style.animation="";
        }
        btn.src="img/angle-double-up.svg";
        console.log(btn.src);
    }else{
        el.style.animation="filter-colapse 0.8s normal forwards";
        
        for(i=0;i<sec.length;i++){
            sec[i].style.animation="filter-colapse 0.8s normal forwards";
        }

        
        btn.src="img/angle-double-down.svg";
    }
}