<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="alfa" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<!-- <link rel="stylesheet" type="text/css" href="mystyle.css"> -->

<style type="text/css">
.toBeHidden {
	display: none;
}


</style>

<script type="text/javascript">

  function checkAccountType(accountTypeShow) {
	if(accountTypeShow.value =="savingAccount"){
		document.getElementById("salaried").style.display="block";
		document.getElementById("overDraft").style.display="none";
		document.getElementById("curBalance").style.display="none";
		document.getElementById("savNotSalBalance").style.display="none";
		document.getElementById("savSalBalance").style.display="none";

	}
		else if(accountTypeShow.value =="currentAccount"){
			document.getElementById("overDraft").style.display="block";
			document.getElementById("curBalance").style.display="block";
			document.getElementById("salaried").style.display="none";
			document.getElementById("savNotSalBalance").style.display="none";
			document.getElementById("savSalBalance").style.display="none";
		}		
}
  function checkSalariedType(SalaryTypeShow) {
		if(SalaryTypeShow.value =="salaried"){
			document.getElementById("savSalBalance").style.display="block";
			document.getElementById("savNotSalBalance").style.display="none";

		}
  			else if(SalaryTypeShow.value =="notSalaried"){
				document.getElementById("savNotSalBalance").style.display="block";
				document.getElementById("savSalBalance").style.display="none";
			}		
	}
   
  
  function resetAll()
  {
	    document.getElementById("salaried").style.display="none";
		document.getElementById("overDraft").style.display="none";
		document.getElementById("curBalance").style.display="none";
		document.getElementById("savNotSalBalance").style.display="none";
		document.getElementById("savSalBalance").style.display="none";
	  
  }
  
  function checkDate(obj){
		 var ageDif = Date.now()-(new Date(obj.value)).getTime();
		 var ageDate = new Date(ageDif);
		 if(Math.abs(ageDate.getUTCFullYear()-1970) < 18 )
			 {
			   alert("Minimum age Should be 18 .");
			   obj.value = 0;
			 }
			
		 }
	  

</script>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="addAccount" method="get">
		<h1 align="center">Add Account Details</h1>
		<br>

		<table align="center" cellspacing="8" border="2">
			<tr>
				<td align="center">Customer Name</td>
				<td><input type="text" name="accountHolderName" required="required"></td>
			</tr>
			<tr>
				<td align="center">Account Type</td>
				<td><select name="accountType" onchange="checkAccountType(this);" required="required">
						<option id="" value="">Select Account Type</option>
						<option id="savingAccount" value="savingAccount">Saving Account</option>
						<option id="currentAccount" value="currentAccount">Current Account</option>
				</select></td>
			</tr>

			<tr id="Balance">
				<td align="center">Enter Balance</td>
				<td><input type="number" name="balance" min="10000"></td>
			</tr>

			<tr>
				<td align="center"><input type="reset" value="Clear" onclick="resetAll();"></td>
				<td align="center"><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>


</body>
</html>