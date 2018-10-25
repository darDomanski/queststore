

function showGroupContent(idOfGroup) {
    
    var element = document.getElementById(idOfGroup);
    style = window.getComputedStyle(element);
    res = style.getPropertyValue("display");

    if (res == "flex") {
        document.getElementById(idOfGroup).style.display="none";
    }
    else if (res == "none") {
        document.getElementById(idOfGroup).style.display="flex";
    }
    
    
   
}