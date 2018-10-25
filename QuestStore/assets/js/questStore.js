function dzialam(){
	if(this.nextElementSibling.classList.contains('hide')){
		this.nextElementSibling.classList.add('show');
		this.nextElementSibling.classList.remove('hide');
		for(var i=0; i<this.getElementsByClassName("arrow-up").length; i++){
			this.getElementsByClassName("arrow-down")[i].style.display = "none";
			this.getElementsByClassName("arrow-up")[i].style.display = "block";
		}
	
	} else {
		this.nextElementSibling.classList.add('hide');
		this.nextElementSibling.classList.remove('show');
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