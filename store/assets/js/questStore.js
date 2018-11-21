function dzialam(){
	if(this.nextElementSibling.classList.contains('hide')){
		var size = ((Math.ceil(this.nextElementSibling.getElementsByClassName("thumbnail").length/3)+0.5) * document.getElementsByClassName("thumbnail")[0].clientHeight)  + "px";
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

// function move() {
//     var elem = document.getElementById("myBar");
//     var width = 1;
//     var id = setInterval(frame, 10);
//     function frame() {
//         if (width >= 100) {
//             clearInterval(id);
//         } else {
//             width++;
//             elem.style.width = width + '%';
//         }
//     }
// } 

function show(){
	this.getElementsByClassName("blur")[0].style.opacity = "1";
	this.getElementsByClassName("blur2")[0].style.opacity = "1";
	this.getElementsByClassName("description")[0].style.opacity = "1";
}

function hide(){
	this.getElementsByClassName("blur")[0].style.opacity = "0";
	this.getElementsByClassName("blur2")[0].style.opacity = "0";
	this.getElementsByClassName("description")[0].style.opacity = "0";
}

function changeToInput(){
	var	thumbnail = this.parentNode;
	var coin = thumbnail.getElementsByClassName("coin")[0];
	coin.style.display = "none";
	var input = document.createElement("input");
	input.type = "text";
	input.value = "100";
	alert(thumbnail);
	alert(thumbnail.getElementsByClassName("caption")[0]);
	alert(input);
	thumbnail.insertBefore(input, thumbnail);

	input.focus();
}

window.onload = function(){
	for(var i=0; i<document.querySelectorAll('.new-element').length; i++){
		document.querySelectorAll('.new-element')[i].addEventListener("click", changeToInput)
	}
	for(var i=0; i< document.querySelectorAll('.thumbnail').length; i++){
		document.querySelectorAll('.thumbnail')[i].addEventListener("mouseover", show);
		document.querySelectorAll('.thumbnail')[i].addEventListener("mouseout", hide);		
	}

	for(var i = 0; i < document.getElementsByClassName("group-title").length; i++){
		document.getElementsByClassName("group-title")[i].addEventListener("click", dzialam);	
	}
}
