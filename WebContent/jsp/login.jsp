<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url action="doLogin" namespace="/login" id="doLoginURL"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="description" content="Reflect Template" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>My CMS Admin Login Page</title>
        <link rel="stylesheet" href="../css/login.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="../css/style1.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="../css/jquery-ui.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="../css/jquery.wysiwyg.css" type="text/css" media="screen" />
        <!--Internet Explorer Trancparency fix-->
        <!--[if IE 6]>
        <script src="../js/ie6pngfix.js"></script>
        <script>
          DD_belatedPNG.fix('#head, a, a span, img, .message p, .click_to_close, .ie6fix');
        </script>
        <![endif]--> 
        
        <script type='text/javascript' src='../js/jquery.js'></script>
        <script type='text/javascript' src='../js/jquery-ui.js'></script>
        <script type='text/javascript' src='../js/jquery.wysiwyg.js'></script>
    </head>
    
    <body class="nobackground">
    	
        <div id="login">
        
        	<h1 class="logo">
            	<a href="#">MyCMS Admin Login</a>
            </h1>
            <h2 class="loginheading">Login</h2>
            <div class="icon_login ie6fix"></div>
        	<form id="login-form" action="${doLoginURL}" method="post">
			<s:if test='message!=null'>
				<div class='negative'><span style='padding-left:30px;'><strong><s:property value="message" /></strong></span></div> 
			</s:if>

            <p>
            	<label for="name">Username</label>
            	<input class="input-medium" type="text" value="" name="username" id="name"/>
        	</p>
        	<p>
            	<label for="password">Password</label>
            	<input class="input-medium" type="password" value="" name="password" id="password"/>
        	</p>
        
        	<p class="remember">
            	<label for="checkbox1" class="inline">Remember me?</label>
            	<input type="checkbox" value="1" name="checkbox1" id="checkbox1" />
        	</p>
        	<p class="clearboth">
            	<input class="button" name="submit" type="submit" value="Login"/>
        	</p>
            </form>
        </div>
        
        <div class="login_message message error" style="display:none">
          <p>Wrong Username or password.</p>
        </div>
    </body>
</html>