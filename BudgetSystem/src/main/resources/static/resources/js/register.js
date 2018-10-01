$(document).ready(function(){
	$('#registerForm').validate({
		
		rules:{
			userName:{
				required: true,
				email: true,
				maxlength: 50
			},
			password:{
				required: true,
				maxlength: 16
			},
			confirmPass:{
				required: true,
				equalTo: '#password'
			},
			fullName:{
				required: true,
				maxlength: 30
			}
		},
		messages:{
			userName:{
				required: "Username is required",
				email: "UserName must be email",
				maxlength: "Username must be less than 16 characters"
			},
			password:{
				required: "Password is required",
				maxlength: "Password must be less than 16 characters"
			},
			confirmPass:{
				required: "Confirm password is required",
				equalTo: "Confirm password did not match"
			},
			fullName:{
				required: "Name is required",
				maxlength: "Name must be less than 30 characters"
			}
		}		
	})
})