function validateSignUp() {
    var x = document.forms["signup"]["firstName"].value;
    if(x == "") {
    alert("You must enter a first name");
    return false;}
}

//$('.signup')
//  .form({
//    on: 'blur',
  //  onFailure: function(formErrors, fields) {
    //$.each(fields, function(e)) {
   // }
    //}






//fields: {
//firstName : 'empty',
//lastName : 'empty',
//email : 'empty',
//password: 'empty',
//passwordValid: [ 'match[password]', 'empty'],
//startingWeight: 'empty'
//}
//})
//;