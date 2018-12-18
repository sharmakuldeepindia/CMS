var diffExchangeEpoch = 315532800;
var RuleTokenRow;




function fetchInstrumentsForAddToken() {
	$('#addTokenCallPut').val($('#addTokenCallPut option:first').val());
	$('#addTokenStrike').empty();
	var addTokenInstrument = $("#addTokenInstrument");
	$.ajax({
		type : "GET",
		url : "/fetch/getAllInstrumentNames",
		success : function(data) {
			addTokenInstrument.empty();
			var output = [];
			output.push('<option value>Select One</option>');
			for (var i = 0; i < data.length; i++) {
				// console.log(data.InstrumentList[i])

				if (data[i].length && (data[i] != "FUTIVX"))
					output.push('<option value="' + data[i] + '">' + data[i]
							+ '</option>');
			}
			addTokenInstrument.html(output.join(''));

		},
		error : function(e) {
			console.log(e);
		}
	});

}

function loadAddTokenSymbol(val) {
	$('#addTokenCallPut').val($('#addTokenCallPut option:first').val());
	$('#addTokenStrike').empty();
	var addTokenSymbol = $('#addTokenSymbol');
	var addTokenInstrument = $("#addTokenInstrument").val();
	if (addTokenInstrument.substring(0, 3) == 'FUT') {
		$("#expiryOPT").hide();
		$("#OPTRow").hide();
	}
	addTokenSymbol.empty();
	$.ajax({
		global : false,
		type : "POST",
		url : "/fetch/getAllTokenSymbol",
		data : {
			instrumentName : addTokenInstrument
		},
		success : function(data) {
			var output = [];
			output.push('<option value>Select One</option>');
			for (var i = 0; i < data.length; i++) {
				if (data[i])
					output.push('<option value="' + data[i] + '">' + data[i]
							+ '</option>');
			}
			addTokenSymbol.html(output.join(''));
		},
		error : function(e) {
			console.log(e);
		}
	})

}

function loadAddTokenExpiry(temp) {
	var symbol, instrument, addTokenExpiry;
	if (temp == 'openAddTokenDiv') {
		$('#addTokenCallPut').val($('#addTokenCallPut option:first').val());
		$('#addTokenStrike').empty();
		symbol = $('#addTokenSymbol').val();
		instrument = $('#addTokenInstrument').val();
	} else if (temp == 'openAddOPTTokenDiv') {
		symbol = $('#addOPTTokenSymbol').val();
		instrument = $('#addOPTTokenInstrument').val();
	}
	if (symbol) {
		$.ajax({
			global : false,
			type : "POST",
			url : "/fetch/getAllExpiryDates",
			data : {
				symbol : symbol
			},
			success : function(data) {
				if (temp == 'openAddTokenDiv') {
					if (instrument.substring(0, 3) == "OPT") {
						console.log("yes opt it is" + data);
						addTokenExpiry = $("#addTokenExpiry2");
						// $("#expiryOPT").show();
						// $("#OPTRow").show();
						$("#expiryOPT").css('display', 'table-cell');
						$("#OPTRow").css('display', 'table-cell');
						$("#expiryFUT").css("display", "none");
					} else if (instrument.substring(0, 3) == "FUT") {
						addTokenExpiry = $("#addTokenExpiry1");
						// $("#expiryFUT").show();
						// $("#expiryOPT").hide();
						// $("#OPTRow").hide();
						$("#expiryFUT").css('display', 'table-cell');
						$("#expiryOPT").css("display", "none");
						$("#OPTRow").css("display", "none");
					}
				}

				else if (temp == 'openAddOPTTokenDiv') {
					addTokenExpiry = $("#addOPTTokenExpiry");
				}
				var output = [];
				var abc = [];
				output.push('<option value>Select Expiry</option>');
				$.each(data, function(key, value) {
					abc.push(new Date((value + diffExchangeEpoch) * 1000)
							.toUTCString())
				});
				$.each(abc, function(key, value) {
					output.push('<option value="' + value + '">'
							+ value.substring(4, 16) + '</option>');
				});

				addTokenExpiry.html(output.join(''));
			}
		});
	}

}
function resetStrike() {
	$('#addTokenCallPut').val($('#addTokenCallPut option:first').val());
	$('#addTokenStrike').empty();
}

function loadAddTokenStrike() {
	var symbol = $('#addTokenSymbol').val();
	var instrument = $('#addTokenInstrument').val();
	var addTokenExpiry = $('#addTokenExpiry2').val();
	var addTokenCallPut = $('#addTokenCallPut').val();
	var date = new Date(addTokenExpiry);
	var myEpoch = date.getTime() / 1000.0;
	myEpoch = parseInt(myEpoch) - diffExchangeEpoch;
	var strike1 = $("#addTokenStrike");
	strike1.empty();
	if (addTokenExpiry && addTokenCallPut) {

		$.ajax({
			global : false,
			type : "post",
			url : "/fetch/getAllStrikesForTokenTable",
			data : {
				symbol : symbol,
				callPut : addTokenCallPut,
				expiryDate : myEpoch
			},
			success : function(data) {
				var output = [];
				var arr = [];
				$.each(data, function(key, value) {
					arr.push(value / 100);
				});
				arr.sort(function(a, b) {
					return a - b
				});
				output.push('<option value>Select Strike</option>');
				$.each(arr, function(key, value) {
					output.push('<option value="' + value + '">' + value
							+ '</option>');
				});
				strike1.html(output.join(''));
			}
		});
	}
}

function appendUserToken() {
	var symbol = $('#addTokenSymbol').val();
	var instrument = $('#addTokenInstrument').val();
	var addTokenExpiry1 = $('#addTokenExpiry1').val();
	var addTokenExpiry2 = $('#addTokenExpiry2').val();
	var addTokenCallPut = $('#addTokenCallPut').val();
	var addTokenStrike = $('#addTokenStrike').val();
	var expiry1Conversion = $('#addTokenExpiry1').find(":selected").text();
	var expiry2Conversion = $('#addTokenExpiry2').find(":selected").text();
	console.log(addTokenExpiry1+"***"+addTokenExpiry2);
	var date1 = new Date(addTokenExpiry1);
	var myEpoch = date1.getTime() / 1000.0;
	myEpoch = parseInt(myEpoch) - diffExchangeEpoch;
	var date2 = new Date(addTokenExpiry2);
	console.log(date2);
	var myEpoch1 = date2.getTime() / 1000.0;
	myEpoch1 = parseInt(myEpoch1) - diffExchangeEpoch;
	console.log("hello "+myEpoch1);
	console.log(myEpoch+"***"+myEpoch1);
	if (symbol && (addTokenExpiry1 || addTokenExpiry2)) {
		$.ajax({
			global : false,
			type : "post",
			url : "/fetch/getTokenForRuleRow",
			data : {
				symbol : symbol,
				callPut : addTokenCallPut,
				addTokenExpiry1 : myEpoch,
				instrument : instrument,
				addTokenExpiry2 : myEpoch1,
				strike : addTokenStrike,
				expiry2Conversion : expiry2Conversion,
				expiry1Conversion : expiry1Conversion
			},
			success : function(data) {
				console.log(data[0]);
				console.log(data[1]);
				if(data[0] && data[1]){
					console.log($(RuleTokenRow).find('.ruleLots').val());
					$(RuleTokenRow).find('.ruleToken').val(''+data[0]);
					$(RuleTokenRow).find('.ruleTokenName').val(''+data[1]);
					alert("Token added Successfully");	
				}
				else{
					alert("Token Not Found");
				}	
				
			},
			error: function(e){
				alert("Error in adding Token");
			}
		
		});
	}

}


var getAllUserRulesUpdate;
var userRuleMap = new Object();
function getUserRules(){
	
	var marketWatchTable = $("#marketWatchTable");	
	$.ajax({
		global : false,
		type : "get",
		//url : "/user/getUserRules",
		url: "/user/getAllUserRules1",
		data : {},
		success : function(data) {
			var s="";
			//console.log(data);
			for(var i=0;i<data.length;i++){
				userRuleMap[data[i].userRule.name]=data[i];
				var ruleRows = data[i].ruleTokens;
				var ruleTableRow = "";
				for (var j = 0; j < ruleRows.length; j++) {
				
					ruleTableRow += "<tr>"
							+ "<td hidden><input class='ruleInputText ruleRowId' value="
							+ ruleRows[j].id
							+ " type='text'></td>"
							+ "<td>"
							+ ruleRows[j].ruleBuySell+"</td>"

							+ "<td>"
							+ ruleRows[j].quantity
							+ "</td>"									
							+ "<td>"
							+ ruleRows[j].ruleTokenName
							+ "</td>"
							+ "<td>"
							+ ruleRows[j].ruleToken
							+ "</td>"
							+ "</tr>";
				}
				var finalRuleTable =ruleTableRow
				var tt= "<table class='table table-bordered customTableSmall' id='"+data[i].userRule.name+"' style='display:none'>"+
				"<thead style='background:#f0ad4e'><th>B/S</th><th>Qty</th><th>Token Name</th><th>Token</th></thead>"+
				finalRuleTable+"</table>"
				
				$("#ruleTokensDialog").append(tt);
				
				s+="<tr>" +
						"<td>"+(i+1)+"</td>" +
					
						"<td style='color:blue'>"+
						 "<span ondblclick='editRule(this)' onmouseover='showTokenData(this)'" +
						 " class='ruleName' onmouseleave='closeTokenData(this)' >"
						   +data[i].userRule.name+"</span></td>" +
						"<td class='ruleBuyQty'>"+data[i].userRule.bestBuyQty+"</td>" +
						"<td class='ruleBestBuy'>"+data[i].userRule.bestBuy+"</td>" +
						"<td class='ruleSellQty'>"+data[i].userRule.bestSellQty+"</td>" +
						"<td class='ruleBestSell'>"+data[i].userRule.bestSell+"</td>" +
						"<td class='ruleDepth'>"+data[i].userRule.depth+"</td>" +		
						"<td class='ruleId' hidden>"+data[i].userRule.id+"</td>" +
						"</tr>" 
					
			}
		
			
			//$("#marketWatchTable").trigger("update").trigger("sorton",[ [[0,0],[2,0]] ]);
//			   $(marketWatchTable).DataTable().destroy();
			  // $(marketWatchTable).clear();
			   $(marketWatchTable).find("tbody>tr").remove();
			   $(marketWatchTable).find("tbody").append(s);
			   //$(marketWatchTable).DataTable();
			   $(marketWatchTable).footable({
					"filtering": {
						"enabled": true,
						"delay": .1,
						"dropdownTitle": "Search in:",
						"filters": [{
							"name": "my-filter",							
							"columns": ["name"]
						}]
					}
				});
			    $(".footable-filtering").find('.dropdown-toggle').hide();
			    $(".footable-page-link").css("margin-left","10px");
			    $(".footable-page-link").css("font-size","20px");
			
			   updateAllUserRule();
			
		},
		error: function(e){
//			alert("Error in getting Rules");
			alert("Comming Soon");
		}
	
	});
	
}


function updateAllUserRule(){
	
	var marketWatchTable = $("#marketWatchTable");
	$.ajax({
		global : false,
		type : "get",
		//url : "/user/getUserRules",
		url: "/user/getAllUserRules1",
		data : {},
		success : function(data) {
			for(var i=0;i<data.length;i++){
				userRuleMap[data[i].userRule.name]= data[i];
				var ruleRows = data[i].ruleTokens;
//				var ruleTableRow = "";
//				for (var j = 0; j < ruleRows.length; j++) {				
//					ruleTableRow += "<tr>"
//							+ "<td hidden><input class='ruleInputText ruleRowId' value="
//							+ ruleRows[j].id
//							+ " type='text'></td>"
//							+ "<td>"
//							+ ruleRows[j].ruleBuySell+"</td>"
//
//							+ "<td>"
//							+ ruleRows[j].quantity
//							+ "</td>"									
//							+ "<td>"
//							+ ruleRows[j].ruleTokenName
//							+ "</td>"
//							+ "<td>"
//							+ ruleRows[j].ruleToken
//							+ "</td>"
//							+ "</tr>";
//				}
//				var finalRuleTable =ruleTableRow
//				var tt= "<table class='table table-bordered customTableSmall' id='"+data[i].userRule.name+"' style='display:none'>"+
//				"<th>B/S</th><th>Qty</th><th>Token Name</th><th>Token</th>"+
//				finalRuleTable+"</table>"
//				//$("#ruleTokensDialog").find("#"+data[i].userRule.name).remove();
//				$("#ruleTokensDialog").append(tt);		 
					
			}
			
			 $("#marketWatchTable").find('tbody tr:visible').each(function(){
		  
				var ruleName = $(this).find('.ruleName').html();
			
				var data = userRuleMap[ruleName];
				//console.log(data.userRule.name);
				$(this).find('.ruleBuyQty').html(data.userRule.bestBuyQty);
				$(this).find('.ruleBestBuy').html(data.userRule.bestBuy);
				$(this).find('.ruleSellQty').html(data.userRule.bestSellQty);
				$(this).find('.ruleBestSell').html(data.userRule.bestSell);
				$(this).find('.ruleDepth').html(data.userRule.depth);
		     });
			      clearTimeout(getAllUserRulesUpdate);
			      getAllUserRulesUpdate =setTimeout(updateAllUserRule,1000);
		   }
		});	
}

function showTokenData(val){
	//alert("hello"+($(val).html()));
	var ruleName = $(val).html();
	$("#ruleTokensDialog").dialog({
		height : 250,
		width:500,
		position: { my: "right bottom", at: "right-5 bottom-150", of: window},
		title : 'Token Of Rule '+ruleName,
	});
	$(ruleTokensDialog).dialog("open");
	$("#"+ruleName).css("display","table");
}

function closeTokenData(val){
	var ruleName = $(val).html();
	$(ruleTokensDialog).dialog("close");
	$("#"+ruleName).css("display","none");
}

function editRule(val){
	var ruleName = $(val).html();
	console.log(ruleName);
	var data = userRuleMap[ruleName];
	console.log(data.userRule);
	console.log(data.userRule.name);
	var ruleDiv = $("#ruleDiv");	
	var ruleTableStart = "<div class='ruleTopDiv' style=''>"
		+ "<div><span>Rule Name&nbsp;&nbsp;</span><input  class='ruleInputTextMedium ruleName' " +
				"onchange='checkExistingRuleName(this)' type='text' value="
		+ data.userRule.name
		+ ">"
		+ "<input hidden='hidden' class='ruleInputTextMedium ruleId' type='text' value="
		+ data.userRule.id
		+ ">"
		+ "&nbsp;&nbsp;<label class='checkbox-inline'>Alert &nbsp;<input type='checkbox' class='ruleAlert' value="
		+ data.userRule.alert
		+ "></label>"
		
		+ "&nbsp;&nbsp;Depth </td><td><select class='ruleInputText ruleDepth'>"
		+ "<option value='L1'>L1</option>"
		+ "<option value='L2'>L2</option>"
		+ "<option value='L3'>L3</option>"
		+ "<option value='L4'>L4</option>"
		+ "<option value='L5'>L5</option></select>"
		
		
		+ "&nbsp;&nbsp;<button class='btn btn-xs btn-success' onclick='saveRule(this)'>Update</button>"
		+ "&nbsp;&nbsp;<button class='btn btn-xs btn-danger' onclick='deleteRule(this)'>Delete</button>  </div>"
		+ "<table style='padding-top:0px' class='table-condensed table-hover table-bordered  ruleTable'>";

var ruleRows = data.ruleTokens;
var ruleTableRow = "";
for (var j = 0; j < ruleRows.length; j++) {
	ruleTableRow += "<tr>"
			+ "<td><button hidden='hidden' class='btn btn-xs btn-info'>Add Token</button></td>"
			+ "<td hidden='hidden'><input class='ruleInputText ruleRowId' value="
			+ ruleRows[j].id
			+ " type='text'></td>"
			+ "<td>B/S</td><td><select class='ruleInputText ruleBuySell'>"
			+ "<option value='Buy' "
			+ (((ruleRows[j].ruleBuySell) == "Buy") ? 'selected'
					: '')
			+ ">Buy</option>"
			+ "<option value='Sell' "
			+ (((ruleRows[j].ruleBuySell) == "Sell") ? 'selected'
					: '')
			+ ">Sell</option></select></td>"

			+ "<td>Quantity</td><td><input class='ruleInputText ruleQuantity' type='text' value="
			+ ruleRows[j].quantity
			+ "></td>"									
			+ "<td>Token</td><td><input readonly class='ruleInputTextLarge ruleTokenName' type='text'value="
			+ ruleRows[j].ruleTokenName
			+ "></td>"
			+ "<td><input readonly class='ruleInputText ruleToken' type='text'value="
			+ ruleRows[j].ruleToken
			+ "></td>"
			+ "<td><button class='btn btn-xs btn-info' onclick='addRowToRuleTable(this)'>+</button></td>"
			+ "<td><button class='btn btn-xs btn-info' onclick='deleteRuleTableRow(this)'>-</button></td></tr>";
}
var finalRuleTable = ruleTableStart + ruleTableRow
		+ ruleTableEnd;
$(ruleDiv).html('');
$(ruleDiv).append(finalRuleTable);
$("#ruleTableDialog").dialog('open');
$('.addRuleButton').hide();



}

