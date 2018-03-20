$(document).ready(function(){
    $(".vyn").hover(function(){
        // $(this).css("opacity","0.3");
        let img = $(this).parent();
        $(this).css("opacity","0.4");
        img.find(".preview").css("opacity","1");
        
        // let img2 = $(this).getElementsByClassName('vyn');
        // console.log(img2);
        // img2.css("opacity","0.3");
        // $(this).find('preview').css("opacity","1");
       
        
    },function(){
        // $(this).css("opacity","1");
        // $(".preview").css("opacity","0");
        let img = $(this).parent();
        $(this).css("opacity","1");
        img.find(".preview").css("opacity","0");
    });
    $(".preview").hover(function(){
        // $(this).css("opacity","0.3");
        let img = $(this).parent();
        $(this).css("opacity","1");
        img.find(".vyn").css("opacity","0.4");
        
        // let img2 = $(this).getElementsByClassName('vyn');
        // console.log(img2);
        // img2.css("opacity","0.3");
        // $(this).find('preview').css("opacity","1");
       
        
    },function(){
      
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
function toggleView(){
    var el = document.getElementsByClassName("product-list");
    el.classList.toggle("box");
    el = document.getElementsByClassName("product");
    for(i=0;i<el.length;i++){
        el[i].classList.toggle("box");
    }
}
