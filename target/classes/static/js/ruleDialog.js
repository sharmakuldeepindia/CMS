$(function() {	

	//$("#marketWatchTable").tablesorter(); 
	
	getUserRules();
	$(".dialog").dialog({
		autoOpen : false,
		draggable : true,
		//position : [ 'middle', 50 ],
		position: 'center',
		width : window.innerWidth - 200,
		
		height : 300,
		modal : false,
		resizable : true,
		maxWidth : window.innerWidth,
		autoResize : true,
		title : 'Rule Table',
		open : function() {
		},
		close : function(ev, ui) {
			
		}
	});
	loadAllUserRuleNames();

	$("#tokenSelectDialog").dialog({
		height : 250,
		position: 'bottom',
		title : 'Token Table',
		open : function() {
		},
		close : function(ev, ui) {
		}
	});
	
	$("#marketWatchDialog").dialog({
		height : 400,
		position: 'center',
		title : 'Market Watch',
		open : function() {
		},
		close : function(ev, ui) {
		}
	});
	
	
	//search Rule table by rule Name*************************************
	
	$('#ruleSearchBox').on("keyup", function(e) {
	  //  if (e.keyCode == 13) {
	      var searchText = $("#ruleSearchBox").val();
	      if(searchText){
//	    	  $("#marketWatchTable tr:gt(0)").each(function(){
//	    		  console.log($(this).find('.ruleName').html());
//	    		  var ruleName =($(this).find('.ruleName').html());	    		  
//	    		  if(searchText==ruleName){
//	    			 $(this).show(); 
//	    		  }
//	    		  else{
//	    			  $(this).hide();
//	    		  }
//	    	  });
	    
	    	        $("#marketWatchTable tr:gt(0)").each(function(index, row)
	    	        {
	    	            var allCells = $(row).find('td');
	    	            if(allCells.length > 0)
	    	            {
	    	                var found = false;
	    	                allCells.each(function(index, td)
	    	                {
	    	                    var regExp = new RegExp(searchText, 'i');
	    	                    if(regExp.test($(td).text()))
	    	                    {
	    	                        found = true;
	    	                        return false;
	    	                    }
	    	                });
	    	                if(found == true)$(row).show();
	    	                else
	    	                {
	    	                    $(row).hide();
//	    	                    $(row).css("display","none");
	    	                }
	    	            }
	    	        });
	      }
	      else{
	    	  $("#marketWatchTable tr:gt(0)").each(function(index, row){
	    		  $(this).show();
	    	  }) 
	      }
	   // }
	});

});

function openTokenSelectDialog(temp) {
	RuleTokenRow=$(temp).parents('tr');
	$("#tokenSelectDialog").dialog('close');
	$("#tokenSelectDialog").dialog('open');

	fetchInstrumentsForAddToken();
}

function openRuleTable() {
	$("#ruleTableDialog").dialog('open');
	//getAllUserRules();
	$(ruleDiv).html('');

	$('.addRuleButton').show();
}

function openMarketWatch(){	
	$("#marketWatchDialog").dialog('open');
	getUserRules();
	
}