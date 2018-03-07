const BRAND = document.querySelectorAll(".brandC");
const YEAR = document.querySelectorAll(".yearC");
const OS = document.querySelectorAll(".osC");

window.onload = function() {
	var string = window.location + "";
	var split = string.split("?");
	string = string.substring(split[0].length + 1);
	split = string.split("&");
	var brands = split[0].split("=")[1].split(",");
	var osList = split[1].split("=")[1].split(",");
	var yearList = split[2].split("=")[1].split(",");
	for (let i = 0; i < BRAND.length; i++) {
		for(let j = 0; j< brands.length; j++){
			if(brands[j] == BRAND[i].value){
				BRAND[i].checked = true;
			}
		}
	}
	for (let i = 0; i < YEAR.length; i++) {
		console.log(YEAR[i].value);
		for(let j = 0; j< yearList.length; j++){
			if(yearList[j] == YEAR[i].value){
				YEAR[i].checked = true;
			}
		}
	}
	for (let i = 0; i < OS.length; i++) {
		for(let j = 0; j< osList.length; j++){
			if(osList[j] == OS[i].value){
				OS[i].checked = true;
			}
		}
	}
	
	
	
	console.log(yearList);

};

function searchWithFilters() {

	let brandFilters = [];
	let yearFilters = [];
	let osFilters = [];
	console.log(BRAND[0].checked); // true/false
	for (let i = 0; i < BRAND.length; i++) {
		if (BRAND[i].checked) {
			brandFilters.push(BRAND[i].value);
		}
	}
	for (let i = 0; i < YEAR.length; i++) {
		if (YEAR[i].checked) {
			yearFilters.push(YEAR[i].value);
		}
	}
	for (let i = 0; i < OS.length; i++) {
		if (OS[i].checked) {
			osFilters.push(OS[i].value);
		}
	}
	if (brandFilters.length == 0) {
		brandFilters.push("NA");
	}
	if (yearFilters.length == 0) {
		yearFilters.push("NA");
	}
	if (osFilters.length == 0) {
		osFilters.push("NA");
	}

	console.log("filters : " + brandFilters);
	console.log("http://localhost:8080/product/" + "fullFilter?brands="
			+ brandFilters + "&osList=" + osFilters + "&yearList="
			+ yearFilters + "&pageSize=10&pageNumber=0");

	window.location.replace("http://localhost:8080/product/"
			+ "fullFilter?brands=" + brandFilters + "&osList=" + osFilters
			+ "&yearList=" + yearFilters + "&pageSize=10&pageNumber=1");
	/*
	 * $.ajax({ url: "/user/profile?username=" + getURLParameter('name'), type:
	 * "GET", contentType: "application/json; charset=utf-8", success: function
	 * (data, textStatus, jqXHR) { console.log("data: " + data); }, error:
	 * function (data, textStatus, jqXHR) { console.log("Cannot read"); } });
	 */

}

/*
 * document.getElementById("searchButton").addEventListener("click", function(){
 * 
 * }});
 */
