function dzialam(){
	if(this.nextElementSibling.classList.contains('hide')){
		var size = ((this.nextElementSibling.getElementsByClassName("thumbnail").length/3 + 1) * this.nextElementSibling.getElementsByClassName("thumbnail")[0].clientHeight)-100  + "px";
		this.nextElementSibling.classList.add('show');
		this.nextElementSibling.classList.remove('hide');
		this.nextElementSibling.setAttribute("style","height:"+size);
		for(var i=0; i<this.getElementsByClassName("arrow-up").length; i++){
			this.getElementsByClassName("arrow-down")[i].style.display = "none";
			this.getElementsByClassName("arrow-up")[i].style.display = "block";
		}
	
	} else {
		this.nextElementSibling.classList.add('hide');
		this.nextElementSibling.classList.remove('show');
		this.nextElementSibling.setAttribute("style","height:0");
		for(var i=0; i<this.getElementsByClassName("arrow-down").length; i++){
			this.getElementsByClassName("arrow-down")[i].style.display ="block";
			this.getElementsByClassName("arrow-up")[i].style.display ="none";
		}
	}
}

window.onload = function(){
	for(var i = 0; i < document.getElementsByClassName("group-title").length; i++){
		document.getElementsByClassName("group-title")[i].addEventListener("click", dzialam);	
	}
}