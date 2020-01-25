$(function() {
	$("#list-button-1").click(showAllFilms);
});
/*
function showAllFilms() {
	$.ajax({
		url : "films-string.jsp",
		success : showList,
		cache : false
	});
}*/
function showList(){
	$("#film-result").load("films-string.jsp")
	
}