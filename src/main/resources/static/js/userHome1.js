var UserRuleNames = new Object();
function loadAllUserRuleNames() {
	//console.log("loadingggggggggggg");
	$.ajax({
		type : "GET",
		/* contentType : "application/json", */
		url : "/user/getUserRuleNames",
		/* data : JSON.stringify(obj), */
		/*
		 * dataType : 'json', cache : false,
		 */

		success : function(data) {
			for (var i = 0; i < data.length; i++) {
				// console.log("i"+i);
				// console.log(data[i]);
				UserRuleNames[data[i]] = 1;
			}
			// console.log("Already added Rules:");
			// console.log(UserRuleNames);
		},
		error : function(e) {
			console.log(e);
		}
	});
}

var MaxUserRules = 3;

var ruleTableRow = "<tr><td><button class='btn btn-xs btn-info' onclick='openTokenSelectDialog(this)'>Add Token</button></td>"
		+ "<td hidden='hidden'><input class='ruleInputText ruleRowId' value='' type='text'></td><td></td>"
		+ "<td>B/S</td><td><select class='ruleInputText ruleBuySell'>"
		+ "<option value='Buy'>Buy</option>"
		+ "<option value='Sell'>Sell</option></select></td>"
//		+ "<td>Lots</td><td><input class='ruleInputText ruleLots' type='text'></td>"
//		+ "<td>Levels</td><td><input class='ruleInputText ruleLevel' type='text'></td>"
		+ "<td>Quantity</td><td><input class='ruleInputText ruleQuantity' type='text'></td>"
		+ "<td>Token</td><td><input readonly class='ruleInputTextLarge ruleTokenName' type='text'></td>"
		+ "<td><input readonly class='ruleInputText ruleToken' type='text'></td>"
		+ "<td><button class='btn btn-xs btn-info' onclick='addRowToRuleTable(this)'>+</button></td>"
		+ "<td><button class='btn btn-xs btn-info' onclick='deleteRuleTableRow(this)'>-</button></td></tr>";
var ruleTableEnd = "</tbody></table><hr style='border:0px solid white'></div>";

function addNewRule() {
	var ruleDiv = $("#ruleDiv");
	var ruleTableStart = "<div class='ruleTopDiv' style=''>"
			+ "<div><span>Rule Name&nbsp;&nbsp;</span><input class='ruleInputTextMedium ruleName' onblur='checkExistingRuleName(this)' type='text'>"
			+ "<input hidden='hidden' class='ruleInputTextMedium ruleName'  type='text' val=''>"
			+ "&nbsp;&nbsp;<label class='checkbox-inline'>Alert &nbsp;<input type='checkbox' class='ruleAlert' value=''></label>"
			
			+ "&nbsp;&nbsp;Depth </td><td><select class='ruleInputText ruleDepth'>"
			+ "<option value='L1'>L1</option>"
			+ "<option value='L2'>L2</option>"
			+ "<option value='L3'>L3</option>"
			+ "<option value='L4'>L4</option>"
			+ "<option value='L5'>L5</option></select>"
			
			+ "&nbsp;&nbsp;<button class='btn btn-xs btn-success' onclick='saveRule(this)'>Submit</button>"
			+ "&nbsp;&nbsp;<button class='btn btn-xs btn-danger' onclick='deleteRule(this)'>Delete</button> </div>"
			+ "<table style='padding-top:0px' class='table-hover table-bordered table-condensed ruleTable'>";


	var finalRuleTable = ruleTableStart + ruleTableRow + ruleTableEnd;
	$(ruleDiv).append(finalRuleTable);
}

function addRowToRuleTable(val) {
	var parentTable = $(val).closest('table');
	var noOfRows = $(parentTable).find('tbody > tr').length;
	if (noOfRows >= MaxUserRules) {
		alert("Max Allowed Rows in Rule:" + MaxUserRules);
		return false;
	}

	$(val).closest('table').append(ruleTableRow);
}
function deleteRuleTableRow(val) {
	if (confirm("Are You Sure to Delete Row?")) {
		var ruleRow = $(val).closest('tr');
		var ruleTopDiv = $(val).parents('.ruleTopDiv');
		var parentTable = $(val).closest('table');
		var noOfRows = $(parentTable).find('tbody > tr').length;
		console.log("this is the length:" + noOfRows);
		if (noOfRows == 1) {
			alert("Delete Complete Rule In Case of Single Row!");
			return false;
		} else {
			var ruleRowId = $(ruleRow).find('.ruleRowId').val();
			var ruleId = $(ruleTopDiv).find('.ruleId').val();
			if (ruleRowId) {
				$.ajax({
					type : "POST",
					url : "/user/deleteSingleRuleRow",
					data : {
						ruleRowId : ruleRowId,
						ruleId:ruleId
					},

					success : function(data) {
						if (data) {
							console.log(data);
							$(val).closest('tr').remove();
						} else {
							alert("Problem in deleting Rule Row");
						}

					},
					error : function(e) {

						console.log(e);

					}
				});

			} else {
				$(val).closest('tr').remove();
			}
		}

	} else {
		return;
	}

}

function saveRule(val) {
	var userRule = new Object();
	var ruleTopDiv = $(val).parents('.ruleTopDiv');
	userRule.name = $(ruleTopDiv).find('.ruleName').val();
	userRule.id = $(ruleTopDiv).find('.ruleId').val();
	userRule.alert = $(ruleTopDiv).find('.ruleAlert').is(':checked');
	userRule.depth = $(ruleTopDiv).find('.ruleDepth').val();
	console.log("rule name is this:" + userRule.name);
	console.log("rule alert is this:" + userRule.alert);
	console.log("rule depth is this:"+userRule.depth);
	var ruleTable = $(ruleTopDiv).find('.ruleTable');
	var ruleRowObject = new Object();
	userRule.tokenCount=0;
	var ruleRowArray = [];
	$(ruleTable)
			.find('tbody > tr')
			.each(
					function() {
						ruleRowObject.quantity = $(this).find('.ruleQuantity').val();
						ruleRowObject.id = $(this).find('.ruleRowId').val();
						ruleRowObject.ruleBuySell = $(this).find('.ruleBuySell ').val();
						ruleRowObject.ruleTokenName = $(this).find('.ruleTokenName ').val();
						ruleRowObject.ruleToken = $(this).find('.ruleToken').val();
						console.log(ruleRowObject.quantity + "***"
							    + ruleRowObject.ruleBuySell + "**"
								+ ruleRowObject.ruleTokenName + "**"
								+ ruleRowObject.ruleToken);
						ruleRowArray.push(ruleRowObject);
						ruleRowObject = {};
						userRule.tokenCount+=1;
					});
	// userRule.ruleRowArray=ruleRowArray;
	var obj = new Object();
	obj.userRule = userRule;
	obj.ruleTokens = ruleRowArray;
	console.log(JSON.stringify(obj));
	// console.log(userRule.ruleRowArray.length);
	// for(var i = 0 ;i<userRule.ruleRowArray.length;i++){
	// console.log('in thissss');
	// console.log(userRule.ruleRowArray[i].lots);
	// }

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/user/saveRule",
		data : JSON.stringify(obj),
		dataType : 'json',
		cache : false,

		success : function(data) {	
		
			if(data){
				alert("Rule Saved Successfully!");
			}
			
			getUserRules();

		},
		error : function(e) {

			console.log(e);

		}
	});
}

function getAllUserRules() {
	var ruleDiv = $("#ruleDiv");
	$(ruleDiv).html("");
	$
			.ajax({
				type : "GET",
				/* contentType: "application/json", */
				url : "/user/getAllUserRules1",
				/* data: JSON.stringify(obj), */
				/* dataType: 'json', */
				/* cache: false, */

				success : function(data) {
					console.log("hey just got the response ******************");
					console.log(data);
					for (var i = 0; i < data.length; i++) {
						console.log(data[i].userRule.name);

				
						var ruleTableStart = "<div class='ruleTopDiv' style=''>"
								+ "<div><span>Rule Name&nbsp;&nbsp;</span><input  class='ruleInputTextMedium ruleName' type='text' value="
								+ data[i].userRule.name
								+ ">"
								+ "<input hidden='hidden' class='ruleInputTextMedium ruleId' type='text' value="
								+ data[i].userRule.id
								+ ">"
								+ "&nbsp;&nbsp;<label class='checkbox-inline'>Alert &nbsp;<input type='checkbox' class='ruleAlert' value="
								+ data[i].userRule.alert
								+ "></label>"
								
								+ "&nbsp;&nbsp;Depth </td><td><select class='ruleInputText ruleDepth'>"
								+ "<option value='L1'>L1</option>"
								+ "<option value='L2'>L2</option>"
								+ "<option value='L3'>L3</option>"
								+ "<option value='L4'>L4</option>"
								+ "<option value='L5'>L5</option></select>"
								
								
								+ "&nbsp;&nbsp;<button class='btn btn-xs btn-success' onclick='saveRule(this)'>Update</button>"
								+ "&nbsp;&nbsp;<button class='btn btn-xs btn-danger' onclick='deleteRule(this)'>Delete</button>  </div>"
								+ "<table style='padding-top:0px' class=''>";

						var ruleRows = data[i].ruleTokens;
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
						//$(ruleDiv).append(finalRuleTable);
					}
				},
				error : function(e) {
					console.log(e);
				}
			});
}

function deleteRule(val) {
	if (confirm("Are You Sure to Delete Rule?")) {
		var ruleTopDiv = $(val).closest('.ruleTopDiv');
		var ruleId = $(ruleTopDiv).find('.ruleId').val();
		var ruleTable = $(ruleTopDiv).find('.ruleTable');
		var ruleRowIdArray = [];
		$(ruleTable).find('tbody > tr').each(function() {
			var ruleRowId = $(this).find('.ruleRowId').val()
			ruleRowIdArray.push(ruleRowId);
		});
		console.log(ruleRowIdArray + "ruleId****" + ruleId + "***");
		if (ruleId) {
			console.log("rule will be deleting from backend");
			$.ajax({
				type : "POST",
				/* contentType : "application/json", */
				url : "/user/deleteUserRule",
				data : {
					ruleId : ruleId,
					ruleRowIdArray : ruleRowIdArray
				},
				/* dataType : 'json', */
				cache : false,

				success : function(data) {

					if (data) {
						console.log(data);
						$(ruleTopDiv).remove();
					} else {
						alert("Problem in deleting Rule!");
					}
					$("#ruleTableDialog").dialog("close");
					getUserRules();
				},
				error : function(e) {

					console.log(e);

				}
			});
		} else {
			console.log("rule will be deleting from frontend");
			$(ruleTopDiv).remove();
		}
	} else {
		return false;
	}
}

function checkExistingRuleName(val) {
	console.log("this function called");
	var ruleName = $(val).val();
	if (ruleName) {
		if (ruleName in UserRuleNames) {			
			alert("Rule Name already exists");
			$(val).val('').focus();
		}
	}
}


