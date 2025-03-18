/**
 * http://usejsdoc.org/
 */
function validatePassword() {
	var key = CryptoJS.enc.Utf8.parse('Bar12345Bar12345');
	var ive  =CryptoJS.enc.Utf8.parse('RandomInitVector');
	if(document.getElementById("password").value != null && document.getElementById("password").value != ""){
		var encrypted = CryptoJS.AES.encrypt(document.getElementById("password").value,  key, {iv: ive});
		document.getElementById("password").value = encrypted;
		sessionStorage.removeItem("proxyArray");
		sessionStorage.removeItem("ngStorage-proxyIdVal");
		sessionStorage.removeItem("ngStorage-proxyUserId");
		sessionStorage.removeItem("ngStorage-proxyupdaterId");
	}
}
