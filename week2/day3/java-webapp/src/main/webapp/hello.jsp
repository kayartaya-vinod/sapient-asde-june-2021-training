<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Hello JSP</title>
    </head>
    <body>
        <h1>Hello, JSP!</h1>
        <hr />

        <p>JSP is a templating language from JCP</p>
        <% out.println("<p>JSP is a HTML file with Java content</p>"); %>
        <h3>Multiplication table for 67 is</h3>
        <%
            for(int i=1; i<=25; i++){
                out.println(String.format("%d X %d = %d <br/>", 67, i, 67*i));
            }
        %>
    </body>
</html>
