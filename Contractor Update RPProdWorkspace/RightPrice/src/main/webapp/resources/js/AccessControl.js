function accessControl(userRole) {
	var currentUser = sessionStorage.getItem("proxyArray");
	console.log("The Current User Value is......... ");
	console.log(currentUser);
		if(currentUser != null || currentUser != undefined ) {
			var userRoleSplit = currentUser.split(/[\s,]+/);
			console.log("Access Count");
			console.log(userRoleSplit[userRoleSplit.length - 1]);
			if(userRoleSplit[userRoleSplit.length - 1] == 'false') {
				userRole = userRole.replace("[", "").replace("]", "").replace(" ","").split(',');
				userRole = sessionStorage.getItem("proxyArray");
				
			} else {
				sessionStorage.removeItem("proxyArray");
			}
		}  

			$("#includedFooter").load("/AdminPortal/Portal/Footer.jsp");	
			$('ul.dropdown-menu [data-toggle=dropdown]').on('click',function(event) {
				event.preventDefault();
				event.stopPropagation();
				$(this).parent().siblings()
						.removeClass('open');
				$(this).parent().toggleClass('open');
			});
		 
		 // On load hide all the available nav bar
			$(".02").hide();
			$(".03").hide();
			$(".04").hide();
			$(".05").hide();
			$(".06").hide();
			$(".07").hide();
			$(".09").hide();
			$(".10").hide();
			$(".11").hide();
			$(".12").hide();
			userRole = userRole.replace("[", "").replace("]", "").replace(" ","").split(',');
			if(currentUser != null || currentUser != undefined ) {
				$(".02").show();
				$(".02_01").show();
				$(".proxy_08").show();
			} else {
				$(".proxy_08").hide();
			}
			setMenus(userRole);
		}

function setMenus(menuArray)
{	
	
	var size=menuArray.length;
	var userType=menuArray[1];
	//alert(userType)
	if(userType.length>12){
		var useraccess=menuArray[0]
		
		var useraccessBolean=menuArray[size-1]
		
		var base64key = "QmFyMTIzNDVCYXIxMjM0NQ==";
		var parsedBase64Key = CryptoJS.enc.Base64
				.parse(base64key);
		var ive = CryptoJS.enc.Utf8
				.parse('RandomInitVector');
		var encrypted = CryptoJS.AES.decrypt(
				userType, parsedBase64Key, {
					iv : ive
				});
		
		var decryptedText = encrypted
				.toString(CryptoJS.enc.Utf8);

		menuArray[1]=decryptedText;
		//alert(menuArray[1])
		
		var encrypteds = CryptoJS.AES.decrypt(
				useraccess, parsedBase64Key, {
					iv : ive
				});
		
		var decryptedTexts = encrypteds
				.toString(CryptoJS.enc.Utf8);
		
		menuArray[0]=decryptedTexts;
		//------------
		
		var encryptedss = CryptoJS.AES.decrypt(
				useraccessBolean, parsedBase64Key, {
					iv : ive
				});
		
		var decryptedTextss = encryptedss
				.toString(CryptoJS.enc.Utf8);
		menuArray[size-1]=decryptedTextss;
		console.log("the UserType in access control");
		console.log(menuArray[1]);
	}
	
	sessionStorage.setItem('userType',menuArray[1]);
	console.log("User Type Value "+ sessionStorage.getItem('userType'));
	
	console.log(menuArray);
	
	for (var i = 2; i < menuArray.length; i++) 
	{
		setvisibility(menuArray[i]);
	}
}

function setvisibility(listIdentifier)
{
	var menu =  listIdentifier.split("_");
	var explicitMenu = "";
	for (var i = 0; i < menu.length; i++) 
	{
		explicitMenu += menu[i];
		explicitMenu = explicitMenu.replace(" ","");
		$("."+ explicitMenu).show();
		explicitMenu+= "_";
	}
}