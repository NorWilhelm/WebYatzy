$.ajax({
    crossDomain: true,
    type:"GET",
    contentType: "application/json; charset=utf-8",
    url: "localhost:8080/WebYatzy-0.0.2/game",
    data: {projectID:1},
    dataType: "jsonp",
    jsonpCallback: 'fnsuccesscallback'
});