<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet">
<link href="css/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<script
	src="js/jquery-2.1.1.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
</head>
<body>
<div class="wrapper">
		<div id="main" style="padding:50px 0 0 0;">
	<form id="references" action="CalServlet">
		<h3>References</h3>
		<h4>This is a demonstration of inserting References</h4>
		<div>
			<label> <span>Source:</span> <input type="text" name="source"
				id="source" required>
			</label>
		</div>
		<div>
			<label> <span>Language:</span> <select name="language">
					<option value="English" selected="selected">English</option>
					<option value="Hindi">Hindi</option>
					<option value="Spanish">Spanish</option>
			</select>
			</label>
		</div>
		<div>
			<label> <span>URI:</span> <input type="text" name="uri"
				id="uri" required>
			</label>
		</div>
		<div>
			<label> <span>Subject:</span> <input type="text"
				name="subject" id="subject" required>
			</label>
		</div>
		<div>
			<label> <span>Title:</span> <input type="text" name="subject"
				id="subject" required>
			</label>
		</div>
		<div>
			<label> <span>DOI:</span> <input type="text" name="doi"
				id="doi" required>
			</label>
		</div>
		<div>
			<label> <span>PMid:</span> <input type="text" name="pmid"
				id="pmid" required>
			</label>
		</div>
		<div>
			<button name="submit" type="submit" style="margin-top: 10px"
				id="holiday-submit">Add Reference</button>
		</div>
	</form>
			
		</div>
	</div>
</body>
</html>