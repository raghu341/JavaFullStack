<html>
    <head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>FORM</title>

            <script type="text/javascript">


        <%
        String name = request.getParameter("name");
       /*  App myTest = new App();*/ 
        /*myTest.submitData(); */
        %>



            function getvalues()
            {

                    var name = document.getElementById("name");
                   
            }

            

            </script>

    </head>
    <body>
        <form action="App.java" method="post" >
            <table>
                
                <tr>
                    
                    <td>
                    <input type="text" placeholder="Search.." id = "name" name="search">
     				 <button type="submit" onclick="getvalues()"><i class="fa fa-search"></i></button></td>
                </tr>
            </table>

        </form>
    </body>
</html>