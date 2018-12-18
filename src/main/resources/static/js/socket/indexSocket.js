'use strict';
var UserMap=[];
var stompClient = null;
$(document).ready(function(){
	connect();
})

function connectToSocket(){
	if(stompClient==null){
		connect();
	}
	
}
function disConnectToSocket(){
	stompClient.disconnect();
	console.log("value of stopmClient is :"+stompClient);
	stompClient=null;
	

}

function connect() {	
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
        stompClient.reconnect_delay=5000;
        stompClient.connect('','', onConnected, onError); 
       // event.preventDefault();
}


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public/event', onEventReceived);
//    stompClient.subscribe('/topic/private', onPrivateMessageReceived);
    // Tell your username to the server
    
   
}

function onEventReceived(payload) {
	//console.log("in this "+time); 
	console.log("in this "+payload);
	var message = JSON.parse(payload.body);
	console.log(message.time);
	console.log(message.activeUser[0].name);
	var a="";
	for(var i=0;i<message.activeUser.length;i++){
		console.log(message.activeUser[i].active)
		UserMap[message.activeUser[i].id]=message.activeUser[i]
		if(message.activeUser[i].active==1){
			a+="<a href='#' onclick='openMessagesBox("+message.activeUser[i].id+")'><img src='http://192.168.10.28:8080/images/green.png'/ height='10px' width='10px' style='margin-right:3px'>"+message.activeUser[i].name+"</a>";
		}
		else{
			a+="<a href='#' onclick='openMessagesBox("+message.activeUser[i].id+")'><img src='http://192.168.10.28:8080/images/red.png'/ height='10px' width='10px' style='margin-right:3px'>"+message.activeUser[i].name+"</a>";

		}
		
	}
	console.log(a);
	$(".dropup-content").html("");
	$(".dropup-content").html(""+a);

}

function onError(error) {	
	 console.log("value of stomClient: "+stompClient);
}

function onClose(){	
	console.log("Socket is closed******************");	
}

function openMessagesBox(userId){
	console.log(UserMap);
	var User = UserMap[userId];
	console.log("in openMessagesBox:"+User.name);
	
	var a ="";
	a+=" <button class='dropbtn' >"+User.name+"</button>"
	$("#messageBox").html(a);
}
